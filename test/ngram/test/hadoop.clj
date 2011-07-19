(ns ngram.test.hadoop
  (:use [ngram.hadoop])
  (:use [midje.sweet])
  (:require [clojure-hadoop.imports :as imp]))

(imp/import-io)


(fact "string-long-writer writes key and value to context"
      (string-long-writer ...context... ...key... ...value...) => anything
      (provided
       (Text. ...key...) => ...text-key...
       (LongWritable. ...value...) => ...long-writable-value...
       (.write ...context... ...text-key... ...long-writable-value...) => nil))
