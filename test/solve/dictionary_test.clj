(ns solver.dictionary-test
  (:require
  [clojure.test :refer [deftest is]]
  [solver.dictionary :as sut]))


(deftest test-get-dictionary
  (do
    (let [dictionary (sut/get-dictionary)]
      (is (= (count (get dictionary 3)) 3882))
      (is (= (count (get dictionary 4)) 13371)))))

(deftest test-matches?
  (do
    (let [dictionary (sut/get-dictionary)]
      (is (= (sut/matches? (get dictionary 8) #{"retailer"}) #{"retailer"})))))

(deftest test-matches4?
  (do
    (let [dictionary (sut/get-dictionary)]
      (is (= (sut/matches? (get dictionary 4) ["asdf" "item" "wert" "qwer" "base"]) #{"wert" "item" "base"})))))
