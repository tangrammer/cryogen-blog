{:title "Apply AOP in stuartsierra/component"
 :layout :post
 :tags  ["milesian" "aop" "stuartsierra-component"]
 :toc true}


A clojure lib that lets you wrap your stuartsierra/components in the same way as AOP does. 

... for those who aren't familiar with [AOP](http://en.wikipedia.org/wiki/Aspect-oriented_programming), it is a programming paradigme that aims to increase modularity by allowing the separation of cross-cutting concerns. Examples of cross-cutting concerns can be: applying security, logging and throwing events, and as wikipedia explains:
> Logging exemplifies a crosscutting concern because a logging strategy necessarily affects every logged part of the system. Logging thereby crosscuts all logged classes and methods....


### what is it included?
[milesian/aop](https://github.com/milesian/aop) includes a **wrap function** as customization system function and specific **component-matchers** to apply middleware to those components that are matched.

### basic understanding of milesian/aop 
Let's *refactor* for a while some AOP words to easy understand the functionality provided.

+ **thing-to-happen** = aspect/cross-cutting concern
+ **place-where-will-happen** = target


Basically to include a new **thing-to-happen** in your app, you need to define the **thing-to-happen** and the **place-where-will-happen**


### the thing-to-happen :- fn-middleware
The **thing-to-happen** is a function milddleware, very similar to [common ring middleware](https://github.com/ring-clojure/ring/wiki/Concepts#middleware)

```clojure
(defn your-fn-middleware
  [*fn* this & args]
  (let [fn-result (apply *fn* (conj args this))]
   fn-result))
```

### the place-where-will-happen :- defrecord-wrapper.aop/Matcher
The **place-where-will-happen** is calculated with a [Matcher](https://github.com/tangrammer/defrecord-wrapper/blob/master/src/defrecord_wrapper/aop.clj#L4) protocol implementation

```clojure
(defprotocol defrecord-wrapper.aop/Matcher
  (match [this protocol function-name function-args]))
```
As you can see the options available to decide if the **thing** has to happen in current **place** are component protocol, function-name and function-args

For example imagine you have this component :

```clojure
(defprotocol Database
  (save-user [_ user])
  (remove-user [_ user]))

(defprotocol WebSocket
  (send [_ data]))
 
(defrecord YourComponent []
	Database
  	(save-user [this user]
   	 (format "saving user: %" user ))
  	(remove-user [this user]
   	 (format "removing user: %" user ))
	Websocket
  	(send [this data]
   	 (format "sending data: %" data)))
   
```

and you want to match some fns

```clojure
;; maybe all your component fns protocols

(defrecord YourComponentMatcher []
  defrecord-wrapper.aop/Matcher
  (match [this protocol function-name function-args]
   	(contains? #{Database WebSocket} protocol)))

;; or maybe only your Database/remove-user function

(defrecord YourRefinedComponentMatcher []
  defrecord-wrapper.aop/Matcher
  (match [this protocol function-name function-args]
   	(and (= Database protocol) (=function-name "remove-user"))))
```

and you define your loggin AOP middleware to apply

```clojure
(defn logging-middleware
  [*fn* this & args]
  (let [fn-result (apply *fn* (conj args this))]
   (println (meta *fn*))
   fn-result))
```


apply aop to your system => wrap with optional middleware your components
 
```clojure
;;  construct your instance of SystemMap as usual
(def system-map (component/system-map :your-component (YourComponent.)))

;; Using plain clojure code
(-> system-map
    (component/update-system (comp component/start #(milesian.aop/wrap % logging-middleware)))
    
;; if you prefer better way to express previous composition start function
;;  you can use milesian/BigBang too
(bigbang/expand system-map
                {:before-start []
                 :after-start  [[milesian.aop/wrap logging-middleware]]})

```


## Let's match with component perspective 

milesian/aop includes a [Matcher](https://github.com/tangrammer/defrecord-wrapper/blob/master/src/defrecord_wrapper/aop.clj#L4-L5) implementation that **uses a stuartsierra/component perspective in contrast to** function and protocol perspective of [matchers](https://github.com/tangrammer/defrecord-wrapper/blob/master/README.md#matchers-available-in-tangrammerdefrecord-wrapper) included on more generic tangrammer/defrecord-wrapper lib

####  ComponentMatcher 
This implementation  uses the system component id to match using its component protocols.

Example using previous example will match both protocols: Database and Websocket, and therefore all their related fns

```clojure

(milesian.aop.matchers/new-component-matcher :system system-map 
                                             :components [:your-component] 
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

