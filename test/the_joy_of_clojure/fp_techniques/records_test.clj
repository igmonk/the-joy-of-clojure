(ns the-joy-of-clojure.fp-techniques.records-test
  (:require [clojure.test :refer :all]
            [the-joy-of-clojure.fp-techniques.records :refer :all]))

(deftest xconj-xseq-test
  (testing "xconj-xseq"
    (let [tree (reduce xconj nil [3 5 2 4 6])
          actual (xseq tree)
          expected [2 3 4 5 6]]
      (is (= expected actual)))))
