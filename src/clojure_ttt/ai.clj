(ns clojure_ttt.ai
  (:require
    [clojure.string :refer [replace]]
    [clojure_ttt.board         :as b]
    [clojure_ttt.game_rules    :as r])
  (:refer-clojure :exclude [replace]))

(defn open-spaces [board]
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
         
(defn rank-open-spaces [board max-mark min-mark]
  (let [board board
        max-mark max-mark
        min-mark min-mark
        depth    0
        spaces (open-spaces board)]
  (map (fn [space] (move-on-open-space space board max-mark min-mark depth)) spaces)))

(defn build-rankings [board max-mark min-mark]
  (let [open-spaces (open-spaces board)
        ranks       (rank-open-spaces board max-mark min-mark)]
    (zipmap ranks open-spaces)))

(defn find-best-ranked-move [board max-mark min-mark]
  (let [rank-graph  (build-rankings board max-mark min)]
    (get rank-graph (key (apply max-key val rank-graph)))))

(defn ai-move [board max-mark min-mark]
  (let [move (find-best-ranked-move board max-mark min-mark)]
    move))