(ns clojure_ttt.ai_spec
  (:require [speclj.core :refer :all]
    [clojure_ttt.ai :refer :all]))

(describe "ai"

  (it "ranks the boards"
      (let [board [ 0   1   2
                   "a" "h" "h"
                   "h" "a" "a"]
            max-mark "a"
            min-mark "h"]
            (should= 0
              (rank-boards board max-mark min-mark 0))))
  
  (xit "blocks a win"
    (let [board [0  1 "h"
                "a" 4 "h"
                 6 "a" 8]
          max-mark "a"
          min-mark "h"]
          (should= 8
            (ai-move board max-mark min-mark))))

  (xit "plays a corner on the first move"
      (let [board [0  1  2
                   3  4  5
                   6  7  8]
            max-mark "X"
            min-mark "O"
             move (ai-move board max-mark min-mark)]
            (should-contain move
              [0 2 6 8])))

  (xit "plays the center move on the second move"
    (let [board ["h" 1  2
                  3  4  5
                  6  7  8]
          max-mark "a"
          min-mark "h"]
          (should= 4
            (ai-move board max-mark min-mark))))

   (xit "plays the center move on the second move"
    (let [board [0 1  "h"
                  3  4  5
                  6  7  8]
          max-mark "a"
          min-mark "h"]
          (should= 4
            (ai-move board max-mark min-mark))))
  
   (xit "blocks a knight set up"
      (let [board ["h"  1   2
                    3  "a"  5
                    6  "h"  8]
            max-mark "a"
            min-mark "h"
             move (ai-move board max-mark min-mark)]
            (should-contain move
              [3 5 6 8]))))
