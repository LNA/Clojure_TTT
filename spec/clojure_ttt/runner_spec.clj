(ns clojure_ttt.runner_spec
  (:require [speclj.core :refer :all]
    [clojure_ttt.runner  :refer :all]
    [clojure_ttt.board         :as p]
    [clojure_ttt.ui            :as u]
    [clojure_ttt.game_rules    :as r]
    [clojure_ttt.ai            :as a]))

(describe "Runer" 
  (it "gets a move"
    (let [board (vec (range 9))
          current-type  "h"
          next-type     "a"
          current-mark  "X"
          next-mark     "O"]
    (with-redefs [read-line (constantly "1")]
        (should= 1
          (get-move board current-type current-mark next-mark)))))

  (it "hits the game over message in the game loop"
    (let [board         (vec (range 9))
          game-over-message "game over message"]
        (with-redefs [println (constantly "game over message")
                      prn     (constantly true)]
          (should= game-over-message
            (game-loop "X" "O" "a" "a" board))))))