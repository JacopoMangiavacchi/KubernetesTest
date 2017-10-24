from sanic import Sanic, response
import requests

app = Sanic(__name__)

@app.route('/language', methods=['GET'])
async def get_language(request):
    return response.json({'language': 'Python'})

@app.route('/request', methods=['POST'])
async def get_language(req):
    if not req.json or not 'url' in req.json:
        return response.text(body= '', status=404)
    url = req.json['url']
    result = requests.get(url)

    return response.json({'language': 'Python'})

app.run(host="0.0.0.0", port=8070, workers=4)