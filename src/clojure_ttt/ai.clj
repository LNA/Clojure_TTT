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
    (r/game-in-progress? board mark opponent) 100
    (r/tie? board mark opponent) 0
    (r/winner? board mark)       500))

(defn best-move [tracked-moves]
   (last (last tracked-moves)))

(defn track-moves [space max-rank min-rank depth]
  (best-move (sorted-map (- max-rank depth) space, (+ min-rank depth) space)))

(defn best-move-for [board space mark opponent depth]
  (let [max-rank (rank (b/make-move-on board space mark) mark opponent)
        min-rank (rank (b/make-move-on board space opponent) opponent mark)]
        (track-moves space min-rank (* -1 min-rank) depth)))

(defn minimax [board max-mark min-mark]
  (loop [board board
         max-mark max-mark
         min-mark min-mark
         depth 0]
    (if (r/game-over? board max-mark min-mark)
      (best-move-for board (first (get-open-spaces board)) min-mark max-mark depth)
      (do 
        (if (r/game-over? (b/make-move-on board (first (get-open-spaces board)) max-mark) max-mark min-mark)
          (first (get-open-spaces board))
          (do 
          (best-move-for board (first (get-open-spaces board)) min-mark max-mark depth ) ;switches here
          (recur (b/make-move-on board (first (get-open-spaces board)) max-mark) min-mark max-mark ( inc depth))))))))
