# KubernetesTest
Kubernetes polyglot microservices orchestration test (Swift, Node Javascript, Python, Java, Scala, Kotlin, C#, Go, TypeScript)

Implementation of the same Rest microservice in the following languages/platforms

- Swift: Kitura
- Javascript: Node.JS
- TypeScript: Node.JS
- Python: Flask
- Python: Sanic
- Java: Spring
- Kotlin: Spring
- Scala: Spray, Akka
- Go: Gorilla Mux
- C#: .Net WebApi


Each of these services expose the following API

- GET /language
- POST /request  [{"url" : [GET URL]}]


TEST:

    curl -H "Content-Type: application/json" -X POST -d '{"url" : "http://localhost:[PORT]/language"}' http://localhost:[PORT]/request
  
 
Dockers and Kubernetes:

For each platforms configuration files and readme instructions are provided in order to create the container, publish it on registry and create a Kubernetes pod

 
