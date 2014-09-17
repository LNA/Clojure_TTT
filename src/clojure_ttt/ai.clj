(ns clojure_ttt.ai
  (:require
    [clojure.string :refer [replace]]
    [clojure_ttt.game_rules    :as r])
  (:refer-clojure :exclude [replace]))

(defn ai-move [board]
  (first (keep-indexed #(if (number? %2) %1) board)))


