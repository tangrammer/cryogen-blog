{:title "Apply AOP in stuartsierra/component"
 :layout :post
 :tags  ["milesian" "aop" "stuartsierra-component"]
 :toc true}


[milesian/aop](https://github.com/milesian/aop) clojure library lets you wrap your stuartsierra components in the same way as AOP does.

In this project you can find aop actions and component matchers that are thought to fit in milesian/BigBang


To get how this milesian/aop works on stuartsierra components you should start reading the internal tool used tangrammer/defrecord-wrapper due that we are actually wrapping defrecords that will behave as components. 

