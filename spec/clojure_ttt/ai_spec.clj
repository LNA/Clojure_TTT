(ns clojure_ttt.ai_spec
  (:require [speclj.core :refer :all]
    [clojure_ttt.ai :refer :all]))

(describe "ai"
  ; (it "gives the rank of a specific move"
  ;   (let [max-winning-board ["X" "X" "X" 3 4 5 6 7 8]
  ;         min-winning-board ["O" "O" "O" 3 4 5 6 7 8]
  ;         tie-board         ["X" "X" "O" "O" "O" "X" "X" "O" "O"]]
  ;     (should= 500
  ;       (rank max-winning-board "X" "O" 0))
  ;     (should= -500
  ;       (rank min-winning-board "X" "O" 0))
  ;     (should= 0
  ;       (rank tie-board "X" "O" 0))))

  ; (it "ranks through move-on-open-space"
  ;   (let [mark "X"
  ;         opponent "O"
  ;         space  0
  ;         depth  0]
  ;     (should= 500
  ;       (move-on-open-space space [0 "X" "X" 3 4 5 6 7 8] mark opponent depth))
  ;     (should= -500
  ;       (move-on-open-space space [0 "X" "X" 3 4 5 6 7 8] opponent mark depth))
  ;     (should= 0
  ;       (move-on-open-space space ["X" "X" "O" "O" "O" "X" "X" "O" "O"] mark opponent depth))))

  ; (it "ranks through rank-open-spaces" 
  ;   (let [mark     "X"
  ;         opponent "O"
  ;         depth     0]
  ;     (should= [500 -500 -500 -500 -500 -500 -500]
  ;       (rank-open-spaces [0 "X" "X" 3 4 5 6 7 8] mark opponent depth))
  ;     (should= [-500 -500 -500 -500 -500 -500 -500]
  ;       (rank-open-spaces [0 "X" "X" 3 4 5 6 7 8] opponent mark depth))
  ;     (should= []
  ;       (rank-open-spaces ["X" "X" "O" "O" "O" "X" "X" "O" "O"] mark opponent depth))))
               
  ; (it "gives the open spaces" 
  ;   (let [board [0 "X" "X" "O" "O" "X" "X" 7 8]]
  ;     (should= [0 7 8]
  ;       (get-open-spaces board))))

  ; (it "gives a hash map of open ranked spaces without multiple rankings"
  ;   (let [board [0  "X" "X" "O" "O" "X" "X"  7  8]
  ;         max-mark "X"
  ;         min-mark "O" 
  ;         depth     0]
  ;     (should= {-500 7, 500 8}
  ;       (build-rankings board max-mark min-mark depth))))

  ; (it "gives the best ranked move"
  ;   (let [board [0  "X" "X" "O" "O" "X" "X" 7 8]
  ;         max-mark "X"
  ;         min-mark "O" 
  ;         depth   0]
  ;     (should= 8
  ;       (find-best-ranked-move board max-mark min-mark 0))))


  ; (it "plays the last open move"
  ;   (let [board [0  "X" "X" "O" "O" "X" "X" "O"  "X"]
  ;         max-mark "X"
  ;         min-mark "O" 
  ;         depth     0]
  ;     (should= 0
  ;       (find-best-ranked-move board max-mark min-mark depth))))

  ; (it "adds the next open space to a board" 
  ;    (let [board ["O" 1  2 3 "X" 5 6  7 "X"]
  ;         max-mark "X"
  ;         min-mark "O" ]
  ;         (should= ["O" "X" 2 3 "X" 5 6 7 "X"]
  ;           (add-next-open-space board max-mark min-mark))))

  ; (it "gives the best move of both scores"
  ;     (let [board ["O" "X" "X" 
  ;                  "O" "O" "X" 
  ;                   6  "X" "X"]

  ;           max-mark "X"
  ;           min-mark "O" 
  ;           depth     0]
  ;       (should= 6
  ;         (return-best-move board max-mark min-mark depth))))

  ; (it "THIS should not get to this function: gives the best move of a full board"
  ;     (let [board ["O" "X" "X" 
  ;                  "O" "O" "X" 
  ;                   "X" "X" "X"]

  ;           max-mark "X"
  ;           min-mark "O" 
  ;           depth     0]
  ;       (should= 6
  ;         (return-best-move board max-mark min-mark depth))))

  ; (it "sets the game state for a full board"
  ;     (let [board ["O" "X" "X" 
  ;                  "O" "O" "X" 
  ;                   "X" "X" "X"]

  ;           max-mark "X"
  ;           min-mark "O" 
  ;           depth     0]
  ;       (should= 6
  ;         (game-state board max-mark min-mark depth))))

  ; (it "builds a board with two open spaces"
  ;   (let [board [0  1   "X"
  ;               "O" "X" "X"
  ;               "O" "X" "O"]
  ;         max-mark "X"
  ;         min-mark "O"
  ;         depth 0]
  ;         (should= 1
  ;           (return-best-move board max-mark min-mark depth))))

  ; (it "stores the best move and its score"
  ;   (let [board [0  "X" "X" "O" "O" "X" "X"  7  8]
  ;         max-mark "X"
  ;         min-mark "O" 
  ;         depth     0]
  ;     (should= {500 8}
  ;       (store-move-and-score board max-mark min-mark depth))))

  ; (it "does not return nil when storing a score" 
  ;   (let [board ["X" "X" "X" "O" "O" "X" "X" "O" "X"]
  ;         max-mark "X"
  ;         min-mark "O" ]
  ;     (should= {}
  ;       (store-move-and-score board max-mark min-mark 0))))

  ; (it "finds the next open space"
  ;   (let [open-spaces [0 7 8]]
  ;     (should= 7
  ;       (next-open-space open-spaces))))

  ; (it "returns move from stored moves"
  ;   (let [space {500 8}]
  ;     (should= 8
  ;       (return-move space))))

  ; (it "goes to depth 2"
  ;   (let [board [ 0   1   "X" 
  ;                "X" "O" "O" 
  ;                "X" "X" "O"]
  ;         max-mark "X"
  ;         min-mark "O" 
  ;         best-move (ai-move board max-mark min-mark)]
  ;     (should-contain best-move [0 2])))

  ; (it "gives the best ranked move"
  ;   (let [board ["O" 1  2 
  ;                 3 "X" 5 
  ;                 6  7 "X"]
  ;         max-mark "X"
  ;         min-mark "O" 
  ;         depth  0
  ;         best-move (ai-move board max-mark min-mark)]
  ;     (should-contain best-move [1 2 6])))

  (it "MINIMAX"
    (let [board [0  "X"  2 
                "O" "O" "X" 
                "X" "O" "X"]
          max-mark "X"
          min-mark "O" ]
      (should= 0
        (minimax board max-mark min-mark 0)))))

  ; (it "finds the best score" 
  ;   ((let [new-score {500 8}
  ;          old-score {600 7}]
  ;          (should= {600 7}
  ;           (find-best-score new-score old-score))))))




