(ns clojure_ttt.runner
  (:require [clojure_ttt.board         :as p]
            [clojure_ttt.ui            :as u]
            [clojure_ttt.game_rules    :as r]
            [clojure_ttt.ai            :as a])
  (:gen-class :main true))

(defn set-players []
  (def player-one-mark (ref (read-line)))
  (def current-mark (ref @player-one-mark))
  (u/ask-for-player-type "one")
  (def player-one-type (ref (read-line)))
  (u/ask-for-mark "two")
  (def player-two-mark (ref (read-line)))
  (u/ask-for-player-type "two")
  (def player-two-type (ref (read-line)))
  (def board (ref (vec (range 9)))))

(defn setup-game [] 
  (u/welcome-message)
  (u/ask-for-mark "one")
  (set-players)
  (u/lets-begin-message)
  (u/print-board @board))

(defn -main []
  (setup-game)
  (while (not (r/game-over? @board @current-mark))
    (u/ask-for-move @current-mark)
    (let [move (Integer. (read-line))]
      (if (= true (r/valid-move? board move))
      (def board (ref (p/make-move-on @board move @current-mark)))
      (u/invalid-move-message))
      (u/print-board @board)
      (def current-mark (ref (r/switch-players @current-mark @player-one-mark @player-two-mark)))))
      (u/game-over-message @current-mark @board))
