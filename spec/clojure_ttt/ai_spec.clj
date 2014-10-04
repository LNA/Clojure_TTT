(ns clojure_ttt.ai_spec
  (:require [speclj.core :refer :all]
    [clojure_ttt.ai :refer :all]))

(describe "ai"
  (it "orders the moves through check-open-moves"
      (let [board ["h" 1   2
                   "h" 4   5
                    6 "a"  8]

            open-spaces (get-open-spaces board)
            ranks (check-open-spaces board "h" "a" 0)]
        (should="something"
         ranks)))

 (xit "returns the best move when win is space 2"
    (let [board [ 0   1   2
                 "O" "O" "X"
                  6  "O" "X"]
          max-mark "X"
          min-mark "O"
          move (minimax board max-mark min-mark) ]
      (should-contain move
        [1 2])))

   (xit "blocks an opponent win"
    (let [board [0  1   2
                 3  "O" 5
                 "O" 7 "X"]
          max-mark "X"
          min-mark "O"]
          (should= 2
            (minimax board max-mark min-mark))))

   (xit "gives a corner move to block the lower right knight set up"
    (let [board [0  1    2
                 3  "X"  "O"
                 6  "O"   8]
          max-mark "X"
          min-mark "O"
           move (minimax board max-mark min-mark)]
          (should-contain move
            [0 2 6 8])))

    (xit "gives a corner move to block the lower left knight set up"
      (let [board [0    1    2
                  "O"  "X"   5
                   6   "O"   8]
            max-mark "X"
            min-mark "O"
             move (minimax board max-mark min-mark)]
            (should-contain move
              [0 2 6 8])))

    (xit "gives a corner move to block the upper left knight set up"
      (let [board [0   "O"  2
                   "O" "X"  5
                   6    7  8]
            max-mark "X"
            min-mark "O"
             move (minimax board max-mark min-mark)]
            (should-contain move
              [0 2 6 8])))

    (xit "gives a corner move to block the upper right knight set up"
      (let [board [0   "O"   2
                   3   "X"  "O"
                   6    7    8]
            max-mark "X"
            min-mark "O"
             move (minimax board max-mark min-mark)]
            (should-contain move
              [0 2 6 8]))))
