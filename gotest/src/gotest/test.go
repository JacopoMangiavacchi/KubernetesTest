package main

import (
    "encoding/json"
    "log"
    "net/http"
    "io"
    "io/ioutil"

    "github.com/gorilla/mux"
)

type Language struct {
	Language  string `json:"language"`
}

type Request struct {
	Url  string `json:"url"`
}

func main() {

    router := mux.NewRouter().StrictSlash(true)
    router.HandleFunc("/language", HandleLanguage).Methods("GET")
    router.HandleFunc("/request", HandleRequest).Methods("POST")

    log.Fatal(http.ListenAndServe(":8040", router))
}

func HandleLanguage(w http.ResponseWriter, r *http.Request) {
    language := Language{Language: "Go"}
    json.NewEncoder(w).Encode(language)
}

func HandleRequest(w http.ResponseWriter, r *http.Request) {
    // Unmarshal the Json Body in a Request type
    var request Request
    body, err := ioutil.ReadAll(io.LimitReader(r.Body, 1048576))
    if err != nil {
        panic(err)
    }
    if err := r.Body.Close(); err != nil {
        panic(err)
    }
    if err := json.Unmarshal(body, &request); err != nil {
        w.Header().Set("Content-Type", "application/json; charset=UTF-8")
        w.WriteHeader(422) // unprocessable entity
        if err := json.NewEncoder(w).Encode(err); err != nil {
            panic(err)
        }
    }

    // Build the request
	req, err := http.NewRequest("GET", request.Url, nil)
	if err != nil {
		log.Fatal("NewRequest: ", err)
		return
	}

	client := &http.Client{}

	// Send an HTTP request and
	// returns an HTTP response
	resp, err := client.Do(req)
	if err != nil {
		log.Fatal("Do: ", err)
		return
	}

	// Defer the closing of the body
	defer resp.Body.Close()

    // Fill the language response with the data from the JSON
	var languareResponse Language

    // Use json.Decode for reading streams of JSON data
    if err := json.NewDecoder(resp.Body).Decode(&languareResponse); err != nil {
        log.Println(err)
    }

    json.NewEncoder(w).Encode(languareResponse)
}

