(ns app.clj.html-templates
  (:use [io.pedestal.app.templates :only [tfn dtfn tnodes]]))

(defmacro app.clj-templates
  []
  {:app.clj-page (dtfn (tnodes "app.clj.html" "hello") #{:id})})

;; Note: this file will not be reloaded automatically when it is changed.
