(ns culture.facts-test
  (:require [clojure.edn :as edn]
            [clojure.string :as str]
            [clojure.test :refer [deftest is]]
            [culture.facts :as facts]))

(deftest new-delhi-has-culture-basis
  (let [sb (facts/spec-basis "new-delhi")]
    (is (= 9 (count sb)))
    (is (= (count sb) (count (set (map :culture/id sb)))))
    (is (every? #(str/starts-with? (:culture/url %) "https://") sb))
    (is (every? #(= "new-delhi" (:culture/municipality %)) sb))
    (is (every? #(= "IND" (:culture/country %)) sb))
    (is (every? #(seq (:culture/summary %)) sb))
    (is (every? #(string? (:culture/retrieved-at %)) sb))))

(deftest unknown-municipality-has-no-basis
  (is (nil? (facts/spec-basis "mumbai")))
  (is (nil? (facts/spec-basis "zzz"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["new-delhi" "mumbai"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["mumbai"] (:missing-municipalities c)))))

(deftest by-kind-filters
  (is (= 4 (count (facts/by-kind "new-delhi" :dish))))
  (is (= ["new-delhi.beverage.rooh-afza"]
         (mapv :culture/id (facts/by-kind "new-delhi" :beverage))))
  (is (empty? (facts/by-kind "new-delhi" :craft)))
  (is (empty? (facts/by-kind "mumbai" :dish))))

(deftest tx-file-matches-catalog
  (let [tx (edn/read-string (slurp "data/culture-tx.edn"))
        flat (mapcat val (sort-by key facts/catalog))]
    (is (= (vec flat) (vec tx)))))
