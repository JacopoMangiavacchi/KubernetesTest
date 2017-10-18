# scalatest

Pre-requisite

	brew install scala
	brew install sbt

Local test: 

	sbt run
	
	curl -H "Content-Type: application/json" -X POST -d '{"url" : "http://localhost:8050/language"}' http://localhost:8050/request

