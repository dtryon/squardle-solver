(ns solver.solver-test
  (:require
  [clojure.test :refer [deftest is]]
  [solver.solver :as sut]))


(deftest test-make-grid
  (do
    (is (= (sut/make-grid 2 ["a" "d" "i" "e"]) [["a" "d"] ["i" "e"]]))))

(deftest test-make-grid3
  (do
    (is (= (sut/make-grid 3 ["x" "r" "r" "e" "a" "e" "t" "l" "i"])
                         [["x" "r" "r"]
                          ["e" "a" "e"]
                          ["t" "l" "i"]] 
                          ))))

(deftest test-get-value
  (do
    (let [grid (sut/make-grid 2 ["a" "d" "i" "e"])]
      (is (= (sut/get-value [0 0] grid) "a"))
      (is (= (sut/get-value [0 1] grid) "i"))
      (is (= (sut/get-value [1 0] grid) "d"))
      (is (= (sut/get-value [1 1] grid) "e")))))

(deftest test-all-points-grid
  (do
    (let [grid (sut/make-grid 2 ["a" "d" "i" "e"])]
      (is (= (sut/all-points-grid grid) [[[0 0] [0 1]] [[1 0] [1 1]]])))))

(deftest test-all-points
  (do
    (let [grid (sut/make-grid 2 ["a" "d" "i" "e"])]
      (is (= (sut/all-points grid) [[0 0] [0 1] [1 0] [1 1]])))))

(deftest test-valid-point
  (do
      (is (not (sut/valid-point [0 2] 1)))
      (is (not (sut/valid-point [2 0] 1)))
      (is (not (sut/valid-point [-1 0] 1)))
      (is (sut/valid-point [0 0] 1))
      (is (sut/valid-point [0 0] 1))
      (is (sut/valid-point [1 0] 1))
      (is (sut/valid-point [0 1] 1))
      (is (sut/valid-point [0 0] 1))))

(deftest test-get-neighbors
  (do
      (is (= (sut/get-neighbors [0 0] [[[0 0] [0 1]] [[1 0] [1 1]]]) [[0 1] [1 0] [1 1]]))
      (is (= (sut/get-neighbors [1 0] [[[0 0] [0 1]] [[1 0] [1 1]]]) [[1 1] [0 0] [0 1]]))))

(deftest test-already-used-point
  (do
      (is (sut/already-used-point [0 1] [[0 0] [0 1] [1 1]]))
      (is (not (sut/already-used-point [1 0] [[0 0] [0 1] [1 1]])))
      (is (sut/already-used-point [0 0] [[0 0] [0 1] [1 1]]))))

(deftest test-get-unused-neighbors
  (do
      (is (= (sut/get-unused-neighbors [0 0] [] [[[0 0] [0 1]] [[1 0] [1 1]]]) [[0 1] [1 0] [1 1]]))
      (is (= (sut/get-unused-neighbors [1 0] [[0 0]] [[[0 0] [0 1]] [[1 0] [1 1]]]) [[1 1] [0 1]]))))

(deftest test-get-point-paths
  (do
      (is (= (count (sut/get-point-paths [1 0] [[[0 0] [0 1]] [[1 0] [1 1]]])) 6))))

(deftest test-all-paths
  (do
    (let [grid (sut/make-grid 2 ["a" "d" "i" "e"])]
      (is (= (count (sut/all-paths grid)) 24)))))

(deftest test-all-paths3
  (do
    (let [grid (sut/make-grid 3 ["x" "r" "r" "e" "a" "e" "t" "l" "i"])]
      (is (= (count (sut/all-paths grid)) 4224)))))

(deftest test-paths-words
  (do
    (let [grid (sut/make-grid 2 ["a" "d" "i" "e"])
          paths (sut/all-paths grid)]
      (is (= (count (sut/get-paths-words grid paths)) 24)))))

(deftest test-get-words
  (do
    (let [grid (sut/make-grid 2 ["a" "d" "i" "e"])]
      (is (= (sut/get-words grid 4) [{4 #{"idea"}}])))))

(deftest test-get-words2
  (do
    (let [grid (sut/make-grid 2 ["i" "t" "m" "e"])]
      (is (= (sut/get-words grid 3) [{3 #{"tie" "met"}} {4 #{"item" "time" "mite" "emit"}}])))))

(deftest test-squaredle
  (do 
    (is (= (sut/squaredle 3 ["x" "r" "r" "e" "a" "e" "t" "l" "i"] 4) nil))))

(deftest test-squaredle
  (do 
    (is (= (sut/squaredle 3 ["t" "m" "r" "e" "n" "a" "w" "g" "g"] 4) nil))))

(deftest test-squaredle
  (do 
    (is (= (sut/squaredle 4 ["b" "o" "t" "h" "w" "h" "i" "f" "e" "r" "d" "n" "t" "l" "a" "g"] 4) nil))))


