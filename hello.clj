; https://clojure.org/guides/learn/functions#_test_your_knowledge

(defn greet []
  (println "Hello, World!"))

(def greet1 (fn [] (println "Hello, World!")))
(def greet2 #(println "Hello, World!"))

(defn greeting
  ([] "Hello, World!")
  ([name] (str "Hello, " name "!"))
  ([word1, word2] (str word1 ", " word2 "!")))

;; For testing
(assert (= "Hello, World!" (greeting)))
(assert (= "Hello, Clojure!" (greeting "Clojure")))
(assert (= "Good morning, Clojure!" (greeting "Good morning" "Clojure")))

(defn do-nothing [x] x)

(defn always-thing [& _] 100)
(defn make-thingy [x] (fn [& _] x))

;; Tests
(let [n (rand-int Integer/MAX_VALUE)
      f (make-thingy n)]
  (assert (= n (f)))
  (assert (= n (f 123)))
  (assert (= n (apply f 123 (range)))))

(defn triplicate [f] (f) (f) (f))

(defn opposite [f]
  (fn [& args] (not (apply f args))))

(defn triplicate2 [f & args]
  (triplicate (apply f args)))

(assert (= (Math/cos Math/PI) -1.0))
(assert (= (+
            (Math/pow (Math/sin Math/PI) 2)
            (Math/pow (Math/cos Math/PI) 2)) 1.0))

(defn http-get [url]
  (let [url (java.net.URL. url)
        stream (.openStream url)] (slurp stream)))

(assert (.contains (http-get "https://www.w3.org") "html"))

(defn one-less-arg [f x]
  (fn [& args] (apply f x args)))

(defn two-fns [f g]
  (fn [x] (f (g x))))
