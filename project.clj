(defproject clojure_ttt "0.1.0"
  :description "Clojure TTT"
  :dependencies [[org.clojure/clojure "1.4.0"]]
  :profiles {:dev {:dependencies [[speclj "3.0.1"]]}}
  :plugins [[speclj "3.0.1"]]
  :test-paths ["spec"]
  :main clojure_ttt.runner
  :java-source-path "src/")