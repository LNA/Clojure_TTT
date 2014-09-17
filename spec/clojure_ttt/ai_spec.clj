(ns clojure_ttt.ai_spec
  (:require [speclj.core :refer :all]
    [clojure_ttt.ai :refer :all]))

(describe "ai" 
  (it "make an easy move"
    (let [board ["X" 1 2 3 4 5 "X" "X" "X"]]
      (should= 1
       (ai-move board)))))