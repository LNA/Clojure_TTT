(ns clojure_ttt.ai_spec
  (:require [speclj.core :refer :all]
    [clojure_ttt.ai :refer :all]))

(describe "ai"
  (it "gives the rank of a specific move"
    (let [max-winning-board ["X" "X" "X" 3 4 5 6 7 8]
          min-winning-board ["O" "O" "O" 3 4 5 6 7 8]
          tie-board         ["X" "X" "O" "O" "O" "X" "X" "O" "O"]
          in-progress-board [0  "X" 2 "O" "O" "X" "X" "O" "X"]]
      (should= 500
        (rank max-winning-board "X" "O"))
      (should= 500
        (rank min-winning-board "O" "X"))
      (should= 0
        (rank tie-board "X" "O"))
      (should= 100
        (rank in-progress-board "X" "O")))))

(it "MINIMAX"
  (let [board [0  "X"  2 
              "O" "O" "X" 
              "X" "O" "X"]
        max-mark "X"
        min-mark "O" ]
    (should= 0
      (minimax board max-mark min-mark))))
