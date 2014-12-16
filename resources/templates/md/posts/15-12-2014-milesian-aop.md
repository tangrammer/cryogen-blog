{:title "Apply AOP in stuartsierra/component"
 :layout :post
 :tags  ["milesian" "aop" "stuartsierra-component"]
 :toc true}


[milesian/aop](https://github.com/milesian/aop) clojure library lets you wrap your stuartsierra components in the same way as AOP does. It includes a component/update-system customization function and specific component-matchers.

If you aren't familiar with Aspect Oriented Programming [AOP](http://en.wikipedia.org/wiki/Aspect-oriented_programming) it's a programming paradigme that aims to increase modularity by allowing the separation of cross-cutting concerns. In other practical words/examples with AOP you can plug or unplug transversal functionality (also called aspects or concerns) to your existent code without changing it. Examples of cross-cutting concerns can be: applying security, logging and throwing events.    
As wikipedia says:
> Logging exemplifies a crosscutting concern because a logging strategy necessarily affects every logged part of the system. Logging thereby crosscuts all logged classes and methods....

Your app only needs to define the **thing-to-happen** and the **place-where-will-happen**

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
### Simple Usage to apply AOP to your stuartsierra/component system
```clojure
;;  construct your instance of SystemMap as usual
(def system-map (new-system-map))

;; using milesian/BigBang to customize your system easily
(bigbang/expand system-map
                            {:before-start []
                             :after-start  [[aop/wrap your-fn-middleware]]})

;; Using plain clojure code
(-> your-system-map
    (component/update-system (comp component/start #(milesian.aop/wrap % your-fn-middleware)))
```


### Matchers available in tangrammer/defrecord-wrapper
Due that milesian/aop actually uses [tangrammer/defrecord-wrapper](https://github.com/tangrammer/defrecord-wrapper/), there are a few special matchers  for free that you can be intereseted on using:
+ `nil` value that returns nil
+ `fn` value  that returns itself, (it's a shortcut to apply your-fn-middleware for all fns protocol)
+ `defrecord-wrapper.aop/SimpleProtocolMatcher` that returns your-fn-middleware when the protocol of the fn invoked matchs with any of the the protocols provided




## milesian/aop Let's match with component perspective 

milesian/aop include a [tangramer.defrecord-wrapper/Match](https://github.com/tangrammer/defrecord-wrapper/blob/master/src/defrecord_wrapper/aop.clj#L4-L5) implementation that **use a stuartsierra/component perspective in contrast to** function and protocol perspective of tangrammer/defrecord-wrapper [SimpleProtocolMatcher](https://github.com/tangrammer/defrecord-wrapper/blob/master/src/defrecord_wrapper/aop.clj#L15) implementation.

####  ComponentMatcher 
This implementation  uses the name (system-map key) of the component in the system and try to match using its component protocols.
For example if we take an example from [milesian/system-examples](https://github.com/milesian/system-examples/blob/master/src/milesian/system_examples.clj), the :c component implements Talk protocol, so if we write the following clojure code, all the Talk protocol function implementations of our :c component will be wrapped by [milesian.aop.utils/logging-function-invocation](https://github.com/milesian/aop/blob/master/src/milesian/aop/utils.clj#L20) 

```clojure

;; following milesian/BigBang system start pattern, we need to 
;; include a milesian.aop/wrap action with a matcher 
;; (in this case using ComponentMatcher impl)

 [milesian.aop/wrap (milesian.aop.matchers/new-component-matcher 
                                          :system system-map 
                                          :components [:c] 
                                          :fn milesian.aop.utils/logging-function-invocation)]                                          
```

###  Dependency Component Query Oriented 
This project also contains two ComponentMatcher function constructors that let you query/filter the components to match using a dependency component query point of view. 


#### ComponentTransitiveDependenciesMatcher fn constructor
**[new-component-transitive-dependencies-matcher](https://github.com/milesian/aop/blob/master/src/milesian/aop/matchers.clj#L33)** uses [stuartsierra/dependency transitive-dependencies](https://github.com/stuartsierra/dependency/blob/master/src/com/stuartsierra/dependency.clj#L19)  to get the dependencies fo each component specified in ```:components [...]``` argument , so if you use same example project and changes fn constructor, passing :c component, you'll have matched following components :a :b :c, due that [:c depends on :b and :b depends on :a besides :c also depends on :a](https://github.com/milesian/system-examples/blob/master/src/milesian/system_examples.clj#L45-L50)

```clojure
  (milesian.aop.matchers/new-component-transitive-dependencies-matcher 
                                          :system system-map 
                                          :components [:c] 
                                          :fn milesian.aop.utils/logging-function-invocation)
 ;; it's the same as                                           
 
   (milesian.aop.matchers/new-component-matcher 
                                          :system system-map 
                                          :components [:a :b :c] 
                                          :fn milesian.aop.utils/logging-function-invocation)
 
```

 
#### ComponentTransitiveDependentsMatcher fn constructor
**[new-component-transitive-dependents-matcher](https://github.com/milesian/aop/blob/master/src/milesian/aop/matchers.clj#L40)** uses [stuartsierra/dependency transitive-dependents](https://github.com/stuartsierra/dependency/blob/master/src/com/stuartsierra/dependency.clj#L22) to get the dependents components for each component specified in ```:components [...]``` argument, then if you use same example project and changes fn constructor, passing :a component you'll have matched following components :a :b :c, due that [:c depends on :b and :b depends on :a besides :c also depends on :a](https://github.com/milesian/system-examples/blob/master/src/milesian/system_examples.clj#L45-L50)
```clojure
  (milesian.aop.matchers/new-component-transitive-dependents-matcher 
                                          :system system-map 
                                          :components [:a] 
                                          :fn milesian.aop.utils/logging-function-invocation)
 ;; it's the same as                                           
 
   (milesian.aop.matchers/new-component-matcher 
                                          :system system-map 
                                          :components [:a :b :c] 
                                          :fn milesian.aop.utils/logging-function-invocation)
 
```

