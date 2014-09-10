(ns clojure_ttt.ui
  (:require
    [clojure.string :refer [replace]]
    [clojure.java.io :as io]
    [clojure.set])
  (:refer-clojure :exclude [replace]))

(defn ask-player-for-move []
  (println "where would you like to move?"))

(defn print-board [board]
    (prn (str "  " (board 0) "  |  " (board 1) "  |  " (board 2) "  "))
    (prn "-----------------")
    (prn (str "  " (board 3) "  |  " (board 4) "  |  " (board 5) "  "))
    (prn "-----------------")
    (prn (str "  " (board 6) "  |  " (board 7) "  |  " (board 8) "  ")))