# pythontest


Use Python 3.6 via Anaconda and install Sanic for Async IO support

	pip install sanic
	pip install request


Local test: 

	python app.py

	curl -H "Content-Type: application/json" -X POST -d '{"url" : "http://localhost:8080/language"}' http://localhost:8080/request

