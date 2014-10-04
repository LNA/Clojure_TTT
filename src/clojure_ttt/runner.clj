(ns clojure_ttt.runner
  (:require [clojure_ttt.board         :as b]
            [clojure_ttt.ui            :as u]
            [clojure_ttt.game_rules    :as r]
            [clojure_ttt.ai            :as a])
  (:gen-class :main true))


(defn get-human-move [board current-mark]
  (u/ask-for-move current-mark)
  (let [move (Integer. (read-line))]
    (if (r/valid-move? board move)
      move
      (do
      (u/invalid-move-message)
      (let [move (get-human-move board current-mark)]
        move)))))

(defn get-move [board current-type current-mark next-mark]
  (if (= current-type "a")
    (a/minimax board current-mark next-mark)
    (get-human-move board current-mark))) 

(defn game-loop [current-mark next-mark current-type next-type board]
    (loop [current-mark current-mark
          next-mark     next-mark
          current-type current-type
          next-type next-type
          board         board]
    (if (r/game-over? board next-mark current-mark)
      (u/game-over-message board current-mark next-mark) 
      (do 
        (let [move (get-move board current-type current-mark next-mark)] 
             (let [updated-board (b/make-move-on board move current-mark)]
          (u/print-board updated-board)
          (recur next-mark current-mark next-type current-type updated-board)))))))

(defn start []
  (let [_ (u/ask-for-mark "one")
          current-mark (read-line)
          _ (u/ask-for-type "one")
          current-type (read-line)
          _ (u/ask-for-mark "two")
          next-mark (read-line)
          _ (u/ask-for-type "one")
          next-type (read-line)
          board (vec (range 9))]
          (u/lets-begin-message)
          (u/print-board board) 
   (game-loop current-mark next-mark current-type next-type board)))

  (defn -main []
    (u/welcome-message)
    (start))