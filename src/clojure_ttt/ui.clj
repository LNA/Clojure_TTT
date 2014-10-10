(ns clojure_ttt.ui
  (:require
    [clojure.string :refer [replace]]
    [clojure_ttt.game_rules    :as r])
  (:refer-clojure :exclude [replace]))

(defn welcome-message []
  (println "Welcome to Clojure Tic Tac Toe!!!"))

(defn ask-for-type [player-number]
  (println "Please type a for ai or h for human, player " player-number ))

(defn ask-for-mark [player-number]
  (println "Please enter your game piece, player" player-number ":"))

(defn ask-for-move [mark]
  (println "Where would you like to move," mark " ?"))

(defn lets-begin-message []
  (println "Great.  Let's begin!!!"))

(defn ai-move-message []
  (println "I'm thinking..."))

(defn print-board [board]
    (prn "                 ")
    (prn (str "  " (board 0) "  |  " (board 1) "  |  " (board 2) "  "))
    (prn "-----------------")
    (prn (str "  " (board 3) "  |  " (board 4) "  |  " (board 5) "  "))
    (prn "-----------------")
    (prn (str "  " (board 6) "  |  " (board 7) "  |  " (board 8) "  "))
    (prn "                 "))

(defn invalid-move-message []
  (println "Invalid move.  Try again!"))

(defn game-over-message [board mark-1 mark-2]
  (if (= true (r/winner? board mark-1 mark-2)) 
    (println "Game over!") 
    (println "Its a tie!")))