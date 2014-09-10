(ns clojure_ttt.ui
  (:require
    [clojure.string :refer [replace]]
    [clojure.java.io :as io]
    [clojure.set])
  (:refer-clojure :exclude [replace]))

(defn welcome-message []
  (println "Welcome to Clojure Tic Tac Toe!!!"))

(defn ask-for-mark [player-number]
  (println "Please enter your game piece, player" player-number ":"))

(defn ask-for-move []
  (println "Where would you like to move?"))

(defn lets-begin-message []
  (println "Great.  Let's begin!!!"))

(defn print-board [board]
    (prn (str "  " (board 0) "  |  " (board 1) "  |  " (board 2) "  "))
    (prn "-----------------")
    (prn (str "  " (board 3) "  |  " (board 4) "  |  " (board 5) "  "))
    (prn "-----------------")
    (prn (str "  " (board 6) "  |  " (board 7) "  |  " (board 8) "  ")))