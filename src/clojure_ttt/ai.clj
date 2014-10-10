(ns clojure_ttt.ai
  (:require
    [clojure.string :refer [replace]]
    [clojure_ttt.board         :as b]
    [clojure_ttt.ui            :as u]
    [clojure_ttt.game_rules    :as r])
  (:refer-clojure :exclude [replace]))

(defn get-open-spaces [board]
  (keep-indexed #(if (number? %2) %1) board))

(defn rank [board max-mark min-mark]
  (cond
    (r/game-in-progress? board max-mark min-mark) 100 
    (r/tie? board max-mark min-mark) 0
    (r/winner? board max-mark min-mark)       500))

(defn get-boards [board max-mark min-mark depth]
  (map (fn [space] (b/make-move-on board space max-mark)) (get-open-spaces board)))

(defn scores-before-depth [board max-mark min-mark depth]
  (map (fn [board] (rank board max-mark min-mark)) (get-boards board max-mark min-mark depth))) 

(defn apply-depth [depth score]
  (if (odd? depth)
    (Math/abs (- depth score))
    (- score depth))) 

(defn get-scores [board max-mark min-mark depth]
  (map (fn [x] (apply-depth depth x)) (scores-before-depth board max-mark min-mark depth))) ;test me!!!

(defn build-scores [board max-mark min-mark depth]
  (zipmap (get-open-spaces board) (get-scores board max-mark min-mark depth)))

(defn best-min-score [board min-mark max-mark depth]
  (first (first (sort-by val > (build-scores board min-mark max-mark (inc depth))))))

(defn best-max-score [board max-mark min-mark depth]
  (first (first (sort-by val > (build-scores board max-mark min-mark depth)))))

(defn max-scored-move [board max-mark min-mark depth]
  (last (first (sort-by val > (build-scores board max-mark min-mark depth)))))

(defn min-scored-move [board min-mark max-mark depth]
  (last (first (sort-by val > (build-scores board min-mark max-mark (inc depth))))))

(defn remaining-scores [board max-mark min-mark depth]
  (first (first (build-scores board max-mark min-mark depth))))

(defn minimax [board max-mark min-mark depth]
  (loop [board board
         max-mark max-mark
         min-mark min-mark
         depth depth
         desired-score (- 500 depth)]
  (if (< 200 (max-scored-move board max-mark min-mark depth))
    (best-max-score board max-mark min-mark depth)
     (do
      (if (= (- desired-score 1)  (min-scored-move board min-mark max-mark depth))
        (best-min-score board min-mark max-mark depth)
        (recur (b/make-move-on board (remaining-scores board max-mark min-mark depth) max-mark) min-mark max-mark (inc depth) desired-score))))))
  
(defn get-move [board max-mark min-mark depth]
  (minimax board max-mark min-mark depth))

(defn ai-move [board max-mark min-mark]
  (if (= false (r/game-over? board min-mark max-mark))
   (get-move board max-mark min-mark 0)))