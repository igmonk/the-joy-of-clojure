(ns the-joy-of-clojure.fp-techniques.multimethods-and-udp
  (:refer-clojure :exclude [get]))

; UDP = Universal Design Pattern
; This idea that there is generality in the specific is of far-reaching importance.
; — Douglas Hofstadter, Gödel, Escher, Bach

; In addition to the prototype reference, the UDP requires a set of supporting functions to operate:
; beget, get, put, has?, and forget

(defn beget
  "Takes a map and associates its prototype reference to another map, returning a new map."
  [this proto]
  (assoc this ::prototype proto))

(defn get
  "Performs (potentially) nested lookup:
  whenever value isn't found in a given map, the prototype chain is followed until the end."
  [m k]
  (when m
    (if-let [[_ v] (find m k)]
      v
      (recur (::prototype m) k))))

; Function put takes a key and an associated value and puts them into the supplied map,
; overwriting any existing key of the same name (using assoc directly will work).
(def put assoc)


; Multimethods
; Multimethods provide a way to perform function polymorphism based on the result of an arbitrary dispatch function.

(defmulti compiler :os)
(defmethod compiler ::unix [m] (get m :c-compiler))
(defmethod compiler ::osx [m] (get m :llvm-compiler))

