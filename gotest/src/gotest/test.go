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
        panic(err)
    }

	req, err := http.NewRequest("GET", request.Url, nil)
	if err != nil {
        panic(err)
	}

	client := &http.Client{}

	resp, err := client.Do(req)
	if err != nil {
        panic(err)
	}

	defer resp.Body.Close()

	var languareResponse Language

    if err := json.NewDecoder(resp.Body).Decode(&languareResponse); err != nil {
        panic(err)
    }

    json.NewEncoder(w).Encode(languareResponse)
}