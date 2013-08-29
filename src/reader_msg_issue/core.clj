(ns reader-msg-issue.core
  (:require [clj-time.format :as tf])
  (:import [org.joda.time DateTime Interval]))

;; ## For Clojure Reader

(def ^:private reader-datetime-formatter (tf/formatters :rfc822))

(defn from-edn-datetime
  [dt]
  (tf/parse reader-datetime-formatter dt))

(defmethod print-method DateTime [o ^java.io.Writer w]
  (let [prefix "#acme/datetime"
        dt     (tf/unparse reader-datetime-formatter o)]
    (.write w (str prefix " \"" dt "\""))))
