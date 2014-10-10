(ns clojure_ttt.ai_spec
  (:require [speclj.core :refer :all]
    [clojure_ttt.ai :refer :all]))

(describe "ai"

  ;private
  (it "gets the open spaces"
      (let [board ["h" 1   2
                   "h" 4   5
                    6 "a"  8]]
        (should= [1 2 4 5 6 8]
         (get-open-spaces board))))

  (it "gets each board for scoring"
      (let [board ["h" 1   2
                   "h" 4   5
                    6 "a"  8]]
        (should= ["h" "h" 2 "h" 4 5 6 "a" 8]
         (first (get-boards board "h" "a" 0)))
        (should= ["h" 1 2 "h" 4 5 6 "a" "h"]
           (last (get-boards board "h" "a" 0)))))

  (it "gets the scores"
      (let [board ["h" 1   2
                   "h" 4   5
                    6 "a"  8]]
        (should= [100 100 100 100 500 100]
         (get-scores board "h" "a" 0))
        (should= [99 99 99 99 99 99]
         (get-scores board "a" "h" 1))))

  ;public
  (it "finds the best move"
      (let [max-board ["h" 1 2 "h" 4 5 6 "a" 8]
            min-board ["h" 1 2 3 4 5 "a" "a" 8]]
        (should= 6
         (minimax max-board "h" "a"))
        (should= 8
         (minimax min-board "h" "a"))))

   (it "blocks a knight set up"
      (let [board ["O"  1   2
                    3  "X"  5
                    6  "O"  8]
            max-mark "X"
            min-mark "O"
             move (minimax board max-mark min-mark)]
            (should-contain move
              [3 5 6 8])))

    (it "gives a corner move to block the lower left knight set up"
      (let [board [0    1    2
                  "O"  "X"   5
                   6   "O"   8]
            max-mark "X"
            min-mark "O"
             move (minimax board max-mark min-mark)]
            (should-contain move
              [0 2 6 8])))

    (it "gives a corner move to block the upper left knight set up"
      (let [board [0   "O"  2
                   "O" "X"  5
                   6    7  8]
            max-mark "X"
            min-mark "O"
             move (minimax board max-mark min-mark)]
            (should-contain move
              [0 2 6 8])))

    (it "gives a corner move to block the upper right knight set up"
      (let [board [0   "O"   2
                   3   "X"  "O"
                   6    7    8]
            max-mark "X"
            min-mark "O"
             move (minimax board max-mark min-mark)]
            (should-contain move
              [0 2 6 8]))))