(ns swag-app.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[swag-app started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[swag-app has shut down successfully]=-"))
   :middleware identity})
