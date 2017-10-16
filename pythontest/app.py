#!flask/bin/python
from flask import Flask, jsonify, abort, request

app = Flask(__name__)

@app.route('/language', methods=['GET'])
def get_language():
    return jsonify({'language': 'Python'})


@app.route('/request', methods=['POST'])
def create_request():
    if not request.json or not 'url' in request.json:
        abort(400)
    return jsonify({'language': 'Python'})

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=8070)
