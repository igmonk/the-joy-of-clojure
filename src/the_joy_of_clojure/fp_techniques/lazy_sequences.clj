(ns the-joy-of-clojure.fp-techniques.lazy-sequences)

(defn rec-step [[x & xs]]
  (if x
    [x (rec-step xs)]
    []))

(defn lz-rec-step [s]
  (lazy-seq
    (if (seq s)
      [(first s) (lz-rec-step (rest s))]
      [])))

(defn simple-range [i limit]
  (lazy-seq
    (when (< i limit)
      (cons i (simple-range (inc i) limit)))))
