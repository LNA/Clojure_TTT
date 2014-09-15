(ns clojure_ttt.game_rules_spec
  (:require [speclj.core :refer :all]
    [clojure_ttt.game_rules :refer :all]))

(describe "row-win?"
  (it "gives true for a 0 1 2 win"
    (let [board ["X" "X" "X" 3 4 5 6 7 8 ]]
      (should= true
       (row-one-win? board "X"))))

  (it "gives true for a 3 4 5 win"
    (let [board [0 1 2 "X" "X" "X" 6 7 8]]
      (should= true
       (row-two-win? board "X"))))

  (it "gives true for a 6 7 8 win"
    (let [board [0 1 2 3 4 5 "X" "X" "X"]]
      (should= true
       (row-three-win? board "X"))))

  (it "gives true for a 6 7 8 win"
    (let [board [0 1 2 3 4 5 "X" "X" "X"]]
      (should= true
       (row-win? board "X"))))

  (it "gives nil for a non win"
    (let [board (vec (range 9))]
      (should= nil
       (row-win? board "X")))))

(describe "column-win?"
  (it "gives true for a 0 3 6 win"
    (let [board ["X" 1 2 "X" 4 5 "X" 7 8]]
      (should= true
       (column-one-win? board "X"))))

  (it "gives true for a 1 4 7 win"
    (let [board [0 "X" 2  3 "X" 5 6 "X" 8]]
      (should= true
       (column-two-win? board "X"))))

  (it "gives true for a 2 5 8 win"
    (let [board [0 1 "X" 3 4 "X" 6 7 "X"]]
      (should= true
       (column-three-win? board "X"))))

  (it "gives true for a 2 5 8 win"
    (let [board [0 1 "X" 3 4 "X" 6 7 "X"]]
      (should= true
       (column-win? board "X"))))

  (it "gives false for a non win"
    (let [board (vec (range 9))]
      (should= nil
       (column-win? board "X")))))

(describe "diag-win?"
  (it "gives true for a 0 4 8 win"
    (let [board ["X" 1 2 3 "X" 5 6 7 "X"]]
      (should= true
       (left-diag-win? board "X"))))

  (it "gives true for a 2 4 6 win"
    (let [board [0 1 "X" 3 "X" 5 "X" 7 8]]
      (should= true
       (right-diag-win? board "X"))))

  (it "gives true for a 2 4 6 win"
    (let [board [0 1 "X" 3 "X" 5 "X" 7 8]]
      (should= true
       (diag-win? board "X"))))

  (it "gives false for a non win"
    (let [board (vec (range 9))]
      (should= nil
       (diag-win? board "X")))))

(describe "winner?"
  (it "gives true for a 2 4 6 win"
    (let [board [0 1 "X" 3 "X" 5 "X" 7 8]]
      (should= true
       (winner? board "X"))))

  (it "gives nil for a non win"
    (let [board (vec (range 9))]
      (should= nil
       (winner? board "X")))))

(describe "find-open-spaces"
  (it "returns true for an open space"
    (let [board (vec (range 2))]
      (should= [true true]
       (find-open-spaces board))))

  (it "returns false for a closed space"
    (let [board ["X" 2]]
      (should= [false true]
       (find-open-spaces board))))
  )

(describe "full-board?"
  (it "gives true for a full board"
    (let [board ["V" "V" "X" "V" "X" "V" "X" "V" "V"]]
      (should= true
       (full-board? board))))
  
  (it "gives false for a non full board"
    (let [board (vec (range 9))]
      (should= false
       (full-board? board)))))

(describe "game-over?"
  (it "gives true if there is a tie"
    (let [board ["O" "X" "X" "X" "X" "O" "O" "O" "X"]]
      (should= true 
        (game-over? board "X"))))

    (it "gives true if there is winner"
      (let [board ["X" "O" 2 "X" "X" "O" "X" "O" "O"]]
        (should= true 
          (game-over? board "X"))))

    (it "gives false if the game is in progress"
      (let [board (vec (range 9))]
        (should= false 
          (game-over? board "X")))))

(describe "valid-move?"
  (it "returns true if a move is valid"
    (let [board (vec (range 9))]
      (should= true 
        (valid-move? board 4))))

  (it "returns false if a space is taken"
    (let [board ["O" 2 "X" "X" "X" "O" "O" "O" "X"]]
      (should= false 
        (valid-move? board 4)))))

(describe "switch-players"
  (it "gives the next player mark"
    (let [player-one-mark "X"
          player-two-mark "O"
          current-mark "X"]
      (should= "O"
        (switch-players current-mark player-one-mark player-two-mark)))))