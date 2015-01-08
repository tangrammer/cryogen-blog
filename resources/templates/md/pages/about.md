{:title "@tangrammer"
 :layout :page
 :page-index 0
 :navbar? true}


Hello, my name is Juan Antonio Ruz, I'm 38 years old, live in Spain and in a self-taught way I started to develop more than 10 years ago ...

This is my very summarised experience until 2006:

---
**2014-06 / 2014-12**   
**juxt.pro**   

---
I had the chance to telework with [JUXT](https://github.com/juxt/) (a Londoner company, one of the most important clojurian  european companies). During this time I worked very closed with one of the founders [Malcolm Sparks](https://github.com/malcolmsparks) making serious contributions to [juxt/cylon](https://github.com/juxt/cylon), [juxt/modular](https://github.com/juxt/cylon). Also I was working in customizing [stuartsierra/component](https://github.com/stuartsierra/component) systems. Two reasons took me there, firstly co-dependency was needed to refine juxt/modular design and the other reason was my interest in getting real time visualization of component system calls. As a result of this research I've written a few tools and libs that I detail below:

* [tangrammer/co-dependency](https://github.com/tangrammer/co-dependency) lets you use Reverse Dependency Injection
* [milesian/BigBang](https://github.com/milesian/BigBang) compose component/update(s)-system in component/start invocation time
* [milesian/aop](https://github.com/milesian/aop) uses AOP in stuartsierra/component systems


Here you can see a [picture](https://camo.githubusercontent.com/282505e0818069e3871986cd00ef7513a0272f82/68747470733a2f2f646c2e64726f70626f7875736572636f6e74656e742e636f6d2f752f383638383835382f67726170685f7365712e706e67) and a [video](https://vimeo.com/114150238) , (I only clicked on opensensors.io login button and after getting the visualisations I sent the login form.) that shows the final result of real time visualization of component system calls

---

**2014-06 / 2014-12**   
**opensensors.io**   

---
As juxter developer I usually had to colaborate with  [opensensors.io](https://opensensors.io/), a Londoner start-up about IoT composed by: [Malcolm Sparks](https://github.com/malcolmsparks), [Michael Klishin](https://github.com/michaelklishin) and [Yodit Stanton](https://github.com/yods).

---

**2012 / 2013**   
**enterpriseweb.com**   

---
I was teleworking  with [enterpriseweb.com](http://enterpriseweb.com/) a New Yorker company doing a lot of js (nodejs, requirejs, jquery, jasmine, D3 ...), clojure and clojurescript. During this time I had to get familiar with big data concepts and concurrent patterns. That's the reasong that I have some background on hadoop, zookeeper, solr, mongodb, accumulo and related clojure libs (liebke/zookeeper-clj, liebke/avout, nathanmarz/cascalog, alexott/clojure-hadoop). In this time  I wrote some clojure code: [a wrapper on top of Open Stack](https://github.com/tangrammer/open-stack-wrapper) , a [library to work with snmp4j](https://github.com/tangrammer/ew_snmp) , and a [open-stack Om client](https://github.com/tangrammer/open-stack-ui).

On this time I worked with core.async, core.logic, and swannodette/om. I attach some video examples to show superficially my interest on clojurescript and client side development.

This [video](http://vimeo.com/89089056) shows my own solution to achieve brepl live editing behavior, the same as clojurescriptone,  using the React/OM framework. The pattern used here communicates the components, in a core.async/CSP manner, putting a map of channels into the component channel. Using this pattern, the developer  can mix mock with real data and move between the different states of the app, only reevaluating the code on emacs (brepl + cemerick/austin)

And this other [video](http://vimeo.com/89089056) shows a previous js experiment project that was built thinking on async compose processes (pipelines), events and aspects. This framework lets swap coding (hot reloading code without browser reload) in dev mode. This feature is built on top of requirejs, socket.io and nodemon. The AOP/EOP orientation of this framework lets you easy filter the internal processes(pipelines) to make logging on the terminal, profiling or doing d3 process [visualisations](https://dl.dropboxusercontent.com/u/8688858/folding_unfolding_pipelines.mov) ([static image](https://dl.dropboxusercontent.com/u/8688858/desktop.png))

---

**2011 / 2013**   
**RoR/js**   

---

Looking forward to do a bit creative and experimental programming I decided to migrate to dynamic languages (RoR, js) and data visualization (processing.org, openlayers, d3.js) lands

---

**2012 / now**   
**js/Clojure/ClojureScript**   

---

I discovered clojure with [quil](https://github.com/quil/quil) (clojure on top of [processing.org](http://processing.org/)). From then, my fascination about its features and the clojure community never ends.


---

**2016 / 2012**   
**JEE**   

---

During this time I was highly focused in JEE, working on companies (Hewlett Packard - Senior Programmer) and as a freelance developer and consultant. I worked with  patterns as MVC, ORM and DI implemented on different ways (Struts, JSF, EJB3, Hibernate, Spring, Seam,  ...). From my java beginning, I always tried to develop with TDD and XP methodologies and was very passionate in Event and Aspect Oriented Programming. I worked as JavaEE teacher too, on private and public companies (+ 1500 Hours)




---
---


Here you can find full details: [linked-in](http://es.linkedin.com/pub/juan-antonio-ruz-velasco/17/704/b65)

And my [4clojure profile](http://www.4clojure.com/user/jaruz), still in progress
