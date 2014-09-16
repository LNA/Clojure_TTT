(ns clojure_ttt.runner
  (:require [clojure_ttt.board         :as p]
    [clojure_ttt.ui            :as u]
    [clojure_ttt.game_rules    :as r]
    [clojure_ttt.ai            :as a])
  (:gen-class :main true))

  (defn game-loop [current-mark next-mark board]
    (loop [current-mark current-mark
          next-mark     next-mark
          board         board]
    (if (r/game-over? board current-mark) (u/game-over-message current-mark board)
      (do (u/ask-for-move current-mark)
        (let [move (Integer. (read-line))]
          (if (r/invalid-move? board move) 
            (u/invalid-move-message)
            (do (let [updated-board (p/make-move-on board move current-mark)] 
              (u/print-board updated-board)
              (recur next-mark current-mark updated-board)))))))))

  (defn -main []
    (u/welcome-message)
    (u/ask-for-mark "one")
    (let [ current-mark read-line]
      (u/ask-for-mark "two")
      (let [ next-mark read-line
             board     (vec (range 9))]
          (u/lets-begin-message)
          (u/print-board board)
   (game-loop current-mark next-mark board))))