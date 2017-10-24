var express    = require('express');
var app        = express();
var bodyParser = require('body-parser');
var request    = require('request');

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

var router = express.Router();

router.get('/language', function(req, res) {
    res.json({ language: 'javascript' });   
});


router.post('/request', function(req, res) {
    request(req.body.url, function (error, response, body) {
        if (!error) {
            res.json({ language: JSON.parse(body).language });   
        }
    });
});

app.use('/', router);

app.listen(8090);
console.log('server is listening on port 8090');