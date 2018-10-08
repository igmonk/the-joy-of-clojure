(ns the-joy-of-clojure.fp-techniques.records)

; This creates a new Java class with a constructor that takes a value for each of the fields listed.
; It also imports that class into current namespace (but not in any other namespace).

(defrecord TreeNode [val l r])

(defn xconj [t v]
  (cond
    (nil? t) (TreeNode. v nil nil)
    (< v (:val t)) (TreeNode. (:val t) (xconj (:l t) v) (:r t))
    :else (TreeNode. (:val t) (:l t) (xconj (:r t) v))))

(defn xseq [t]
  (when t
    (concat (xseq (:l t))
            [(:val t)]
            (xseq (:r t)))))
