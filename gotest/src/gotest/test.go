package main

import (
    "encoding/json"
    "log"
    "net/http"

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
    router.HandleFunc("/language", HandleLanguage)
    router.HandleFunc("/request", HandleRequest)

    log.Fatal(http.ListenAndServe(":8040", router))
}

func HandleLanguage(w http.ResponseWriter, r *http.Request) {
    language := Language{Language: "Go"}
    json.NewEncoder(w).Encode(language)
}

func HandleRequest(w http.ResponseWriter, r *http.Request) {
    language := Language{Language: "Go"}
    json.NewEncoder(w).Encode(language)
}

