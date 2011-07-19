(ns ngram.hadoop
  (:use ngram.core)
  (:require [clojure-hadoop.wrap :as wrap]
            [clojure-hadoop.defjob :as defjob]
            [clojure-hadoop.imports :as imp]))

(imp/import-io)
(imp/import-mapreduce)

; these readers and writers need tests!
(defn string-long-reduce-reader [^Text key wvalues]
  [(.toString key)
   (fn [] (map (fn [^LongWritable v] (.get v))
               (seq wvalues)))])

(defn string-long-writer [^TaskInputOutputContext context key value]
  (.write context (Text. key) (LongWritable. value)))

(defjob/defjob job
  :map my-map
  :map-reader wrap/int-string-map-reader
  :map-writer string-long-writer
  :reduce my-reduce
  :reduce-reader string-long-reduce-reader
  :reduce-writer string-long-writer
  :output-key Text
  :output-value LongWritable
  :input-format :text
  :output-format :text
  :compress-output false)
