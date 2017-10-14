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

	docker kill swiftest


Push docker image on Hub Docker:

	docker login

	docker tag swifttest [USER]/swifttest

	docker push [USER]/swifttest



	