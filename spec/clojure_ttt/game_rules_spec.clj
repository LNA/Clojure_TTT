(ns clojure_ttt.game_rules_spec
  (:require [speclj.core :refer :all]
    [clojure_ttt.game_rules :refer :all]))

(describe "row-one-win??"
  (it "gives true for a 0 1 2 win"
    (let [board ["X" "X" "X" 3 4 5 6 7 8 ]]
      (should= true
       (row-one-win? board "X")))))

(describe "row-two-win?"
  (it "gives true for a 3 4 5 win"
    (let [board [0 1 2 "X" "X" "X" 6 7 8]]
      (should= true
       (row-two-win? board "X")))))

(describe "row-three-win?"
  (it "gives true for a 6 7 8 win"
    (let [board [0 1 2 3 4 5 "X" "X" "X"]]
      (should= true
       (row-three-win? board "X")))))

(describe "row-win?"
  (it "gives true for a 6 7 8 win"
    (let [board [0 1 2 3 4 5 "X" "X" "X"]]
      (should= true
       (row-win? board "X"))))
  (it "gives false for a non win"
    (let [board (vec (range 9))]
      (should= false
       (row-win? board "X")))))

(describe "column-one-win?"
  (it "gives true for a 0 3 6 win"
    (let [board ["X" 1 2 "X" 4 5 "X" 7 8]]
      (should= true
       (column-one-win? board "X")))))

(describe "column-two-win?"
  (it "gives true for a 1 4 7 win"
    (let [board [0 "X" 2  3 "X" 5 6 "X" 8]]
      (should= true
       (column-two-win? board "X")))))

(describe "column-three-win?"
  (it "gives true for a 2 5 8 win"
    (let [board [0 1 "X" 3 4 "X" 6 7 "X"]]
      (should= true
       (column-three-win? board "X")))))

(describe "column-win?"
  (it "gives true for a 2 5 8 win"
    (let [board [0 1 "X" 3 4 "X" 6 7 "X"]]
      (should= true
       (column-win? board "X"))))

  (it "gives false for a non win"
    (let [board (vec (range 9))]
      (should= false
       (column-win? board "X")))))

(describe "left-diag-win?"
  (it "gives true for a 0 4 8 win"
    (let [board ["X" 1 2 3 "X" 5 6 7 "X"]]
      (should= true
       (left-diag-win? board "X")))))

(describe "right-diag-win?"
  (it "gives true for a 2 4 6 win"
    (let [board [0 1 "X" 3 "X" 5 "X" 7 8]]
      (should= true
       (right-diag-win? board "X")))))

(describe "diag-win?"
  (it "gives true for a 2 4 6 win"
    (let [board [0 1 "X" 3 "X" 5 "X" 7 8]]
      (should= true
       (diag-win? board "X"))))

  (it "gives false for a non win"
    (let [board (vec (range 9))]
      (should= false
       (diag-win? board "X")))))

(describe "winner?"
  (it "gives true for a 2 4 6 win"
    (let [board [0 1 "X" 3 "X" 5 "X" 7 8]]
      (should= true
       (winner? board "X"))))

  (it "gives false for a non win"
    (let [board (vec (range 9))]
      (should= false
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