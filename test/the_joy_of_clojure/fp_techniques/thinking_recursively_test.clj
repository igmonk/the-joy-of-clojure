(ns the-joy-of-clojure.fp-techniques.thinking-recursively-test
  (:require [clojure.test :refer :all]
            [the-joy-of-clojure.fp-techniques.thinking-recursively :refer :all]))

(deftest pow-test
  (testing "pow"
    (let [actual (pow 2 10)
          expected 1024]
      (is (= expected actual)))))

(deftest pow-2-test
  (testing "pow-2"
    (let [actual (pow-2 2 10)
          expected 1024]
      (is (= expected actual)))))
