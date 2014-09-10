(ns clojure_ttt.board
  (:require
    [clojure.string :refer [replace]]
    [clojure.java.io :as io]
    [clojure.set])
  (:refer-clojure :exclude [replace]))

(defn board []
  (vec (range 9)))

(defn make-move-on [board move mark]
  (assoc board move mark))
