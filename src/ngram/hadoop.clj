(ns ngram.hadoop
  (:use ngram.core)
  (:require [clojure-hadoop.wrap :as wrap]
            [clojure-hadoop.defjob :as defjob]
            [clojure-hadoop.imports :as imp]))

(imp/import-io)
(imp/import-mapreduce)

(defjob/defjob job
  :map my-map
  :map-reader wrap/int-string-map-reader
  :map-writer nil
  :reduce my-reduce
  :reduce-reader nil
  :reduce-writer nil
  :output-key Text
  :output-value LongWritable
  :input-format :text
  :output-format :text
  :compress-output false)
