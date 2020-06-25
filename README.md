# clj-web-app

Sample rest-api for managing book inventory in Clojure using yada library.

## Prerequisite
System should have below tools installed.

 Java 8+
 
 Leiningen
 
 ### Usage
  command for bringing the server up
  ```
  lein run  
  ```

## Rest ENDPOINTS

### 1. To add books into the inventory

URL : http://localhost:8032/books

CURL : ```curl -X POST http://localhost:8032/books -d '{"id": 1, "title": "Brave Clojure", "author": "Daniel Higginbotham", "prize": 1000}' -H 'Content-Type: application/json' ```

### 2. To fetch all books

URL : http://localhost:8032/books

CURL: ```curl -X GET http://localhost:8032/books```

### 3. To fetch book by id

URL : http://localhost:8032/books/{id}

CURL: ```curl -X GET http://localhost:8032/books/1```

### 4. To Update book 

URL : http://localhost:8032/books/{id}

CURL: ```curl -X PUT http://localhost:8032/books/2 -d '{"title": "book1", "author": "mayur pandey", "prize": 103}' -H 'Content-Type: application/json'```

### 5. TO DELETE BOOK

URL : http://localhost:8032/books/{id}

CURL: ```curl -X DELETE http://localhost:8032/books/1```