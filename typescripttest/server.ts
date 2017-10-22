import * as express from 'express'

class App {
  public express: express.Express

  constructor () {
    this.express = express()
    this.mountRoutes()
  }

  private mountRoutes (): void {
    const router = express.Router()
    router.get('/language', (req, res) => {
      res.json({
        language: 'TypeScript'
      })
    })
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
