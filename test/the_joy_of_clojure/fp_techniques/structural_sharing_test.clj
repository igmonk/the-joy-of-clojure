(ns the-joy-of-clojure.fp-techniques.structural-sharing-test
  (:require [clojure.test :refer :all]
            [the-joy-of-clojure.fp-techniques.structural-sharing :refer :all]))

(deftest xconj-test
  (testing "xconj"
    (testing "t = nil, v = 5"
      (let [actual (xconj nil 5)
            expected {:val 5 :L nil :R nil}]
        (is (= expected actual))))

    (testing "t is not nil, root val = 5, v = 3"
      (let [tree1 {:val 5 :L nil :R nil}
            tree2 (xconj tree1 3)]
        (is (= {:val 5 :L nil :R nil} tree1))
        (is (= {:val 5
                :L {:val 3
                    :L nil
                    :R nil}
                :R nil} tree2))))

    (testing "t is not nil, root val = 5, v = 3, 2, 7"
      (let [tree1 {:val 5 :L nil :R nil}
            tree2 (xconj tree1 3)
            tree3 (xconj tree2 2)
            actual (xconj tree3 7)
            expected {:val 5
                      :L {:val 3
                          :L {:val 2
                              :L nil
                              :R nil}
                          :R nil}
                      :R {:val 7
                          :L nil
                          :R nil}}]
        (is (= expected actual))))))

(deftest xseq-test
  (testing "xseq"
    (testing "nil"
      (let [actual (xseq nil)]
        (is (= nil actual))))

    (testing "2, 3, 5, 7"
      (let [tree {:val 5
                  :L {:val 3
                      :L {:val 2
                          :L nil
                          :R nil}
                      :R nil}
                  :R {:val 7
                      :L nil
                      :R nil}}
            actual (xseq tree)
            expected '(2 3 5 7)]
        (is (= expected actual))))))
