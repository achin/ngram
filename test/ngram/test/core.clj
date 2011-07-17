(ns ngram.test.core
  (:use [ngram.core])
  (:use [midje.sweet]))

(fact "ngrams can generate basic values"
      (ngrams 1 "abc") => '("a" "b" "c")
      (ngrams 2 "abc") => '(" a" "ab" "bc" "c ")
      (ngrams 3 "abcde") => '("  a" " ab" "abc" "bcd" "cde" "de " "e  "))

(fact "ngrams doesn't do anything if n is zero"
      (ngrams 0 ...anything...) => nil)

(fact "my-map uses ngrams to make 3-grams and returns list of (3-gram, 1) pairs"
      (my-map ...key... ...value...) => [["  a" 1]
                                         [" ab" 1]
                                         ["abc" 1]
                                         ["bc " 1]
                                         ["c  " 1]]
      (provided
       (ngrams 3 ...value...) => '("  a" " ab" "abc" "bc " "c  ")))

(fact "integration test: my-map"
      (my-map ...key... "hello") => [["  h" 1]
                                     [" he" 1]
                                     ["hel" 1]
                                     ["ell" 1]
                                     ["llo" 1]
                                     ["lo " 1]
                                     ["o  " 1]])

(fact "my-reduce sums values and returns list of pairs"
      (let [value-fn (fn [] '(1 2 3))]
        (my-reduce ...ngram... value-fn)) => [[...ngram... 6]])
