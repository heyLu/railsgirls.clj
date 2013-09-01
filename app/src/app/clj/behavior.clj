(ns ^:shared app.clj.behavior
    (:require [clojure.string :as string]
              [io.pedestal.app :as app]
              [io.pedestal.app.messages :as msg]))

(defn set-value-transform [old-value message]
  (:value message))

(defn filter-keys [ks m]
  (into {} (filter (fn [[k _]] (contains? ks k)) m)))

(defn add-idea-transform [_old-value idea]
  (filter-keys #{:name :description :picture-url} idea))

(defn change-idea [old-idea new-idea]
  (into old-idea (add-idea-transform nil new-idea)))

(def example-app
  {:version 2
   :transform [[:set-value [:ideas] set-value-transform]
               [:add-idea  [:idea :*] add-idea-transform]
               [:change-idea [:idea :*] add-idea-transform]]
   :emit [[#{[:ideas]} (app/default-emitter [])]
          [#{[:idea :*]} (app/default-emitter [])]]})
