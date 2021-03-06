(defproject tangrammer/cryogen-blog "0.1.0"
            :description "Simple static site generator"
            :url "https://github.com/lacarmen/cryogen"
            :license {:name "Eclipse Public License"
                      :url "http://www.eclipse.org/legal/epl-v10.html"}
            :dependencies [[org.clojure/clojure "1.6.0"]
                           [markdown-clj "0.9.58"
                            :exclusions [com.keminglabs/cljx]]
                           [ring/ring-devel "1.3.2"]
                           [compojure "1.2.2"]
                           [ring-server "0.3.1"]
                           [cryogen-core "0.1.1"]
;                           [tangrammer/lein-less "1.7.3"]
                           ]
            :plugins [[lein-ring "0.8.13"]]
            :resource-paths ["resources"]
            :main cryogen.compiler
            :ring {:init cryogen.server/init
                   :handler cryogen.server/handler})
