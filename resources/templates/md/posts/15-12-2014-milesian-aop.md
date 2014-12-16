{:title "Apply AOP in stuartsierra/component"
 :layout :post
 :tags  ["milesian" "aop" "stuartsierra-component"]
 :toc true}


[milesian/aop](https://github.com/milesian/aop) clojure library lets you wrap your stuartsierra components in the same way as AOP does.

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

Pay attention that defrecord-wrapper.aop ns there are a few special matchers implentation included, nil value that returns nil, the your-fn-middleware itself that returns to itself, and a SimpleProtocolMatcher that returns the your-fn-middleware when the fn invoked belongs to protocol wich matchs the protocols provide:

```clojure
(defrecord-wrapper.aop/new-simple-protocol-matcher :fn your-fn-middleware :protocols [Protocol-1 Protocol-2 Protocol-3])
```

In this project you can find aop actions and component matchers that are thought to fit in milesian/BigBang


To get how this milesian/aop works on stuartsierra components you should start reading the internal tool used tangrammer/defrecord-wrapper due that we are actually wrapping defrecords that will behave as components. 

