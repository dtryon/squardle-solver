(ns solver.dictionary
  (:require [clojure.java.io :refer [reader]]
            [clojure.instant :as in]
            [clojure.set :refer [intersection]]
            [clojure.string :as str]))


(defn read-file
  "read file line by line"
  [file-name]
  (with-open [rdr (reader file-name)]
    (reduce conj [] (line-seq rdr))))

(defn get-dictionary []
  (->> (read-file "resources/solver/english.csv")
      rest
      (map #(first (str/split % #",")))
      (map #(str/lower-case %))
      (filter #(some? (re-matches #"[a-z]+" %)))
      (group-by count)))

(defn matches? [dictionary-words candidate-words]
  (let [dw (set dictionary-words)
        cw (set candidate-words)]
    (intersection dw cw)))
