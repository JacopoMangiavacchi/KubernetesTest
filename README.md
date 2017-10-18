# KubernetesTest
Kubernetes polyglot microservices orchestration test (Swift, Node Javascript, Python, Java, Scala, Kotlin, C#, Go, TypeScript)

Implementation of the same Rest microservice in the following languages/platforms

- Swift: Kitura
- Javascript: Node.JS
- Python: Flask
- Python: Sanic
- Java: Spring
- Scala: Spray, Akka
- Kotlin: Spring
- C#: .Net WebApi
- TypeScript: Node.JS
- Go


Each of these services expose the following API

- GET /language
- POST /request  [{"url" : [GET URL]}]


TEST:

  curl -H "Content-Type: application/json" -X POST -d '{"url" : "http://localhost:[PORT]/language"}' http://localhost:[PORT]/request
  
 
 