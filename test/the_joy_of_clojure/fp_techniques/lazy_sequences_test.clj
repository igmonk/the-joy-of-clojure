(ns the-joy-of-clojure.fp-techniques.lazy-sequences-test
  (:require [clojure.test :refer :all]
            [the-joy-of-clojure.fp-techniques.lazy-sequences :refer :all]))

(deftest rec-step-test
  (testing "rec-step"
    (let [actual (rec-step [1 2 3 4])
          expected [1 [2 [3 [4 []]]]]]
      (is (= expected actual)))))

(deftest lz-rec-step-test
  (testing "lz-rec-step"
    (let [actual (lz-rec-step [1 2 3 4])
          expected (list 1 (list 2 (list 3 (list 4 (list)))))]
      (is (= expected actual)))))

(deftest simple-range-test
  (testing "simple-range"
    (let [actual (simple-range 0 9)
          expected (list 0 1 2 3 4 5 6 7 8)]
      (is (= expected actual)))))

(deftest fib-test
  (testing "fib"
    (let [actual (take 10 (fib))
          expected (list 1 1 2 3 5 8 13 21 34 55)]
      (is (= expected actual)))))
