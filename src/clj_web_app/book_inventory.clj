(ns clj-web-app.book-inventory)

;; atom is used to storing the book record
(def books (atom []))

(defn- do-delete [ctx book-id]
  (reset! books (remove #(= book-id (:id %)) @books))
  (assoc (:response ctx) :status 200 :body "Book Deleted"))

(defn- do-update [ctx book-id]
  (do-delete ctx book-id)
  (swap! books conj (assoc (:body ctx) :id book-id))
  (assoc (:response ctx) :status 200 :body "Book Updated"))

(defn- do-insert [ctx book]
  (swap! books conj book)
  (assoc (:response ctx) :status 201 :body "Book Added"))

(defn fetch-all-books []
  @books)

(defn fetch-book-by-id [book-id]
  (->> @books
      (filter #(= book-id (:id %)))
      first))

(defn add-book [ctx]
  (let [book (:body ctx)
        existing-book (fetch-book-by-id (:id book))]
    (if (empty? existing-book)
      (do-insert ctx book)
      (assoc (:response ctx) :status 400 :body "Book id already used"))))

(defn update-book [ctx]
  (let [book-id (-> ctx :parameters :path :book-id)
        existing-book (fetch-book-by-id book-id)]
    (if (empty? existing-book)
      (assoc (:response ctx) :status 404 :body "Book Id provided for update doesn't exist")
      (do-update ctx book-id))))

(defn delete-book [ctx]
  (let [book-id (-> ctx :parameters :path :book-id)
        existing-book (fetch-book-by-id book-id)]
    (if (empty? existing-book)
      (assoc (:response ctx) :status 404 :body "Book Id provided for deletion doesn't exist")
      (do-delete ctx book-id))))
