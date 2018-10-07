(ns the-joy-of-clojure.fp-techniques.multimethods-and-udp-test
  (:require [clojure.test :refer :all]
            [the-joy-of-clojure.fp-techniques.multimethods-and-udp :refer :all]))

(deftest beget-test
  (testing "beget"
    (let [proto {:super 1}
          expected {:sub 0, :the-joy-of-clojure.fp-techniques.multimethods-and-udp/prototype proto}
          actual (beget {:sub 0} proto)]
      (is (= expected actual)))))

(deftest get-test
  (testing "get"
    (let [obj (beget {:sub 0} {:super 1})
          expected 1
          actual (get obj :super)]
      (is (= expected actual)))))

(deftest udp-test
  (testing "UDP"
    (let [cat {:likes-dogs true :ocd-bathing true}
          cat-morris (beget {:likes-9lives true} cat)
          post-traumatic-cat-morris (beget {:likes-dogs nil} cat-morris)]
      (is (= true (get cat :likes-dogs)))
      (is (= true (get cat-morris :likes-dogs)))
      (is (= nil (get post-traumatic-cat-morris :likes-dogs)))
      (is (= true (get post-traumatic-cat-morris :likes-9lives))))))

(deftest multimethods-test
  (testing "Multimethods"
    (let [clone (partial beget {})
          unix {:os :the-joy-of-clojure.fp-techniques.multimethods-and-udp/unix
                :c-compiler "cc"
                :home "/home"
                :dev "/dev"}
          osx (-> (clone unix)
                  (put :os :the-joy-of-clojure.fp-techniques.multimethods-and-udp/osx)
                  (put :llvm-compiler "clang")
                  (put :home "/home"))]
      (is (= "cc" (compiler unix)))
      (is (= "clang" (compiler osx))))))