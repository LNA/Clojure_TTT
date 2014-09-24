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

(defn move-on-open-space [space board max-mark min-mark depth]
  (let [next-board (b/make-move-on board space max-mark)]
      (+ depth (rank next-board max-mark min-mark depth))))
         
(defn rank-open-spaces [board max-mark min-mark depth]
  (let [spaces (get-open-spaces board)]
  (map (fn [space] (move-on-open-space space board max-mark min-mark depth)) spaces)))

(defn build-rankings [board max-mark min-mark depth]
  (let [open-spaces (get-open-spaces board)
        ranks       (rank-open-spaces board max-mark min-mark depth)]
    (zipmap ranks open-spaces)))

(defn find-best-ranked-move [board max-mark min-mark depth]
  (let [rank-graph  (build-rankings board max-mark min-mark depth)]
    (prn "rank-graph" rank-graph)
    (get rank-graph (key (apply max-key val rank-graph)))))

(defn add-next-open-space [board current-mark next-mark]
  (let [board board
        move (first (get-open-spaces board))]
        (b/make-move-on board move current-mark)))

(defn return-best-move [board max-mark min-mark depth] ;should not get here if (= 1 (count open-spaces))
  ;(prn board)
  (let [max-move (find-best-ranked-move board max-mark min-mark depth)
        min-move (find-best-ranked-move board min-mark max-mark depth)]
        ;(prn "max move" max-move "board" board)
        ;(prn "min-move" min-move "board" board) 
        (if (<= max-move min-move)
          max-move
          min-move)))

(defn store-move-and-score [board max-mark min-mark depth ]
  (let [rank-graph (build-rankings board max-mark min-mark depth)]
    (if (= {} rank-graph)
         {}
        (let [scores (apply max-key val rank-graph)] 
         (into {} [scores])))))

(defn next-open-space [open-spaces]
  (first (next open-spaces))
  )

(defn return-move [stored-move]
  (apply val stored-move))

(defn find-max-score [score-hash]
  (prn "score-hash:" (score-hash)))

(defn smash-hash [space max-rank min-rank]
  (hash-map space, max-rank)
  (hash-map space, min-rank))

(defn best-move-for [board space mark opponent depth]
  (let [depth (+ 1 depth)
        max-rank (- depth (rank (b/make-move-on board space mark) mark opponent depth))
        min-rank (* -1 (+ depth (rank (b/make-move-on board space opponent) opponent mark depth)))]
        (smash-hash space max-rank min-rank)))

(defn minimax [board max-mark min-mark depth]
  (map (fn [x] (best-move-for board x max-mark min-mark depth)) (get-open-spaces board)))
