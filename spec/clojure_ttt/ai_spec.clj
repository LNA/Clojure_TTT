(ns clojure_ttt.ai_spec
  (:require [speclj.core :refer :all]
    [clojure_ttt.ai :refer :all]))

(describe "ai"
  (it "returns the best move when win is space 2"
    (let [board ["O"  1   2  
                 "O" "O" "X" 
                " X" "O" "X"]
          max-mark "X"
          min-mark "O" ]
      (should= 2
        (minimax board max-mark min-mark))))

  (it "returns the best move when win is space 0"
    (let [board [0  1  "O" 
                "X" "O" "O" 
                "X" "O" "X"]
          max-mark "X"
          min-mark "O" ]
      (should= 0
        (minimax board max-mark min-mark))))

   (it "returns the best move on an empty board"
     (let [board [0 1 2 3 4 5 6 7 8]
          max-mark "X"
          min-mark "O" 
          move (minimax board max-mark min-mark)
          best-first-moves [0 2 6 8]]
      (should-contain move
        best-first-moves)))

   (it "blocks an opponent win"
    (let [board [0  1   2 
                 3  "O" 5 
                 "O" 7 "X"]
          max-mark "X"
          min-mark "O"]
          (should= 2
            (minimax board max-mark min-mark)))))