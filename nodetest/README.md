# swifttest


Local test: 

	node server.js

	curl -H "Content-Type: application/json" -X POST -d '{"url" : "http://localhost:8090/language"}' http://localhost:8090/request



Build docker image:

	 docker build -t nodetest .


Test docker image:

	docker run -p 8091:8090 nodetest

	curl -H "Content-Type: application/json" -X POST -d '{"url" : "http://localhost:8090/language"}' http://localhost:8091/request

	docker ps
	
	docker kill XXXXX

