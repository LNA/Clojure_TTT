(ns clojure_ttt.ai
  (:require
    [clojure.string :refer [replace]]
    [clojure_ttt.board         :as b]
    [clojure_ttt.ui            :as u]
    [clojure_ttt.game_rules    :as r])
  (:refer-clojure :exclude [replace]))

(def ^:const highest-score 500)
(def ^:const tie-score 0)
(def ^:const in-progress-score 100)
(def ^:const initial-depth 0)

(defn- get-open-spaces [board]
  (keep-indexed #(if (number? %2) %1) board))

(defn- rank [board max-mark min-mark]
  (cond
    (r/game-in-progress? board max-mark min-mark) in-progress-score
    (r/tie? board max-mark min-mark) tie-score
    (r/winner? board max-mark min-mark)       highest-score))

(defn- get-boards [board max-mark min-mark] 
  (map (fn [space] (b/make-move-on board space max-mark)) (get-open-spaces board)))

(defn- scores-before-depth [board max-mark min-mark]
  (map (fn [board] (rank board max-mark min-mark)) (get-boards board max-mark min-mark))) 

(defn- apply-depth [depth score]
  (if (odd? depth)
    (Math/abs (- depth score))
    (- score depth))) 

(defn- get-scores [board max-mark min-mark depth]
  (map (fn [x] (apply-depth depth x)) (scores-before-depth board max-mark min-mark)))

(defn- build-scores [board max-mark min-mark depth]
  (zipmap (get-open-spaces board) (get-scores board max-mark min-mark depth)))

(defn next-space [board max-mark min-mark depth]
  (first (first (build-scores board max-mark min-mark depth))))

(defn- best-min-move [board min-mark max-mark depth]
  (first (first (sort-by val > (build-scores board min-mark max-mark (inc depth))))))

(defn best-min-score [board min-mark max-mark depth]
  (last (first (sort-by val > (build-scores board min-mark max-mark depth)))))

(defn- best-max-move [board max-mark min-mark depth]
  (first (first (sort-by val > (build-scores board max-mark min-mark depth)))))

(defn- best-max-score [board max-mark min-mark depth]
  (last (first (sort-by val > (build-scores board max-mark min-mark depth)))))

(defn rank-boards [board max-mark min-mark depth]
   (u/print-board board)
  (prn "is there a tie?" (r/tie? board max-mark min-mark))
  (cond
    (r/game-in-progress? board max-mark min-mark) in-progress-score
    (r/tie? board max-mark min-mark) tie-score
    (r/winner? board max-mark min-mark)       highest-score)
  (recur 
    (b/make-move-on board (next-space board max-mark min-mark depth) max-mark) 
        min-mark 
        max-mark
        (inc depth)))

(defn minimax [board max-mark min-mark depth best-score]
  (u/print-board board)
  (prn (next-space board max-mark min-mark depth))
   ;(println (build-scores board min-mark max-mark depth))
    (if (= best-score (best-min-score board min-mark max-mark depth))
      (let [move (first (sort-by val > (build-scores board min-mark max-mark depth)))]
      ;(println (build-scores board min-mark max-mark depth))
      ;(println "got best-score:" move)
      pmap doall move)
      (recur 
        (b/make-move-on board (next-space board max-mark min-mark depth) max-mark) 
        min-mark max-mark (inc depth) (dec best-score))))
  
(defn get-move [board max-mark min-mark depth]
  (if (= highest-score (best-min-score board max-mark min-mark depth))
    (best-max-move board max-mark min-mark depth)
    (let [best-max-move (minimax board max-mark min-mark depth highest-score)
          best-min-move (minimax board min-mark max-mark depth highest-score)]
          (prn "best-max-move" best-max-move)
           (prn "best-min-move" best-min-move)
          (prn ( > (last best-min-move) (last best-max-move)))
          (cond
            ( = (last best-min-move) (last best-max-move)) (first best-min-move)
            ( > (last best-min-move) (last best-max-move)) (first best-min-move)
            ( < (last best-min-move) (last best-max-move)) (first best-max-move)))))

(defn ai-move [board max-mark min-mark]
  (if (= false (r/game-over? board min-mark max-mark))
   (get-move board max-mark min-mark initial-depth)))