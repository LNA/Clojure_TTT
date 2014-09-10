(ns clojure_ttt.ui
  (:require
    [clojure.string :refer [replace]]
    [clojure.java.io :as io]
    [clojure.set])
  (:refer-clojure :exclude [replace]))

(defn ask-player-for-move []
  (println "where would you like to move?"))