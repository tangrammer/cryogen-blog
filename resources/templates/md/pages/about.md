{:title "@tangrammer"
 :layout :page
 :page-index 0
 :navbar? true}


@tangrammer is also called Juan Antonio Ruz. He found that playing with functional programming could be so funny like Arts, thus he changed the mutable objects by pure functions. Nowadays is difficult talk to him without some jokes about good clojure references. Currently is 41 years old and lives in Sevilla (Spain), this is his very summarised experience from 2006, thanks for reading!


---
**2017-01 / now **   
**ride-on.es**   

---

[RIDE ON](http://www.ride-on.es/en/) "Shared mobility systems" Spanish company based in Madrid

First oportunity to telework as a clojure engineer for a spanish company! Times moving :)

This time, I had to take the handover of a great juxt.pro developed backend project. So far, is being great although I have to deal with a complex async system.

More info [here](https://juxt.pro/blog/posts/rideon.html)





---
**2016-06 / 2016-07**   
**swarmloyalty.co.za**   

---

[SWARM LOYALTY](https://swarmloyalty.co.za) South African company based in Cape Town 

Teleworking as a clojure introductor and main designer and developer for a new backend API that would mirror Star Bucks API in this world region

Two high productivity months to leave the project in a consistent state to be fully delivered in the following months



---
**2015-03 / 2016-05**   
**deep-impact.ch**   

---

I teleworked with [DEEP-IMPACT-AG](http://deepimpact.ch/) a swiss company based in Winterthur that defines itself as a web passionate.

They wanted me as clojure introductor in the company so i was charged to migrate legacy projects and to define new ones. Also I was frequently required to develop in java and javascript/react_js 

Following are some of the clojure libs that we fortunately used:
* [datomic](http://www.datomic.com/)
* [aleph](https://github.com/ztellman/aleph)
* [yada](https://github.com/juxt/yada)
* [component](https://github.com/stuartsierra/component)
* [modular](https://github.com/juxt/modular)
* [yesql](https://github.com/krisajenkins/yesql)
* [graph](https://github.com/plumatic/plumbing)
* [schema](https://github.com/plumatic/schema)

I took the advantage of working closely with APIs to write an interesting lib to deal with the logic flow of controller fns [flowgic](https://github.com/DEEP-IMPACT-AG/flowgic)



Definitely, I enjoyed a lot this company, the great team and its web philosophy focused in user experience and performance. 



---
**2014-06 / 2014-12**   
**juxt.pro**   

---
I had the chance to telework with [JUXT](https://github.com/juxt/) (one of the most important  european clojurian companies based in London). During this time I worked with one of the founders [Malcolm Sparks](https://github.com/malcolmsparks) making contributions to:  

*  [juxt/cylon](https://github.com/juxt/cylon): designing and developing the TOTP two-factor authentication support, conducting the background research and co-authoring the OAuth2 support. 

* [juxt/modular](https://github.com/juxt/modular): adding [less module](https://github.com/juxt/modular/tree/master/modules/less) and solving [co-dependency](https://github.com/tangrammer/co-dependency) pattern in [stuartsierra/component](https://github.com/stuartsierra/component) library to help in some communication issues as, for example,  bidi.Router < - - > bidi.WebService relation

Working in customizing systems I was also interested in getting real time visualization of component system calls. As a result of this research I wrote:

* [milesian/BigBang](https://github.com/milesian/BigBang) decomplect your stuartsierra customization system from component/start
* [milesian/identity](https://github.com/milesian/identity) component self reflection
* [milesian/aop](https://github.com/milesian/aop) uses AOP in stuartsierra/component systems
* [milesian/system-diagrams](https://github.com/milesian/system-diagrams) Web client for real time system visualisations. Here you can see a [picture](https://camo.githubusercontent.com/282505e0818069e3871986cd00ef7513a0272f82/68747470733a2f2f646c2e64726f70626f7875736572636f6e74656e742e636f6d2f752f383638383835382f67726170685f7365712e706e67) and a [video](https://vimeo.com/114150238) , (I only clicked on opensensors.io login button and after getting the visualisations I sent the login form.) that shows the final result of real time visualization of component system calls


Some of the libraries that I had to work with in my juxt job were: stuartsierra/component, postgresql/postgresql, clojurewerkz/cassaforte, clj-time, liberator, prismatic/schema, prismatic/plumbing, core.async, bidi, clostache, cylon, joplin, clojurescript, om


---

**2014-06 / 2014-12**   
**opensensors.io**   

---
As juxter developer I usually had to colaborate with  [opensensors.io](https://opensensors.io/), a scalable real time IoT messaging engine that can easily process millions of messages a second from any internet connected device. This project was co-directed by: [Malcolm Sparks](https://github.com/malcolmsparks), [Michael Klishin](https://github.com/michaelklishin) and [Yodit Stanton](https://github.com/yods). 

---

**2012 / 2013**   
**enterpriseweb.com**   

---
I was teleworking  with [enterpriseweb.com](http://enterpriseweb.com/) a New Yorker company doing a lot of js (nodejs, requirejs, jquery, jasmine, D3 ...), clojure and clojurescript. During this time I had to get familiar with big data concepts and concurrent patterns. That's the reasong that I have some background on hadoop, zookeeper, solr, mongodb, accumulo and related clojure libs (liebke/zookeeper-clj, liebke/avout, nathanmarz/cascalog, alexott/clojure-hadoop). In this time  I wrote some clojure code: [a wrapper on top of Open Stack](https://github.com/tangrammer/open-stack-wrapper) , a [library to work with snmp4j](https://github.com/tangrammer/ew_snmp) , and a [open-stack Om client](https://github.com/tangrammer/open-stack-ui).

On this time I worked with core.async, core.logic, and swannodette/om. I attach some video examples to show superficially my interest on clojurescript and client side development.

This [video](https://vimeo.com/87557206) shows my own solution to achieve brepl live editing behavior, the same as clojurescriptone,  using the React/OM framework. The pattern used here communicates the components, in a core.async/CSP manner, putting a map of channels into the component channel. Using this pattern, the developer  can mix mock with real data and move between the different states of the app, only reevaluating the code on emacs (brepl + cemerick/austin)

And this other [video](http://vimeo.com/89089056) shows a previous js experiment project that was built thinking on async compose processes (pipelines), events and aspects. This framework lets swap coding (hot reloading code without browser reload) in dev mode. This feature is built on top of requirejs, socket.io and nodemon. The AOP/EOP orientation of this framework lets you easy filter the internal processes(pipelines) to make logging on the terminal, profiling or doing d3 process [visualisations](https://vimeo.com/92518892) ([static image](/css/images/desktop.png))

---

**2011 / 2013**   
**RoR/js**   

---

Looking forward to do a bit creative and experimental programming I decided to migrate to dynamic typed languages (Ruby and javaScript) and data visualization (processing.org, openlayers, d3.js) lands. Some examples: [1](https://www.flickr.com/photos/codigodelaimagen/sets/72157627936252594/) [2](https://vimeo.com/35173992) [3](https://vimeo.com/30575630)

---

**2012 / now**   
**js/Clojure/ClojureScript**   

---

I discovered clojure with [quil](https://github.com/quil/quil) (clojure on top of [processing.org](http://processing.org/)). From then, my fascination about its features and the clojure community never ends.



---

**2006 / 2012**   
**JEE**   

---

During this time I was highly focused in JEE, working on companies (Hewlett Packard - Senior Programmer) and as a freelance developer and consultant. I worked with  patterns as MVC, ORM and DI implemented on different ways (Struts, JSF, EJB3, Hibernate, Spring, Seam,  ...). From my java beginning, I always tried to develop with TDD and XP methodologies and was very passionate in Event and Aspect Oriented Programming. I worked as JavaEE teacher too, on private and public companies (+ 1500 Hours)




---
---


Here you can find full details: [linked-in](http://es.linkedin.com/pub/juan-antonio-ruz-velasco/17/704/b65)

And my [4clojure profile](http://www.4clojure.com/user/jaruz), still in progress
