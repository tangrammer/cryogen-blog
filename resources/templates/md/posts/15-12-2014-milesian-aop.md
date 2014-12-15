{:title "Apply AOP in stuartsierra/component"
 :layout :post
 :tags  ["milesian" "aop" "stuartsierra-component"]
 :toc true}


[milesian/aop](https://github.com/milesian/aop) clojure library lets you wrap your stuartsierra components in the same way as AOP does.

If you aren't familiar with Aspect Oriented Programming [AOP](http://en.wikipedia.org/wiki/Aspect-oriented_programming) it's a programming paradigme that aims to increase modularity by allowing the separation of cross-cutting concerns. In other practical words/examples with AOP you can plug or unplug features/aspects (like apply-security or print-logging or throw-events) to your existent code without changing it. Your app only defines the thing-to-happen and the place-where-will-happen.

In milesian/aop world the thing-to-happen is a milddleware fn and the place-where-will-happen is calculated with a [Match](https://github.com/tangrammer/defrecord-wrapper/blob/master/src/defrecord_wrapper/aop.clj#L4) protocol implementation

### thing-to-happen :- your-fn-middleware
```clojure
(defn your-fn-middleware
  [*fn* this & args]
  (let [fn-result (apply *fn* (conj args this))]
   fn-result))
```

### place-where-will-happen :- your-match
```clojure
(defprotocol defrecord-wrapper.aop/Matcher
  (match [this protocol function-name function-args]))
```



In this project you can find aop actions and component matchers that are thought to fit in milesian/BigBang


To get how this milesian/aop works on stuartsierra components you should start reading the internal tool used tangrammer/defrecord-wrapper due that we are actually wrapping defrecords that will behave as components. 

