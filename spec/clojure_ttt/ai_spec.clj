(ns clojure_ttt.ai_spec
  (:require [speclj.core :refer :all]
    [clojure_ttt.ai :refer :all]))

(describe "ai"

   (it "finds the scores for a board"
    (let [board [ 0   1   2
                 "a" "h" "a"
                 "h" "a" "a"]
            max-mark "a"
            min-mark "h"]
    (should= {2 500, 1 100, 0 100} 
      (build-scores board "a" "h" 0))))

  (it "plays the best move"
    (let [board [ 0   1   2
                 "a" "h" "h"
                 "h" "a" "a"]
          max-mark "h"
          min-mark "a"]
          (should= 2
            (ai-move board max-mark min-mark ))))
  
  (it "blocks a win"
    (let [board [0  1 "h"
                "a" 4 "h"
                 6 "a" 8]
          max-mark "a"
          min-mark "h"]
          (should= 8
            (ai-move board max-mark min-mark ))))

  (it "plays a corner on the first move"
      (let [board [0  1  2
                   3  4  5
                   6  7  8]
            max-mark "X"
            min-mark "O"
             move (ai-move board max-mark min-mark)]
            (should-contain move
              [0 2 6 8])))

  (it "plays the center or corner on the second move"
    (let [board ["h" 1  2
                  3  4  5
                  6  7  8]
          max-mark "a"
          min-mark "h"
          move (ai-move board max-mark min-mark)]
          (should-contain move
            [2 4 6 8])))

   (it "blocks a knight set up"
      (let [board ["h"  1   2
                    3  "a"  5
                    6  "h"  8]
            max-mark "a"
            min-mark "h"
             move (ai-move board max-mark min-mark)]
            (should-contain move
              [3 5 6 8]))))
