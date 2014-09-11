(ns clojure_ttt.game_rules
  (:require
    [clojure.string :refer [replace]]
    [clojure.java.io :as io]
    [clojure.set])
  (:refer-clojure :exclude [replace]))

(defn row-one-win? [board mark]
  (every? #{mark} (subvec board 0 3)))

(defn row-two-win? [board mark]
  (every? #{mark} (subvec board 3 6)))

(defn row-three-win? [board mark]
  (every? #{mark} (subvec board 6 9)))

(defn row-win? [board mark]
  (cond
    (row-one-win? board mark) true
    (row-two-win? board mark) true
    (row-three-win? board mark) true
    :else false))

(defn column-one-win? [board mark]
  (cond
    (and (= mark (board 0)) (= mark (board 3)) (= mark (board 6))) true
       :else false))

(defn column-two-win? [board mark]
  (cond
    (and (= mark (board 1)) (= mark (board 4)) (= mark (board 7))) true
       :else false))

(defn column-three-win? [board mark]
  (cond
    (and (= mark (board 2)) (= mark (board 5)) (= mark (board 8))) true
       :else false))

(defn column-win? [board mark]
  (cond
    (column-two-win? board mark) true
    (column-two-win? board mark) true
    (column-three-win? board mark) true
    :else false))

(defn left-diag-win? [board mark]
  (cond
    (and (= mark (board 0)) (= mark (board 4)) (= mark (board 8))) true
       :else false))

(defn right-diag-win? [board mark]
  (cond
    (and (= mark (board 2)) (= mark (board 4)) (= mark (board 6))) true
       :else false))

(defn diag-win? [board mark]
  (cond
    (left-diag-win? board mark) true
    (right-diag-win? board mark) true
    :else false))

(defn winner? [board mark]
  (cond
    (row-win? board mark) true
    (column-win? board mark) true
    (diag-win? board mark) true
    :else false))