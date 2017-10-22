import * as express from 'express'
import * as bodyParser from "body-parser"
import * as request from 'request'

class Language {
  language: string

  constructor (language: string) {
    this.language = language
  }
}

interface Request {
  url: string
}

class App {
  public express: express.Express

  constructor () {
    this.express = express()
    this.mountRoutes()
  }

  private mountRoutes (): void {
    const router = express.Router()

    router.get('/language', (req, res) => {
      let language = new Language("TypeScript")

      res.json(language)
    })

    router.post('/request', (req, res) => {
      let passedRequest: Request = req.body as Request

      request(passedRequest.url, (error: any, response: any, body: any) => {
          if (!error) {
            let language: Language = JSON.parse(body) as Language
            res.json(language);   
          }
      });
    })

    this.express.use(bodyParser.json())
    this.express.use('/', router)
  }

  public listen(port: number): void {
    this.express.listen(port, (err: any) => {
      if (err) {
        return console.log(err)
      }
    
      return console.log(`server is listening on ${port}`)
    })
  }
}

let app = new App()

app.listen(8020)
