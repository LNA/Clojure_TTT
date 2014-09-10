(ns clojure_ttt.runner
  (:require [clojure_ttt.board :as p]
            [clojure_ttt.ui    :as u])
  (:gen-class :main true))

(defn -main []
  (u/welcome-message)
  (u/ask-for-mark "one")
  (let [player-one-mark (read-line)]
    (u/ask-for-mark "two")
    (let [player-two-mark (read-line)]
  (u/lets-begin-message)
  (u/print-board (vec (range 9)))
  (u/ask-for-move)
  (let [move (Integer. (read-line))
        starter-board (p/board)
        mark player-one-mark
        board (p/make-move-on starter-board move mark)] ; remove hard coded value
    (prn "Your move was" move)
    (u/print-board board)
    (u/ask-for-move)
    (let [move (Integer. (read-line))
          mark player-two-mark
          board (p/make-move-on board move mark)]
    (prn "Your move was" move)
    (u/print-board board)))
    (u/ask-for-move))))

