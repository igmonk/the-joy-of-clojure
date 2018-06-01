(ns the-joy-of-clojure.fp-techniques.macros-test
  (:require [clojure.test :refer :all]
            [the-joy-of-clojure.fp-techniques.macros :refer :all]))

(deftest infix-test
  (testing "infix"
    (let [actual (infix (1 + 2))
          expected 3]
      (is (= expected actual)))))

(deftest infix-2-test
  (testing "infix-2"
    (let [actual (infix-2 (10 + 20))
          expected 30]
      (is (= expected actual)))))
