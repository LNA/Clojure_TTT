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
    (r/winner? board mark opponent)       500))

(defn best-move-for [board space mark opponent depth]
  (let [max-rank (rank (b/make-move-on board space mark) mark opponent) ]
       (sorted-map-by > space (- max-rank depth))))

(defn check-open-spaces [board max-mark min-mark depth]
  (let [depth (+ 1 depth)]
   (map (fn [x] (best-move-for board x max-mark min-mark depth)) (get-open-spaces board))))

(defn score-available-moves [board max-mark min-mark]
  (loop [board board
         max-mark max-mark
         min-mark min-mark
         depth 0]
        (if (r/game-over? board min-mark max-mark)
          (prn "find-best-score")
          (do
            (check-open-spaces board max-mark min-mark depth)
            (recur (b/make-move-on board (first (get-open-spaces board)) max-mark) min-mark max-mark ( inc depth))))))

(defn ai-move [board max-mark min-mark depth]
  (loop [board board
         max-mark max-mark
         min-mark min-mark
         depth depth]
         (if (= nil (first (get-open-spaces board)))
          (prn "find-best-score")
          (do
           (check-open-spaces board max-mark min-mark depth)
            (score-available-moves (b/make-move-on board (first (get-open-spaces board)) min-mark) min-mark max-mark)
            (recur (assoc board (first (get-open-spaces board)) max-mark ) min-mark max-mark depth)))))

(defn minimax [board max-mark min-mark]
  (if (= false (r/game-over? board min-mark max-mark))
   (ai-move board max-mark min-mark 0)))
