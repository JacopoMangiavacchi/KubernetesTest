# swifttest


Local test: 

	node dist/server.js

	curl -H "Content-Type: application/json" -X POST -d '{"url" : "http://localhost:8020/language"}' http://localhost:8020/request



Build docker image:

	 docker build -t nodetest .


Test docker image:

	docker run -p 8091:8090 nodetest

	curl -H "Content-Type: application/json" -X POST -d '{"url" : "http://localhost:8020/language"}' http://localhost:8020/request

	docker ps
	
	docker kill XXXXX


Push docker image on Hub Docker:

	docker login

	docker tag nodetest [USER]/nodetest

	docker push [USER]/nodetest


Test docker Pull:

	docker pull [USER]/nodettest

	docker run -p 8091:8090 [USER]/nodetest

	curl -H "Content-Type: application/json" -X POST -d '{"url" : "http://localhost:8020/language"}' http://localhost:8020/request

	docker ps
	
	docker kill XXXXX

	
Create Kubernetes Pods:

	kubectl config use-context XXXXXXXX (i.e. minikube)

	kubectl run nodetest --image=[USER]/nodetest:latest --port=8080

	kubectl expose deployment nodetest --type=NodePort

	kubectl get pod

	kubectl get service

	kubectl proxy


Bluemix Test:

	curl -H "Content-Type: application/json" -X POST -d '{"url" : "http://localhost:8020/language"}' http://173.193.105.232:31039/request

Minikube test:

	curl $(minikube service nodetest --url)

	minikube dashboard
