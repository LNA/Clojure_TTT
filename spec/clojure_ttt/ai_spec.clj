(ns clojure_ttt.ai_spec
  (:require [speclj.core :refer :all]
    [clojure_ttt.ai :refer :all]))

(describe "ai"
  (it "returns the best move"
    (let [board [0  "X"  2 
                "O" "O" "X" 
                "X" "O" "X"]
          max-mark "X"
          min-mark "O" ]
      (should= 2
        (minimax board max-mark min-mark))))

  (it "returns the best move"
   (let [board ["X"  1    2 
                "O" "X" "O" 
                "X" "X" "X"]
        max-mark "X"
        min-mark "O" ]
    (should= 1
      (minimax board max-mark min-mark))))

  (it "returns the best move"
    (let [board [0  "X"  2 
                "X" "O" "O" 
                "X" "O" "X"]
          max-mark "X"
          min-mark "O" ]
      (should= 0
        (minimax board max-mark min-mark)))))
