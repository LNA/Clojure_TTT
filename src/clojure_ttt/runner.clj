(ns clojure_ttt.runner
  (:require [clojure_ttt.board         :as p]
    [clojure_ttt.ui            :as u]
    [clojure_ttt.game_rules    :as r]
    [clojure_ttt.ai            :as a])
  (:gen-class :main true))

(defn get-move [board current-type]
  (Integer. (if (= current-type "a")
    (a/ai-move board)
    (read-line))))

(defn game-loop [current-mark next-mark current-type next-type board]
    (loop [current-mark current-mark
          next-mark     next-mark
          current-type current-type
          next-type next-type
          board         board]
    (if (r/game-over? board next-mark)
      (u/game-over-message next-mark board)
      (do (u/ask-for-move current-mark)
      (let [move (get-move board current-type)
           updated-board (p/make-move-on board move current-mark)]
       (u/print-board updated-board)
       (recur next-mark current-mark next-type current-type updated-board))))))

  (defn -main []
    (u/welcome-message)
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