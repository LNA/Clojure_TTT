(ns clojure_ttt.runner
  (:require [clojure_ttt.board         :as p]
            [clojure_ttt.ui            :as u]
            [clojure_ttt.game_rules    :as r])
  (:gen-class :main true))

(defn -main []
  (u/welcome-message)
  (u/ask-for-mark "one")
  (let [player-one-mark (read-line)]
    (u/ask-for-mark "two")
    (let [player-two-mark (read-line)
          board (vec (range 9))]

      (def current-mark (ref player-one-mark))
      ; (def board (ref (vec (range 9))))

      (u/lets-begin-message)
      (u/print-board board)
      (def current-mark (ref player-one-mark))

      (while (not (r/game-over? board player-one-mark player-two-mark))
        (u/ask-for-move)
        (let [move (Integer. (read-line))
          board (p/make-move-on board move @current-mark)] 
          (prn board)
          (prn "Your move was" move)
          (prn board)
          (def current-mark (ref (r/switch-players @current-mark player-one-mark player-two-mark)))
          (u/print-board board))))))