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

    log.Printf(request.Url)


    language := Language{Language: "Go"}
    json.NewEncoder(w).Encode(language)
}

