(ns cryogen.server
  (:require [compojure.core :refer [GET defroutes]]
            [compojure.route :as route]
            [ring.util.response :refer [redirect]]
            [cryogen-core.watcher :refer [start-watcher!]]
            [cryogen-core.compiler :refer [compile-assets-timed read-config]]
            [lein-less.less :as less]
            [me.raynes.fs :as fs]
            ))

(defn init []

  #_(doseq [f ["screen" "bootswatch"]] ;;http://bootswatch.com/readable/
    (less/run-compiler  :javascript
                       {:project-root "resources/templates"
                        :source-path (format "less/%s.less" f)
                        :target-path (format "css/%s.css" f)}))


(compile-assets-timed)



  (start-watcher! "resources/templates" compile-assets-timed))

(defroutes handler
  (GET "/" [] (redirect (str (:blog-prefix (read-config)) "/index.html")))
  (route/resources "/")
  (route/not-found "Page not found"))
