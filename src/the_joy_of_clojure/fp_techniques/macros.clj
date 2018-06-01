(ns the-joy-of-clojure.fp-techniques.macros)

(defmacro infix
  "infix macro doc"
  [infixed]
  (list (second infixed) (first infixed) (last infixed)))

(defmacro infix-2
  "infix-2 macro doc"
  [[operand1 op operand2]]
  (list op operand1 operand2))

(defmacro unless [condition & body]
  `(if (not ~condition)
     (do ~@body)))

(defmacro unless-2
  "Inverted IF"
  [test & branches]
  (conj (reverse branches) test 'if))
