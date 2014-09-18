(ns clojure_ttt.runner_spec
  (:require [speclj.core :refer :all]
    [clojure_ttt.runner  :refer :all]
    [clojure_ttt.board         :as p]
    [clojure_ttt.ui            :as u]
    [clojure_ttt.game_rules    :as r]
    [clojure_ttt.ai            :as a]))

(defn mock-human-input [coll]
  (apply str (interleave coll (repeat "\n"))))

(describe "Runer" 
  (it "gets an ai move"
    (let [board (vec (range 9))
          current-type  "a"]
          (should= 0
            (get-move board current-type))))

  (it "gets a human move"
    (let [board (vec (range 9))
          current-type  "h"]
    (with-redefs [read-line (constantly "1")]
        (should= 1
          (get-move board current-type)))))

  (it "hits the game over message in the game loop"
    (let [board         (vec (range 9))
          game-over-message "game over message"]
          (with-in-str (mock-human-input [0 3 6])
        (with-redefs [println (constantly "game over message")
                      prn     (constantly true)]
          (should= game-over-message
            (game-loop "X" "O" "h" "a" board)))))))
  