(ns the-joy-of-clojure.fp-techniques.protocols-test
  (:require [clojure.test :refer :all]
            [the-joy-of-clojure.fp-techniques.protocols :refer :all]
            [the-joy-of-clojure.fp-techniques.records :refer :all])
  (:import [the_joy_of_clojure.fp_techniques.protocols TreeNode2]))

(deftest fixo-test-tree
  (testing "TreeNode"
    (let [tree (reduce fixo-push nil [3 5 2 4 6 0])
          actual (xseq tree)
          expected [0 2 3 4 5 6]]
      (is (= expected actual))
      (is (= 0 (fixo-peek tree))))))

(deftest fixo-test-vector
  (testing "IPersistentVector"
    (let [actual (fixo-push [2 3 4 5 6] 5/2)
          expected [2 3 4 5 6 5/2]]
      (is (= expected actual)))))

(deftest fixo-test-tree-2
  (testing "fixo-test-tree-2"
    (let [tree (reduce fixo-push (TreeNode2. 3 nil nil) [5 2 4 6])
          actual (xseq tree)
          expected [2 3 4 5 6]]
      (is (= expected actual)))))
