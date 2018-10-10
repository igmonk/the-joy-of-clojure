(ns the-joy-of-clojure.fp-techniques.protocols
  (:require [the-joy-of-clojure.fp-techniques.records :refer :all])
  (:import [the_joy_of_clojure.fp_techniques.records TreeNode]))

(defprotocol FIXO
  (fixo-push [fixo value])
  (fixo-pop [fixo])
  (fixo-peek [fixo]))

(extend-type TreeNode
  FIXO
  (fixo-push [node value]
    (xconj node value))
  (fixo-peek [node]
    (if (:l node)
      (recur (:l node))
      (:val node)))
  (fixo-pop [node]
    (if (:l node)
      (TreeNode. (:val node) (fixo-pop (:l node)) (:r node))
      (:r node))))

(extend-type nil
  FIXO
  (fixo-push [t v]
    (TreeNode. v nil nil)))

(extend-type clojure.lang.IPersistentVector
  FIXO
  (fixo-push [vector value]
    (conj vector value))
  (fixo-peek [vector]
    (peek vector))
  (fixo-pop [vector]
    (pop vector)))


; Method implementations in defrecord
; Fields of the object are available as locals (val instead of (:val t)).
(defrecord TreeNode2 [val l r]
  FIXO
  (fixo-push [t v]
    (if (< v val)
      (TreeNode2. val (fixo-push l v) r)
      (TreeNode2. val l (fixo-push r v))))
  (fixo-peek [t]
    (if l (fixo-peek l) val))
  (fixo-pop [t]
    (if l
      (TreeNode2. val (fixo-pop l) r)
      r)))
