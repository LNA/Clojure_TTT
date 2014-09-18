(ns clojure_ttt.runner_spec
  (:require [speclj.core :refer :all]
    [clojure_ttt.runner  :refer :all]
    [clojure_ttt.board         :as p]
    [clojure_ttt.ui            :as u]
    [clojure_ttt.game_rules    :as r]
    [clojure_ttt.ai            :as a]))

(describe "Runer" 
  (it "gets an ai move"
    (let [board (vec (range 9))
          current-type  "a"]
          (should= 0
            (get-move board current-type)))))