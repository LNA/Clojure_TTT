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
  (some  #(% board mark) [row-one-win? row-two-win? row-three-win?]))

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
  (some  #(% board mark) [column-one-win? column-two-win? column-three-win?]))

(defn left-diag-win? [board mark]
  (cond
    (and (= mark (board 0)) (= mark (board 4)) (= mark (board 8))) true
    :else false))

(defn right-diag-win? [board mark]
  (cond
    (and (= mark (board 2)) (= mark (board 4)) (= mark (board 6))) true
    :else false))

(defn diag-win? [board mark]
  (some  #(% board mark) [left-diag-win? right-diag-win?]))

(defn winner? [board mark]
  (some  #(% board mark) [row-win? column-win? diag-win?]))

(defn find-open-spaces [board]
  (map (fn [x] (number? x)) board))

(defn full-board? [board]
  (let [checked-spaces (find-open-spaces board)]
    (every? false? checked-spaces)))

(defn game-over? [board mark]
  (cond 
    (or (= true (winner? board mark)) (= true (full-board? board))) true
    :else false))

(defn valid-move? [board move]
  (cond
    (and (= true (number? (board move))) (< move 9)) true 
    :else false))

(defn switch-players [current-mark mark-1 mark-2]
   (if (= mark-1 current-mark) mark-2 mark-1))