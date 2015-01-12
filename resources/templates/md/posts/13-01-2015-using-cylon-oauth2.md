{:title "Using juxt/cylon Oauth2"
 :layout :post
 :tags  ["cylon" "oauth2"]
 :toc true}

 
#### how was this [modular-cylon-example](https://github.com/tangrammer/modular-cylon-example) project made?
Firstly, I'd like to clear that this project has been generated using the modular template `bootstrap-cover` following instructions that you can find on [modularity.org](http://modularity.org/). On top of this code I only translate the minimum needed code (mostly authored by [Malcolm Sparks](https://github.com/malcolmsparks)) to get working juxt/cylon oauth2 feature. 


 
## Lets take a look at this demo system

Althouh I've followed (and sometimes directed) this [Oauth2](https://tools.ietf.org/html/rfc6749) implementation, I usually need to visualise the system to think about it, and also in this case I'll show you the system to better understanding

### Step 1  Generate your bootstrap-cover system
Here you go the result of: `$ lein new modular foo bootstrap-cover`


 
[<img src="https://dl.dropboxusercontent.com/u/8688858/cylon-oauth2-example/bootstrap-cover.png" alt="Drawing" style="width: 100%;"/>](https://dl.dropboxusercontent.com/u/8688858/cylon-oauth2-example/bootstrap-cover.png)

As you can see bootstrap-cover template, provides you with one http-listener (modular.http-kit/Webserver) and four webservices (modular.bidi/WebService), all of them are instances of modular.bidi/StaticResourceService (to provide jquery, bootstrap and public resources) except :bootstrap-cover-website-website that makes the dynamic website responses.  
Note that although :modular-bidi-router-webrouter implements WebService too, is modular.ring/WebRequestHandler protocol the requirement for http-listener-listener to use it, so ["... the idea here is that you want individual components to be able to 'contribute' groups of routes"](https://groups.google.com/forum/#!topic/clojure/YP_VM6Zf4RQ).


### Final step :-  Add Oauth2 components
In this final step I added the juxt/cylon components that implements Oauth2 to provide Authorization-Server and Client roles.

**Note that this demo, trying to be simple, uses an atom backed store not intended to be used in production environments (you'd loose all your persistent data each time your app restarts). You can replace that persistence implementation by any one persistence implementations you prefer (for example postgre), only you'll have to implements the require cylon protocol.

You can see last components plus 22 more :) . Don't be afraid I'll work a bit on getting clear this first diagram

[<img src="https://dl.dropboxusercontent.com/u/8688858/cylon-oauth2-example/all.png" alt="Drawing" style="width: 100%;"/>](https://dl.dropboxusercontent.com/u/8688858/cylon-oauth2-example/all.png)

### Now, let's simplify
As I suspect that you're thinking now that OAuth2 is really complex now :), let's split a bit this complex diagram making simplifications to get the simplicity of OAuth2 specification

#### juxt/cylon persistence:  TokenStore and SessionStore protocols

As you can see in the system diagram there're a lot of session-store and token-store components, and obviuosly related with storing data. Following the protocols descriptions:

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

Due that all session-store need a token-store to maintain the related data, let's remove from our graph all those obvius token-store components (highlighted in orange). And the same with listener and router components (hightlighted in yellow)

[<img src="https://dl.dropboxusercontent.com/u/8688858/cylon-oauth2-example/first-step.png" alt="Drawing" width="100%"/>](https://dl.dropboxusercontent.com/u/8688858/cylon-oauth2-example/first-step.png)
<br><br><br><hr><br><br><br>

Now, why don't we remove public static resource services (in yellow) and the clostache-templater (in orange)? ... they actually don't have any relation with Oauth2
[<img src="https://dl.dropboxusercontent.com/u/8688858/cylon-oauth2-example/second-step.png" alt="Drawing" style="width: 100%;"/>](https://dl.dropboxusercontent.com/u/8688858/cylon-oauth2-example/second-step.png)
<br><br><br><hr><br><br><br>

And, one pass more to take away the routers but highlighting only web services, authorization-server web-services in yellow, and web-server web-services in orange. 

[<img src="https://dl.dropboxusercontent.com/u/8688858/cylon-oauth2-example/third-step.png" alt="Drawing" style="width: 100%;"/>](https://dl.dropboxusercontent.com/u/8688858/cylon-oauth2-example/third-step.png)
<br><br><br><hr><br><br><br>

**Yeah, it doesn't hurt now!**    
On authorizarion-listener side (yellow) following webservices: `[:authorization-server, :reset-password, :signup-form :login :logout]` 

On http-listener side (orange) following ones: `[:bootstrap-cover-website-website :webapp-oauth-client]` 


###TODO: full doc In progress
Keep working here in analyzing the no obvious components (oauth2 roles related) :authorization-server and :webapp-oauth-client


