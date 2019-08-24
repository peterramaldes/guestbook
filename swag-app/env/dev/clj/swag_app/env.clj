(ns swag-app.env
  (:require
    [selmer.parser :as parser]
    [clojure.tools.logging :as log]
    [swag-app.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[swag-app started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[swag-app has shut down successfully]=-"))
   :middleware wrap-dev})
