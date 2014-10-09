(ns clojure_ttt.ai_spec
  (:require [speclj.core :refer :all]
    [clojure_ttt.ai :refer :all]))

(describe "ai"
 (xit "gets the scores"
      (let [board ["h" 1   2
                   "h" 4   5
                    6 "a"  8]]
        (should= [100 100 100 100 500 100]
         (get-scores board "h" "a" 0))
        (should= [-99 -99 -99 -99 -99 -99]
         (get-scores board "a" "h" 1))))

  (xit "finds the best move"
      (let [board ["h"  1  2
                   "h"  4  5
                    6  "a" 8]]
        (should= 6
         (minimax board "h" "a"))))

  (it "finds the best move"
      (let [board ["h" 1  2
                    3   4  5
                   "a" "a" 8]]
        (should= 8
         (minimax board "h" "a"))))

  (xit "continues to score if we haven't reached the end node"
    (let [board ["h"  1    2
                 "h"  "a"  5
                 "a"  "h" "a"]]
           (should= 2
          (score-moves board "h" "a" 0))))

  (xit "gives each boards"
      (let [board ["h" 1   2
                   "h" 4   5
                    6 "a"  8]]
        (should= ["h" "h" 2 "h" 4 5 6 "a" 8]
         (first (get-boards board "h" "a" 0)))
        (should= ["h" 1 2 "h" 4 5 6 "a" "h"]
           (last (get-boards board "h" "a" 0)))))

  (xit "gets the open spaces"
      (let [board ["h" 1   2
                   "h" 4   5
                    6 "a"  8]]
        (should= [1 2 4 5 6 8]
         (get-open-spaces board))))

  (xit "gets combines open spaces and scores"
      (let [board ["h" 1   2
                   "h" 4   5
                    6 "a"  8]
            spaces (get-open-spaces board)
            scores (get-scores board "h" "a" 0)]
        (should= {8 100, 6 500, 5 100, 4 100, 2 100, 1 100}
         (score-moves board "h" "a" 0))))

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
