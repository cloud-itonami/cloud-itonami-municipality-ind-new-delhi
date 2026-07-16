(ns ordinance.facts-test
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest is]]
            [ordinance.facts :as facts]))

(deftest new-delhi-has-spec-basis
  (let [sb (facts/spec-basis "new-delhi")]
    (is (= 2 (count sb)))
    (is (every? #(str/includes? (:ordinance/url %) "ndmc.gov.in") sb))))

(deftest unknown-municipality-has-no-spec-basis
  (is (nil? (facts/spec-basis "mumbai")))
  (is (nil? (facts/spec-basis "zzz"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["new-delhi" "mumbai"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["mumbai"] (:missing-municipalities c)))))

(deftest by-topic-filters
  (is (= ["new-delhi.ndmc-act-1994"]
         (mapv :ordinance/id (facts/by-topic "new-delhi" :governance))))
  (is (empty? (facts/by-topic "new-delhi" :labor)))
  (is (empty? (facts/by-topic "mumbai" :governance))))
