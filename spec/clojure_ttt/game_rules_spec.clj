(ns clojure_ttt.game_rules_spec
  (:require [speclj.core :refer :all]
            [clojure_ttt.game_rules :refer :all]))

(describe "row-one-win"
          (it "gives true for a 0 1 2 win"
              (let [board ["X" "X" "X" 3 4 5 6 7 8 ]]
                (should= true
                         (row-one-win board "X")))))

(describe "row-two-win"
          (it "gives true for a 3 4 5 win"
              (let [board [0 1 2 "X" "X" "X" 6 7 8]]
                (should= true
                         (row-two-win board "X")))))

(describe "row-three-win"
          (it "gives true for a 6 7 8 win"
              (let [board [0 1 2 3 4 5 "X" "X" "X"]]
                (should= true
                         (row-three-win board "X")))))

(describe "row-win"
          (it "gives true for a 6 7 8 win"
              (let [board [0 1 2 3 4 5 "X" "X" "X"]]
                (should= true
                         (row-win board "X"))))
          (it "gives false for a non win"
            (let [board (vec (range 9))]
              (should= false
                       (row-win board "X")))))
