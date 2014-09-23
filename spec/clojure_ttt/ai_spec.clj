(ns clojure_ttt.ai_spec
  (:require [speclj.core :refer :all]
    [clojure_ttt.ai :refer :all]))

(describe "ai"
  (it "gives the rank of a specific move"
    (let [max-winning-board ["X" "X" "X" 3 4 5 6 7 8]
          min-winning-board ["O" "O" "O" 3 4 5 6 7 8]
          tie-board         ["X" "X" "O" "O" "O" "X" "X" "O" "O"]]
      (should= 500
        (rank max-winning-board "X" "O" 0))
      (should= -500
        (rank min-winning-board "X" "O" 0))
      (should= 0
        (rank tie-board "X" "O" 0))))

  (it "ranks through move-on-open-space"
    (let [mark "X"
          opponent "O"
          space  0
          depth  0]
      (should= 500
        (move-on-open-space space [0 "X" "X" 3 4 5 6 7 8] mark opponent depth))
      (should= -500
        (move-on-open-space space [0 "X" "X" 3 4 5 6 7 8] opponent mark depth))
      (should= 0
        (move-on-open-space space ["X" "X" "O" "O" "O" "X" "X" "O" "O"] mark opponent depth))))

  (it "ranks through rank-open-spaces" ;rank open spaces
    (let [mark "X"
          opponent "O"]
      (should= [500 -500 -500 -500 -500 -500 -500]
        (rank-open-spaces [0 "X" "X" 3 4 5 6 7 8] mark opponent))
      (should= [-500 -500 -500 -500 -500 -500 -500]
        (rank-open-spaces [0 "X" "X" 3 4 5 6 7 8] opponent mark ))
      (should= []
        (rank-open-spaces ["X" "X" "O" "O" "O" "X" "X" "O" "O"] mark opponent))))
               
  (it "gives the open spaces" 
    (let [board [0 "X" "X" "O" "O" "X" "X" 7 8]]
      (should= [0 7 8]
        (open-spaces board))))

  (it "gives a hash map of open ranked spaces without multiple rankings"
    (let [board [0  "X" "X" "O" "O" "X" "X"  7  8]
          max-mark "X"
          min-mark "O" ]
      (should= {-500 7, 500 8}
        (build-rankings board max-mark min-mark))))

  (it "gives the best move"
    (let [board [0  "X" "X" "O" "O" "X" "X" 7 8]
          max-mark "X"
          min-mark "O" ]
      (should= 8
        (find-best-ranked-move board max-mark min-mark)))))
