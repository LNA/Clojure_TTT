(ns clojure_ttt.runner
  (:require [clojure_ttt.board :as p]
            [clojure_ttt.ui    :as u])
  (:gen-class :main true))

(defn -main []
  (u/ask-player-for-move)
  (let [move (Integer. (read-line))
        starter-board (p/board)
        board (p/make-move-on starter-board move "X")]
    (prn "Your move was" move)
    (u/print-board board)))

 