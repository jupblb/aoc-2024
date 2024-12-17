(require '[clojure.java.io :as io])
(require '[clojure.string :as str])

(defn read-numbers [filename]
  (with-open [reader (io/reader filename)]
    (let [numbers (doall (line-seq reader))]
      (loop [lines numbers
             first-numbers []
             second-numbers []]
        (if (empty? lines)
          [first-numbers second-numbers]
          (let [[a b]
                (map #(Integer/parseInt %) (str/split (first lines) #"\s+"))]
            (recur (rest lines)
                   (conj first-numbers a)
                   (conj second-numbers b))))))))

(let [[first-list second-list] (read-numbers (first *command-line-args*))
      first-list-indexes (map last (sort-by last (map-indexed vector first-list)))
      second-list-indexes (map last (sort-by last (map-indexed vector second-list)))
      distances (map Math/abs (map - first-list-indexes second-list-indexes))
      similarity-scores (map (fn [n] (* n (count (filter #(= % n) second-list)))) first-list)]
  (println (reduce + distances))
  (println (reduce + similarity-scores)))
