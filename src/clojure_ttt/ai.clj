(ns clojure_ttt.ai
  (:require
    [clojure.string :refer [replace]]
    [clojure_ttt.board         :as b]
    [clojure_ttt.ui            :as u]
    [clojure_ttt.game_rules    :as r])
  (:refer-clojure :exclude [replace]))

(defn- get-open-spaces [board]
  (keep-indexed #(if (number? %2) %1) board))

(defn- rank [board max-mark min-mark]
  (cond
    (r/game-in-progress? board max-mark min-mark) 100 
    (r/tie? board max-mark min-mark) 0
    (r/winner? board max-mark min-mark)       500))

(defn- get-boards [board max-mark min-mark depth]
  (map (fn [space] (b/make-move-on board space max-mark)) (get-open-spaces board)))

(defn- scores-before-depth [board max-mark min-mark depth]
  (map (fn [board] (rank board max-mark min-mark)) (get-boards board max-mark min-mark depth))) 

(defn- apply-depth [depth score]
  (if (odd? depth)
    (Math/abs (- depth score))
    (- score depth))) 

(defn- get-scores [board max-mark min-mark depth]
  (map (fn [x] (apply-depth depth x)) (scores-before-depth board max-mark min-mark depth)))

(defn- build-scores [board max-mark min-mark depth]
  (zipmap (get-open-spaces board) (get-scores board max-mark min-mark depth)))

(defn- remaining-scores [board max-mark min-mark depth]
  (first (first (build-scores board max-mark min-mark depth))))

(defn- best-min-move [board min-mark max-mark depth]
  (first (first (sort-by val > (build-scores board min-mark max-mark (inc depth))))))

(defn best-min-score [board min-mark max-mark depth]
  (last (first (sort-by val > (build-scores board min-mark max-mark depth)))))

(defn- best-max-move [board max-mark min-mark depth]
  (first (first (sort-by val > (build-scores board max-mark min-mark depth)))))

(defn- best-max-score [board max-mark min-mark depth]
  (last (first (sort-by val > (build-scores board max-mark min-mark depth)))))

(defn minimax [board max-mark min-mark depth best-score]
  (loop [board board
         max-mark max-mark
         min-mark min-mark
         depth depth
         best-score best-score]
         (prn "")
         (prn "best-score" best-score)
         (prn "depth" depth)
         (prn "max score" (build-scores board max-mark min-mark depth))
         (prn "min score" (build-scores board min-mark max-mark depth))
         (prn "best-max-score " (best-max-score board max-mark min-mark depth))
         (prn "best min score" (best-min-score board min-mark max-mark depth))
         (prn "best min move"  (best-min-move board min-mark max-mark depth))
         (prn "best max move" (best-max-move board max-mark min-mark depth))
         (prn "")
  (if (= best-score (best-max-score board max-mark min-mark depth))    ;if there is a win for the ai
    (best-max-move board max-mark min-mark depth)                      ;take the win
     (do                                                               ;else
      (if (= best-score (best-min-score board min-mark max-mark depth));if there is a win for the opponent
        (best-min-move board min-mark max-mark depth)                  ;block that win
        (recur (b/make-move-on board (remaining-scores board max-mark min-mark depth) max-mark) min-mark max-mark (inc depth) (dec best-score)))))))
  
(defn get-move [board max-mark min-mark depth]
  (minimax board max-mark min-mark depth 500))

(defn ai-move [board max-mark min-mark]
  (if (= false (r/game-over? board min-mark max-mark))
   (get-move board max-mark min-mark 0)))