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

(defn build-scores [board max-mark min-mark depth]
  (zipmap (get-open-spaces board) (get-scores board max-mark min-mark depth)))
  
(defn get-move [board max-mark min-mark depth]
  (let [moves (get-open-spaces board)
        min-scores (build-scores board min-mark max-mark depth)]
    (first (first (sort-by val > min-scores)))))

(defn ai-move [board max-mark min-mark] 
  (get-move board max-mark min-mark initial-depth))