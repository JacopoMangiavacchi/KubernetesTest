import Foundation
import Kitura
import KituraContracts
import KituraKit

struct Language : Codable {
    var language: String
}

struct Request : Codable {
    var url: String
}

func languageHandler(completion: ([Language]?, RequestError?) -> Void ) -> Void {
    let languages: [Language] = [Language(language: "Swift")]
    completion(languages, nil)
}

func requestHandler(request: Request, completion: @escaping (Language?, RequestError?) -> Void ) -> Void {
    if let url = URL(string: request.url), let scheme = url.scheme, let host = url.host, let port = url.port  {
        guard let client = KituraKit(baseURL: "\(scheme)://\(host):\(port)") else { return }

        client.get(url.path) { (languages: [Language]?, error: Error?) in
            guard error == nil else { return }
            guard let languages = languages, languages.count > 0 else { return }
            completion(languages[0], nil)
        }
    }
}

let router = Router()

router.get("/language", handler: languageHandler)
router.post("/request", handler: requestHandler)

Kitura.addHTTPServer(onPort: 8080, with: router)

Kitura.run()