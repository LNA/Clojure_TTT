(ns clojure_ttt.game_rules
  (:require
    [clojure.string :refer [replace]])
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

(defn tie? [board mark-1 mark-2]
  (let [checked-spaces (find-open-spaces board)
        board           board]
    (cond
      (and (every? false? checked-spaces) (not (winner? board mark-1)) (not (winner? board mark-2))) true
      :else false))) 

(defn game-over? [board mark-1 mark-2]
  (cond 
    (or (= true (winner? board mark-1)) (= true (tie? board mark-1 mark-2))) true
    :else false))

(defn in-bounds-move? [board move]
    (<= move 8))

(defn open-move? [board move]
  (number? (board move)))

(defn valid-move? [board move]
  (cond
    (and (in-bounds-move? board move) (open-move? board move)) true
  :else false))