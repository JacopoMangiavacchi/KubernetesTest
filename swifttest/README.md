# swifttest


Local Build:

	swift build


Local test: 

	.build/x86_64-apple-macosx10.10/debug/swifttest

	curl -H "Content-Type: application/json" -X POST -d '{"url" : "http://localhost:8080/language"}' http://localhost:8080/request



Build docker image:

	 docker build -t swifttest .


Test docker image:

	docker run -p 8081:8080 swifttest

	curl -H "Content-Type: application/json" -X POST -d '{"url" : "http://localhost:8080/language"}' http://localhost:8081/request

	docker ps
	
	docker kill XXXXX


Push docker image on Hub Docker:

	docker login

	docker tag swifttest [USER]/swifttest

	docker push [USER]/swifttest


Test docker Pull:

	docker pull [USER]/swifttest

	docker run -p 8081:8080 [USER]/swifttest

	curl -H "Content-Type: application/json" -X POST -d '{"url" : "http://localhost:8080/language"}' http://localhost:8081/request

	docker ps
	
	docker kill XXXXX

	
Create Kubernetes Pods:

	kubectl config use-context XXXXXXXX (i.e. minikube)

	kubectl run swifttest --image=[USER]/swifttest:latest --port=8080

	kubectl expose deployment swifttest --type=NodePort

	kubectl get pod

	kubectl proxy


Bluemix Test:

	curl -H "Content-Type: application/json" -X POST -d '{"url" : "http://localhost:8080/language"}' http://173.193.105.232:32638/request


Minikube test:

	curl $(minikube service swifttest --url)

	minikube dashboard
