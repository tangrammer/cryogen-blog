{:title "Cylon Oauth2 Client and Authorization Server"
 :layout :post
 :tags  ["cylon" "oauth2"]
 :toc true}


A couple of months before, [juxt/cylon](https://github.com/juxt/cylon) added Oauth2 **client and provider** functionality using a [modular](https://github.com/juxt/modular) approach. This post, using an example integration [project](https://github.com/tangrammer/modular-cylon-example), tries to explain the implementation design details and the easy way to integrate Cylon Oauth2 client and provider in your component project.
 
I'd like to clear that [modular-cylon-example](https://github.com/tangrammer/modular-cylon-example) has been generated using the modular template `bootstrap-cover` following instructions that you can find on [modularity.org](http://modularity.org/). On top of this code I only translate the minimum needed code (mostly authored by [Malcolm Sparks](https://github.com/malcolmsparks)) to get working [juxt/cylon](https://github.com/juxt/cylon) oauth2 functionality. 
 
## Let's visualise our component system

Althouh I've followed closely this [Oauth2](https://tools.ietf.org/html/rfc6749) implementation, I usually need to visualise the system to think or talk about it. In this case I'll use system graphs made with [rhizome](https://github.com/ztellman/rhizome)

### Generate your bootstrap-cover system
Here you go the system result of:   
`$ lein new modular foo bootstrap-cover`

 
[<img src="https://dl.dropboxusercontent.com/u/8688858/cylon-oauth2-example/bootstrap-cover.png" alt="Drawing" style="width: 100%;"/>](https://dl.dropboxusercontent.com/u/8688858/cylon-oauth2-example/bootstrap-cover.png)

As you can see **bootstrap-cover** template, provides you with a :http-listener ([modular.http-kit/Webserver](https://github.com/juxt/modular/blob/master/modules/http-kit/src/modular/http_kit.clj#L13)) and four webservices ([modular.bidi/WebService](https://github.com/juxt/modular/blob/master/modules/bidi/src/modular/bidi.clj#L18)), all of them are instances of [modular.bidi/StaticResourceService](https://github.com/juxt/modular/blob/master/modules/bidi/src/modular/bidi.clj#L49) (to provide jquery, bootstrap and public resources) except **:bootstrap-cover-website-website** that makes the dynamic website responses.  
Note that although **:modular-bidi-router-webrouter** implements [WebService](https://github.com/juxt/modular/blob/master/modules/bidi/src/modular/bidi.clj#L18) too, is modular.ring/[WebRequestHandler](https://github.com/juxt/modular/blob/master/modules/ring/src/modular/ring.clj#L10) protocol the requirement for :http-listener-listener to use it. You can find more info about modular.bidi/[Router](https://github.com/juxt/modular/blob/master/modules/bidi/src/modular/bidi.clj#L129) [here](https://groups.google.com/forum/#!topic/clojure/YP_VM6Zf4RQ).


###  Add Oauth2 components
Then, I added the juxt/cylon components to provide Oauth2  [client](https://github.com/tangrammer/modular-cylon-example/blob/master/src/modular/cylon_oauth_example/system.clj#L277) and [provider](https://github.com/tangrammer/modular-cylon-example/blob/master/src/modular/cylon_oauth_example/system.clj#L143) functionality.

**Note that this demo, trying to be simple, uses atom backed stores** not intended to be used in production environments (you'l loose all your persistent data each time your app restarts). You can replace that persistence implementation by any one persistence implementations you prefer (for example postgre), only you'll have to implement the required protocol.

You can see now last bootstrap-cover components plus 22 more :) . Don't be afraid I'll work a bit on getting clear this first diagram. Bootstrap-cover components are highlighted in yellow and oauth components specifically written in this demo project in orange (related with mailing, form rendering and atom storing). 
Following this graph we can distinguish 2 servers, the new **:authorization-server-http-listener** component tree that represents the Oauth provider, and the old one :http-listener-listener that has the **:webapp-oauth-client** included

[<img src="https://dl.dropboxusercontent.com/u/8688858/cylon-oauth2-example/all-bis.png" alt="Drawing" style="width: 100%;"/>](https://dl.dropboxusercontent.com/u/8688858/cylon-oauth2-example/all-bis.png)

### Let's focus on Oauth2
As I suspect that you're thinking now that OAuth2 is really complex now :), let's split a bit this complex diagram to reach the simplicity of OAuth2 specification

#### Cylon persistence:  TokenStore and SessionStore protocols

As you can see in the system diagram there're a lot of **session-store** and **token-store** components needed to keep information in the different Oauth communication flows. 
Following are the protocols descriptions:

**cylon.token-store.protocols/[TokenStore](https://github.com/juxt/cylon/blob/master/src/cylon/token_store/protocols.clj#L11)**

> All TokenStore implementations must provide temporary or persistent
  storage and must expire tokens that are no longer valid. Expiry
  policies are left to the implementor to decide. Token ids are
  determined by the client, but are recommended to be resistent to
  prediction and thus forgery (using HMAC, etc.).

**cylon.session.protocols/[SessionStore](https://github.com/juxt/cylon/blob/master/src/cylon/session/protocols.clj#L3)**

> A SessionStore maps an identifier, stored in a cookie, to a set of
  attributes. It is able to get cookies from the HTTP request, and set
  them on the HTTP response. A SessionStore will typically wrap a
  TokenStore.


Due that all **session-store** need a token-store to maintain related data, let's remove from our visualisation all those obvius token-store components (highlighted in orange). On the other hand, let's do the same with listener and router component relations(hightlighted in yellow) removing listeners from visualisation

[<img src="https://dl.dropboxusercontent.com/u/8688858/cylon-oauth2-example/first-step.png" alt="Drawing" width="100%"/>](https://dl.dropboxusercontent.com/u/8688858/cylon-oauth2-example/first-step.png)
<br><br><br><hr><br><br><br>
#### modular.template/Templater && modular.bidi/StaticResourceService
As we are trying to get into Oauth2 specification and implementation details, why don't we take way the public **static resource services** (in yellow)  and the **clostache-templater** (in orange)? ... they actually don't have any relation with Oauth2 besides html rendering
[<img src="https://dl.dropboxusercontent.com/u/8688858/cylon-oauth2-example/second-step.png" alt="Drawing" style="width: 100%;"/>](https://dl.dropboxusercontent.com/u/8688858/cylon-oauth2-example/second-step.png)
<br><br><br><hr><br><br><br>

#### cylon oauth webservices
And, one pass more to take away the routers but keeping highlighted web services from both servers, authorization-server web-services in yellow, and web-server web-services in orange. Pay attention that although all of the highlighted components in this graph are webservices, some webservices depend on other webservices, as you can see with :authorization-server and :login, or :bootstrap-cover-website-website and :webapp-oauth-client

[<img src="https://dl.dropboxusercontent.com/u/8688858/cylon-oauth2-example/third-step.png" alt="Drawing" style="width: 100%;"/>](https://dl.dropboxusercontent.com/u/8688858/cylon-oauth2-example/third-step.png)

#### Yeah, it doesn't hurt now!
Then, we get following webservices in two servers

+ **:authorization-listener** webservices components: [**:authorization-server, :reset-password, :signup-form :login :logout**] 
+ **:http-listener** webservices components: [**:bootstrap-cover-website-website :webapp-oauth-client**]. 

<hr><br><br><br>

# Oauth Client
#### WebService + AccessTokenGrantee + RequestAuthenticator
The **:webapp-oauth-client** (that represents the **oauth client role**) lives on the same http-listener that our old **:bootstrap-cover-website-website** to provide it with oauth client functionality. In other words, your current webapp only need this dependency to get the oauth client behavior (grant privileges, logout, solicit access token, validate token, refresh access token), moreover it can authenticate requests based in client session identity.  

Reference implementation cylon.oauth.client.web-client/[WebClient](https://github.com/juxt/cylon/blob/master/src/cylon/oauth/client/web_client.clj)


```clojure

WebClient Protocols
;==================

modular.bidi/WebService (routes):
 + :get "/grant" 
 + :get "/logout" 

cylon.oauth.client/AccessTokenGrantee
  (solicit-access-token
    [_ req uri]
    [_ req uri scope-korks]
    "Initiate a process (typically via a HTTP redirect) that will result
    in a new request being made with an access token, if possible. Don't
    request specific scopes but get the defaults for the client.")

  (expired? [_ req access-token])
  
  (refresh-access-token [_ req]
    "Initiate a process (typically via a HTTP redirect) that will result
    in a new request being made with an access token, if possible."
    ))

cylon.authentication.protocols/RequestAuthenticator
(authenticate [_ request]
    "Return (as a map) any credentials that can be determined from the
    given Ring request")

```

Then, <span style="background-color:orange">**:webapp-oauth-client**</span> besides behaving as an independet modular.bidi/**WebService** connected to its related :router, and responsing to `/grant` and `/logout` http `get` calls it also accomplish cylon.oauth.client/**AccessTokenGrantee** for soliciting access token, validating token and refreshing tokens. 

Also our client side can use the user identity obtained from auth-server to restrict client data using cylon.authentication.protocols/**RequestAuthenticator** protocol. Please be aware that client data is not user data so the way that client authenticate its own data is responsability of client and is outside of OAuht2 spec. Anyway juxt/cylon provides a practical [middleware](https://github.com/juxt/cylon/blob/master/src/cylon/oauth/client.clj#L28) to protect client resources based in the  client-session-store component and the identity obtained once user is logged with the auth-server. You can see how to use it this middleware on the demo project

<br><br><br><hr><br><br><br>
 
### Oauth Provider :authorization-server
```clojure
cylon.oauth.server.server/AuthorizationServer

AuthorizationServer Protocols implemented
;========================================

modular.bidi/WebService (routes):
 + :get "/authorize" 
 + :post "/permit-client" 
 + :post "/access-token" 
    
cylon.authentication.protocols/RequestAuthenticator
(authenticate [_ request]
    "Return (as a map) any credentials that can be determined from the
    given Ring request")

```

TODO: in progress

