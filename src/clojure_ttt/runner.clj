(ns clojure_ttt.runner
  (:require [clojure_ttt.board         :as p]
    [clojure_ttt.ui            :as u]
    [clojure_ttt.game_rules    :as r]
    [clojure_ttt.ai            :as a])
  (:gen-class :main true))

(defn set-player-one []
  (u/ask-for-mark "one")
  (def player-one-mark (ref (read-line)))
  (u/ask-for-player-type "one")
  (def player-one-type (ref (read-line))))

(defn set-player-two []
  (u/ask-for-mark "two")
  (def player-two-mark (ref (read-line)))
  (u/ask-for-player-type "two")
  (def player-two-type (ref (read-line))))

(defn set-game-properties [] 
  (set-player-one)
  (set-player-two)
  (def current-mark (ref @player-one-mark)))

  (defn setup-game []
    (u/welcome-message)
    (set-game-properties)
    (u/lets-begin-message)
    (u/print-board (vec (range 9))))

  (defn -main []
   (setup-game)
   (loop [current-mark @player-one-mark
          player-one-mark @player-one-mark
          player-two-mark @player-two-mark
          board       (vec (range 9))]
    (if (r/game-over? board current-mark) (u/game-over-message current-mark board)
      (do (u/ask-for-move current-mark)
        (let [move (Integer. (read-line))]
          (if (r/invalid-move? board move) 
            (u/invalid-move-message)
            (do (let [updated-board (p/make-move-on board move current-mark)] 
              (u/print-board updated-board)
              (recur (r/switch-players current-mark player-one-mark player-two-mark) player-two-mark player-one-mark updated-board)))))))))
