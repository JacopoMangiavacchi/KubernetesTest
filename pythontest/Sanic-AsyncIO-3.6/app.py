from sanic import Sanic, response
import grequests

app = Sanic(__name__)

@app.route('/language', methods=['GET'])
async def get_language(request):
    return response.json({'language': 'Python'})


@app.route('/request', methods=['POST'])
async def get_language(request):
    if not request.json or not 'url' in request.json:
        return response.text(body= '', status=404)
    url = request.json['url']
    
    result = grequests.map([grequests.get('http://google.com')])
    
    print(result[0].content)
    
    return response.json({'language': 'Python'})


app.run(host="0.0.0.0", port=8070)
