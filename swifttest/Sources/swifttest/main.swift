import Foundation
import Kitura

struct Language : Codable {
    var language: String
}

struct Request : Codable {
    var url: String
}

let router = Router()

router.get("/language") { request, response, next in
    let language = Language(language: "Swift")

    response.send(data: try JSONEncoder().encode(language))
    next()
}

router.post("/request") { request, response, next in
    do {
        var data = Data()
        if try request.read(into: &data) > 0 {
            let requestData: Request = try JSONDecoder().decode(Request.self, from: data)
            var req = URLRequest(url: URL(string: requestData.url)!)
            req.httpMethod = "GET"
            
            let dataTask = URLSession.shared.dataTask(with: req, completionHandler: { (data, getResponse, error) in
                guard let data = data, error == nil else {
                    print("Error: \(String(describing: error?.localizedDescription))")
                    return
                }
                
                if let httpStatus = getResponse as? HTTPURLResponse {
                    if httpStatus.statusCode == 200 {
                        do {
                            try response.status(.OK).send(data: data).end()
                            // let language = try JSONDecoder().decode(Language.self, from: data)
                            // try response.status(.OK).send(data: JSONEncoder().encode(language)).end()
                        }
                        catch {
                        }
                    }
                }

                next()
            })
            
            dataTask.resume()
        }
    }
    catch {
    }
}

router.all("/requestParser", middleware: BodyParser())
router.post("/requestParser") { request, response, next in
    guard let parsedBody = request.body else {
        next()
        return
    }

    switch(parsedBody) {
    case .json(let jsonBody):
            if let urlString = jsonBody["url"].string, let url = URL(string: urlString) {
                var request = URLRequest(url: url)
                request.httpMethod = "GET"
                
                let dataTask = URLSession.shared.dataTask(with: request, completionHandler: { (data, getResponse, error) in
                    guard let data = data, error == nil else {
                        print("Error: \(String(describing: error?.localizedDescription))")
                        return
                    }
                    
                    if let httpStatus = getResponse as? HTTPURLResponse {
                        if httpStatus.statusCode == 200 {
                            do {
                                try response.status(.OK).send(data: data).end()
                                // let language = try JSONDecoder().decode(Language.self, from: data)
                                // try response.status(.OK).send(data: JSONEncoder().encode(language)).end()
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

struct Name: Codable {
   let name: String
}

router.post("/") { request, response, next in
    do {
        var data = Data()
        if try request.read(into: &data) > 0 {
            let name: Name = try JSONDecoder().decode(Name.self, from: data)
            try response.status(.OK).send(data: JSONEncoder().encode(name)).end()
        }
    }
    catch {
    }
}





Kitura.addHTTPServer(onPort: 8080, with: router)

Kitura.run()