(ns clojure_ttt.ai
  (:require
    [clojure.string :refer [replace]]
    [clojure_ttt.board         :as b]
    [clojure_ttt.game_rules    :as r])
  (:refer-clojure :exclude [replace]))

(defn get-open-spaces [board]
  (keep-indexed #(if (number? %2) %1) board))

(defn rank-tie [board]
  (if (r/tie? board)
    0))

(defn rank-winner [board mark opponent depth] 
  (if (r/winner? board mark)  
    500 
    (do 
      (r/winner? board opponent) 
      -500)))

(defn rank [board mark opponent depth]
  (if (r/tie? board mark opponent)
    (+ depth 0)
    (rank-winner board mark opponent depth)))

(defn track-moves [space max-rank min-rank]
  (let [score (hash-map space, max-rank)
        new-score (hash-map space, min-rank)]
        (cond 
          (< (first (keys score)) (first (keys new-score))) new-score
          (> (first (keys score)) (first (keys new-score))) score)))

; "space min" {0 499}
; "space max" {0 501} nil why are these returning nil?
; "space min" {2 499}
; "space max" {2 -499} nil 

(defn best-move-for [board space mark opponent depth]
  (let [depth (+ 1 depth)
        max-rank (- depth (rank (b/make-move-on board space mark) mark opponent depth))
        min-rank (* -1 (+ depth (rank (b/make-move-on board space opponent) opponent mark depth)))]
        (track-moves space max-rank min-rank)))

(defn minimax [board max-mark min-mark depth]
  (map (fn [x] (best-move-for board x max-mark min-mark depth)) (get-open-spaces board)))
