(require '[clojure.java.io :as io])
(require '[clojure.string :as str])

(defn reports [filename]
  (with-open [reader (io/reader filename)]
    (let [lines (line-seq reader)]
      (loop [lines lines
             reports []]
        (if (empty? lines)
          reports
          (let [report (map #(Integer/parseInt %) (str/split (first lines) #"\s+"))]
            (recur (rest lines)
                   (conj reports report))))))))

(defn is-sorted [report]
  (or (apply < report) (apply > report)))

(defn differs-ok [report]
  (every?
   (fn [[a b]]
     (and (>= (Math/abs (- a b)) 1) (<= (Math/abs (- a b)) 3)))
   (partition 2 1 report)))

(defn is-valid-1 [report]
  (and (is-sorted report) (differs-ok report)))

(defn remove-one [report]
  (loop [i 0
         reports []]
    (if (< i (count report))
      (recur
       (+ i 1)
       (conj reports (concat (take i report) (drop (+ i 1) report))))
      reports)))

(defn is-valid-2 [report]
  (or
   (is-valid-1 report)
   (some is-valid-1 (remove-one report))))

(println (count (filter is-valid-1 (reports (first *command-line-args*)))))
(println (count (filter is-valid-2 (reports (first *command-line-args*)))))

