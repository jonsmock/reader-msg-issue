(ns immutant.init
  (:require [clojure.tools.logging :refer (info)]
            [clj-time.core :as t]
            [immutant.messaging :as msg]
            [reader-msg-issue.core :as acme-core]))

(info "The data-readers are: " (pr-str *data-readers*))

(info "Edn encoded: " (pr-str (t/now)))
(info "Roundtripping: " (read-string (pr-str (t/now))))
(info "And the class is: " (class (read-string (pr-str (t/now)))))

(info "Start the queue")
(msg/start "queue.jons")

(info "Publish a joda time")
(msg/publish "queue.jons" (t/now))

(info "Attempt to receive it...")
(msg/receive "queue.jons")
