import Foundation
import Kitura
import SwiftyJSON

// Create a new router
let router = Router()

// Handle HTTP GET requests to /
router.get("/language") { request, response, next in
    response.send(json: JSON(["language" : "Swift"]))
    next()
}

// Handle HTTP POST requests to /
router.all("/request", middleware: BodyParser())
router.post("/request") { request, response, next in
    guard let parsedBody = request.body else {
        next()
        return
    }

    switch(parsedBody) {
    case .json(let jsonBody):
            if let urlString = jsonBody["url"].string, let url = URL(string: urlString) {
                var request = URLRequest(url: url)
                
                request.httpMethod = "GET"
                
                let sessionConfiguration = URLSessionConfiguration.default;
                let urlSession = URLSession(configuration:sessionConfiguration, delegate: nil, delegateQueue: nil)
                let dataTask = urlSession.dataTask(with: request, completionHandler: { (data, response2, error) in
                    guard let data = data, error == nil else {
                        print("Error: \(String(describing: error?.localizedDescription))")
                        return
                    }
                    
                    if let httpStatus = response2 as? HTTPURLResponse {
                        if httpStatus.statusCode == 200 {
                            do {
                                let jsonResponse = JSON(data: data)
                                try response.status(.OK).send(json: JSON(["language" : jsonResponse["language"]])).end()
                            }
                            catch {
                            }
                        }
                    }

                    next()
                })
                
                dataTask.resume()
            }
    default:
        next()
        break
    }
}

// Add an HTTP server and connect it to the router
Kitura.addHTTPServer(onPort: 8080, with: router)

// Start the Kitura runloop (this call never returns)
Kitura.run()
