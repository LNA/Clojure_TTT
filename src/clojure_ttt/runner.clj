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
    (let [player-two-mark (read-line)]

      (def current-mark (ref player-one-mark))
      (def board (ref (vec (range 9))))

      (u/lets-begin-message)
      (u/print-board @board)

      (while (not (r/game-over? @board @current-mark))
        (u/ask-for-move @current-mark)
        (let [move (Integer. (read-line))]
          (if (= true (r/valid-move? board move))
          (def board (ref (p/make-move-on @board move @current-mark)))
          (u/invalid-move-message))
          (u/print-board @board)
          (def current-mark (ref (r/switch-players @current-mark player-one-mark player-two-mark)))))
          (u/game-over-message @current-mark @board))))