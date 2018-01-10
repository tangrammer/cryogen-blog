# In progress - Starting here, doc not ready 
 
### Oauth2 Authorization Code Flow

[RFC 6749#Authorization Code Grant](https://tools.ietf.org/html/rfc6749#section-1.2)

![image](/css/images/on-the-clojure-move/auth-code-flow.png)


### Oauth2 Protocol Flow
[RFC 6749#Protocol flow](https://tools.ietf.org/html/rfc6749#section-1.2)

![image](/css/images/on-the-clojure-move/oauth2-protocol-flow.png)

 

# Oauth2 cylon implementation

## Client 
Reference implementation `cylon.oauth.client.web-client/WebClient`


```clojure

Protocols
=========

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

## auth-server
```clojure
cylon.oauth.server.server/AuthorizationServer

Protocols
=========

modular.bidi/WebService (routes):
 + :get "/authorize" 
 + :post "/permit-client"
 + :post "access-token" 
    
cylon.authentication.protocols/RequestAuthenticator
(authenticate [_ request]
    "Return (as a map) any credentials that can be determined from the
    given Ring request")

```

## resource-server
```clojure
cylon.oauth.server.server/AuthorizationServer

Protocols
=========

modular.bidi/WebService (routes):
 + ... any api definition 
    
cylon.authentication.protocols/RequestAuthenticator
(authenticate [_ request]
    "Return (as a map) any credentials that can be determined from the
    given Ring request")



```


## cylon authentication
Due that authentication is beyond Oauth2 scope: [RFC 6749#Authorization Endpoint](https://tools.ietf.org/html/rfc6749#section-3.1)

>The way in which the authorization server authenticates the resource owner
   (e.g., username and password login, session cookies) is beyond the
   scope of this specification.

Cylon provided a authentication set of protocols and reference implementations   h 
