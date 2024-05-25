(ns solver.solver
  (:require [clojure.java.io :refer [reader]]
            [clojure.instant :as in]
            [clojure.string :as str]
            [clojure.pprint :refer [pprint]]
            [solver.util :refer [in?]]
            [solver.dictionary :refer [get-dictionary is-match?]]))

(defn make-grid [size letters]
    (partition size letters))

(defn get-value [[x y] grid]
  (nth (nth grid y) x))

(defn valid-point [[x y] maximum]
  (and
    (<= x maximum)
    (<= y maximum)
    (>= x 0)
    (>= y 0)))

(defn already-used-point [point path]
  (in? path point))

(defn get-neighbors [[x y] idx-grid]
  (let [maximum (- (count idx-grid) 1)
        all-neighbors [
                       [x (- y 1)]
                       [x (+ y 1)]
                       [(- x 1) y]
                       [(+ x 1) y]
                       [(- x 1) (+ y 1)]
                       [(+ x 1) (- y 1)]
                       [(+ x 1) (+ y 1)]
                       [(- x 1) (- y 1)]]]
    (filter #(valid-point % maximum) all-neighbors)))

(defn get-unused-neighbors [point path idx-grid]
  (let [neightbors (get-neighbors point idx-grid)]
    (filter #(not (already-used-point % path)) neightbors)))

(defn all-points [grid]
  (let [size (count grid)
        indexes (for [x (range size)
                      y (range size)]
                  [x y])]
    indexes))

(defn all-points-grid [grid]
  (let [size (count grid)]
    (partition size (all-points grid))))

(defn get-paths [point idx-grid path all-paths]
  (let [new-path (conj path point)
        neightbors (get-unused-neighbors point new-path idx-grid)]
    (cond (empty? neightbors) [new-path] 
          :else (concat all-paths (mapcat #(get-paths % idx-grid new-path all-paths) neightbors)
                                       ))))

(defn get-point-paths [point idx-grid]
  (let [size (count idx-grid)
        max-path-length (* size size)
        paths (get-paths point idx-grid [] [])]
    paths))

(defn all-paths [grid]
  (let [points (all-points grid)
        idx-grid (all-points-grid grid)]
    (mapcat #(get-point-paths % idx-grid) points)))

(defn get-paths-words [grid paths]
  (map (fn [path] 
         (apply str (map (fn [[x y]] (nth (nth grid x) y)) path))) paths))

(defn get-words [grid min-length]
  (let [size (count grid)
        paths (all-paths grid)
        words (get-paths-words grid paths)
        dictionary (get-dictionary)]
    (for [n (range min-length (inc (* size size)))]
        { n (->> words
              (filter #(< n (count %)))
              (map #(subs % 0 n))
              (filter #(is-match? dictionary % n))
              set) })))

(defn squaredle [size letters min-length]
  (let [grid (make-grid size letters)]
    (pprint (get-words grid min-length))))






















