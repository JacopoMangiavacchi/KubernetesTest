package main

import (
    "fmt"
    "log"
    "net/http"

    "github.com/gorilla/mux"
)

func main() {

    router := mux.NewRouter().StrictSlash(true)
    router.HandleFunc("/language", Language)
    router.HandleFunc("/request", Request)

    log.Fatal(http.ListenAndServe(":8040", router))
}

func Language(w http.ResponseWriter, r *http.Request) {
    fmt.Fprintln(w, "Go")
}

func Request(w http.ResponseWriter, r *http.Request) {
    fmt.Fprintln(w, "Go")
}

