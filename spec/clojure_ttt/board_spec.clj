(ns clojure_ttt.board_spec
  (:require [speclj.core :refer :all]
            [clojure_ttt.board  :refer :all]))
(describe "board"
          (it "has 9 spaces"
              (should= [0 1 2 3 4 5 6 7 8] (board))))

(describe "make-move-on"
          (it "makes a move on the board"
              (let [board [0 1 2 3 4 5 6 7 8]]
                (should= ["X" 1 2 3 4 5 6 7 8]
                         (make-move-on board 0 "X")))))