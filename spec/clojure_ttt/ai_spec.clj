(ns clojure_ttt.ai_spec
  (:require [speclj.core :refer :all]
    [clojure_ttt.ai :refer :all]))

(describe "ai"
  (it "blocks a win"
    (let [board [0   1 "h"
                 "a" 4 "h"
                  6 "a" 8]
          max-mark "a"
          min-mark "h"]
          (should= 8
            (ai-move board max min-mark))))

  (it "plays the correct move"
    (let [board ["o" 1  2
                  3  4  5
                  6  7  8]
          max-mark "o"
          min-mark "x"]
          (should= 4
            (ai-move board max-mark min-mark))
          (should= 4
            (ai-move board min-mark max-mark))))

  (it "plays a corner on the first move"
      (let [board [0  1   2
                    3 4  5
                    6 7  8]
            max-mark "X"
            min-mark "O"
             move (ai-move board max-mark min-mark)]
            (should-contain move
              [0 2 6 8])))
  
  (it "finds the best move"
      (let [max-board ["h" 1 2 "h" 4 5 6 "a" 8]
            min-board ["h" 1 2 3 4 5 "a" "a" 8]]
        (should= 6
         (ai-move max-board "h" "a"))
        (should= 8
         (ai-move min-board "h" "a"))))

   (it "blocks a knight set up"
      (let [board ["h"  1   2
                    3  "a"  5
                    6  "h"  8]
            max-mark "a"
            min-mark "h"
             move (ai-move board max-mark min-mark)]
            (should-contain move
              [3 5 6 8]))))
