(ns guestbook.routes.home
  (:require
    [guestbook.layout :as layout]
    [guestbook.db.core :as db]
    [clojure.java.io :as io]
    [guestbook.middleware :as middleware]
    [ring.util.http-response :as response]
    [struct.core :as st]))

(def message-schema
  [[:name
    {:message  "O nome é obrigatório"
     :validate st/required}
    st/string]

   [:message
    {:message  "A mensagem é obrigatória"
     :validate st/required}
    st/string
    {:message  "A mensagem precisa conter ao menos 10 caracteres."
     :validate #(> (count %) 9)}]])

(defn validate-message [params]
  (first (st/validate params message-schema)))

(defn save-message! [{:keys [params]}]
  (if-let [errors (validate-message params)]
    (-> (response/found "/")
        (assoc :flash (assoc params :errors errors)))
    (do
      (db/save-message!
        (assoc params :timestamp (java.util.Date.)))
      (response/found "/"))))

(defn home-page [{:keys [flash] :as request}]
  (layout/render
    request
    "home.html"
    (merge {:messages (db/get-messages)}
           (select-keys flash [:name :message :errors]))))

(defn about-page [request]
  (layout/render request "about.html"))

(defn home-routes []
  [""
   {:middleware [middleware/wrap-csrf
                 middleware/wrap-formats]}
   ["/" {:get  home-page
         :post save-message!}]
   ["/about" {:get about-page}]])

