(ns the-joy-of-clojure.fp-techniques.thinking-recursively)

(defn pow [base exp]
  (if (zero? exp)
    1
    (* base (pow base (dec exp)))))

(defn pow-2 [base exp]
  (letfn [(kapow [base exp acc]
            (if (zero? exp)
              acc
              (recur base (dec exp) (* base acc))))]
    (kapow base exp 1)))
