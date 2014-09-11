(ns clojure_ttt.game_rules
  (:require
    [clojure.string :refer [replace]]
    [clojure.java.io :as io]
    [clojure.set])
  (:refer-clojure :exclude [replace]))

(defn row-one-win [board mark]
  (every? #{mark} (subvec board 0 3)))

(defn row-two-win [board mark]
  (every? #{mark} (subvec board 3 6)))

(defn row-three-win [board mark]
  (every? #{mark} (subvec board 6 9)))

(defn row-win [board mark]
  (cond
    (row-one-win board mark) true
    (row-two-win board mark) true
    (row-three-win board mark) true
    :else false))
