(ns ngram.core
  (:require [clojure.contrib.str-utils2 :as str2]))

(defn ngrams [n s]
  (when (> n 0)
    (let [padding (str2/repeat " " (- n 1))]
      (map #(apply str %)
           (partition n 1 (str padding s padding))))))

(defn my-map [key value]
  (map vector
       (ngrams 3 value)
       (repeat 1)))

(defn my-reduce [key value-fn]
  [[key (reduce + (value-fn))]])
