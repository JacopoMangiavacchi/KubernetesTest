#!flask/bin/python
from flask import Flask

app = Flask(__name__)

@app.route('/language')
def index():
    return "Python"

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=8070)
