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
      (u/lets-begin-message)

      (while (= false (r/game-over? board player-one-mark player-two-mark)
        (u/print-board board))
        (u/ask-for-move)
        (let [move (Integer. (read-line))
          starter-board (p/board)
          mark player-one-mark
          board (p/make-move-on starter-board move mark)] 
          (prn "Your move was" move)
          (u/print-board board))))))

      ; display_board
      ; make_move(settings, current_player, current_mark)
      ; current_player = next_player(current_player, settings)
      ; current_mark   = next_mark(current_mark, settings)