{:title "Apply AOP in stuartsierra/component"
 :layout :post
 :tags  ["milesian" "aop" "stuartsierra-component"]
 :toc true}


[milesian/aop](https://github.com/milesian/aop) clojure library lets you wrap your stuartsierra components in the same way as AOP does. It includes system customization actions and component matchers.

If you aren't familiar with Aspect Oriented Programming [AOP](http://en.wikipedia.org/wiki/Aspect-oriented_programming) it's a programming paradigme that aims to increase modularity by allowing the separation of cross-cutting concerns. In other practical words/examples with AOP you can plug or unplug transversal functionality (also called aspects or concerns) to your existent code without changing it. Examples of cross-cutting concerns can be: applying security, logging and throwing events. 

Your app only defines the **thing-to-happen** and the **place-where-will-happen**

In milesian/aop world the thing-to-happen is a milddleware fn and the place-where-will-happen is calculated with a [Match](https://github.com/tangrammer/defrecord-wrapper/blob/master/src/defrecord_wrapper/aop.clj#L4) protocol implementation

### the thing-to-happen :- your-fn-middleware
```clojure
(defn your-fn-middleware
  [*fn* this & args]
  (let [fn-result (apply *fn* (conj args this))]
   fn-result))
```

### the place-where-will-happen :- your-match
```clojure
(defprotocol defrecord-wrapper.aop/Matcher
  (match [this protocol function-name function-args]))
```
### Matchers available in tangrammer/defrecord-wrapper
Due that milesian/aop actually uses [tangrammer/defrecord-wrapper](https://github.com/tangrammer/defrecord-wrapper/), there are a few special matchers  for free that you can be intereseted on using:
+ `nil` value that returns nil
+ `fn` value  that returns itself, (it's a shortcut to apply your-fn-middleware for all fns protocol)
+ `defrecord-wrapper.aop/SimpleProtocolMatcher` that returns your-fn-middleware when the protocol of the fn invoked matchs with any of the the protocols provided

### Matchers available in milesian/aop
In milesian/aop you can find  that are thought to fit in milesian/BigBang



