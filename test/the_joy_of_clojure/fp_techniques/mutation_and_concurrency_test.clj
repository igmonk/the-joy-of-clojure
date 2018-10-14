(ns the-joy-of-clojure.fp-techniques.mutation-and-concurrency-test
  (:require [clojure.test :refer :all]
            [the-joy-of-clojure.fp-techniques.mutation-and-concurrency :refer :all]))

(deftest manipulable-memoize-test
  (testing "manipulable-memoize"
    (let [slowly (fn [x] (Thread/sleep 1000) x)
          slowly-actual (time [(slowly 9) (slowly 9)]) ; "Elapsed time: 2005.489111 msecs"
          sometimes-slowly (manipulable-memoize slowly)
          sometimes-slowly-actual (time [(sometimes-slowly 100) (sometimes-slowly 100)]) ; "Elapsed time: 1005.455439 msecs"
          cache (:cache (meta sometimes-slowly))
          cache-result (swap! cache dissoc '(100))]
      (is (= [9 9] slowly-actual))
      (is (= [100 100] sometimes-slowly-actual))
      (is (= {} cache-result)))))
