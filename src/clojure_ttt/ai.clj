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
    0
    (rank-winner board mark opponent depth)))

(defn move-on-open-space [space board max-mark min-mark depth]
  (let [space space 
        bord board 
        max-mark max-mark
        min-mark min-mark
        depth  (+ 1 depth)]
        (let [next-board (b/make-move-on board space max-mark)]
            (rank next-board max-mark min-mark depth))))
         
(defn rank-open-spaces [board max-mark min-mark depth]
  (let [board board
        max-mark max-mark
        min-mark min-mark
        depth    depth
        spaces (get-open-spaces board)]
  (map (fn [space] (move-on-open-space space board max-mark min-mark depth)) spaces)))

(defn build-rankings [board max-mark min-mark depth]
  (let [open-spaces (get-open-spaces board)
        ranks       (rank-open-spaces board max-mark min-mark depth)]
    (zipmap ranks open-spaces)))

(defn find-best-ranked-move [board max-mark min-mark depth]
  (let [rank-graph  (build-rankings board max-mark min-mark depth)]
    (get rank-graph (key (apply max-key val rank-graph)))))

(defn add-next-open-space [board current-mark next-mark]
  (let [board board
        move (first (get-open-spaces board))]
        (b/make-move-on board move current-mark)))

(defn return-best-move [board max-mark min-mark depth]
  (let [max-move (find-best-ranked-move board max-mark min-mark depth)
        min-move (find-best-ranked-move board min-mark max-mark depth)]
        (if (<= max-move min-move)
          max-move
          min-move)))

(defn store-move-and-score [board max-mark min-mark depth]
  (let [rank-graph (build-rankings board max-mark min-mark depth)
        stored-moves (apply max-key val rank-graph)]
         (into {} [stored-moves])))

(defn next-open-space [open-spaces]
  (first (next open-spaces))
  )

(defn return-move [stored-move]
  (apply val stored-move))

(defn ai-move [board max-mark min-mark]
  (loop [board board
         max-mark max-mark
         min-mark min-mark
         open-spaces (get-open-spaces board)
         possible-moves {}]
        
         (let [next-space (first open-spaces)
               next-board (b/make-move-on board next-space max-mark)
               depth 0]
         (let [scored-moves (store-move-and-score next-board max-mark min-mark depth)
               open-spaces (next open-spaces)]
               ;score next space on new
        (prn "next space:" next-space "on " next-board "open-spaces" open-spaces "scored moves" scored-moves))
      (recur board max-mark min-mark (rest open-spaces) possible-moves))))
