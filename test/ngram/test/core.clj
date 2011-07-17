(ns ngram.test.core
  (:use [ngram.core])
  (:use [midje.sweet]))

(fact "ngrams can generate basic values"
      (ngrams 1 "abc") => '("a" "b" "c")
      (ngrams 2 "abc") => '(" a" "ab" "bc" "c ")
      (ngrams 3 "abcde") => '("  a" " ab" "abc" "bcd" "cde" "de " "e  "))

(fact "ngrams doesn't do anything if n is zero"
      (ngrams 0 ...anything...) => nil)
