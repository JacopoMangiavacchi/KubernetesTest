# swifttest


Setup Python + Flask:

	virtualenv flask

	flask/bin/pip install flask


Local test: 

	python app.py

	curl -H "Content-Type: application/json" -X POST -d '{"url" : "http://localhost:8080/language"}' http://localhost:8080/request

