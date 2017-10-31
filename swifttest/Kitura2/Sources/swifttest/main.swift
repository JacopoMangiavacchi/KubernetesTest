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
    guard let client = KituraKit(baseURL: "http://localhost:8080") else { return }

    client.get("/language") { (languages: [Language]?, error: Error?) in
        guard error == nil else { return }
        guard let languages = languages, languages.count > 0 else { return }
        completion(languages[0], nil)
    }
}

let router = Router()

router.get("/language", handler: languageHandler)
router.post("/request", handler: requestHandler)

Kitura.addHTTPServer(onPort: 8080, with: router)

Kitura.run()