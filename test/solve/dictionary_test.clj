(ns solver.dictionary-test
  (:require
  [clojure.test :refer [deftest is]]
  [solver.dictionary :as sut]))


(deftest test-get-dictionary
  (do
    (let [dictionary (sut/get-dictionary)]
      (is (= (count dictionary) 169814)))))

(deftest test-is-match?
  (do
    (let [dictionary (sut/get-dictionary)]
      ; (is (not (sut/is-match? dictionary "abode" 4)))
      ; (is (sut/is-match? dictionary "idea" 4))
      (is (sut/is-match? dictionary "retailer" 8)))))
