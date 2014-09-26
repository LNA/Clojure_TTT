(ns clojure_ttt.ai
  (:require
    [clojure.string :refer [replace]]
    [clojure_ttt.board         :as b]
    [clojure_ttt.game_rules    :as r])
  (:refer-clojure :exclude [replace]))

(defn get-open-spaces [board]
  (keep-indexed #(if (number? %2) %1) board))

(defn rank [board mark opponent]
  (cond
    (r/tie? board mark opponent) 0
    (r/winner? board mark)       500
    (r/winner? board opponent)  -500)
    (r/game-in-progress? board mark opponent) 100)


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

(defn best-move-for [board space mark opponent]
  (let [depth 0
        max-rank (rank (b/make-move-on board space mark) mark opponent)
        min-rank (rank (b/make-move-on board space opponent) opponent mark)]
        (prn max-rank board space)))
        ;(track-moves space max-rank min-rank)))

(defn minimax [board max-mark min-mark]
  (map (fn [x] (best-move-for board x max-mark min-mark)) (get-open-spaces board)))
