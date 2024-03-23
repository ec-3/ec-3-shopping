package main

import (
	"log"
	"net/http"
	"server/api"
	"strings"
	"time"

	"github.com/gorilla/mux"
)

// TODO:
// 1. Run this as WebService. Https.
// 2. Send email.
// 3. Create COSMOS DB.
// 4. Order management. Anthentication.

func main() {
	apiServer, err := api.NewAPI()
	if err != nil {
		log.Fatalf("Failed to create api %s", err)
	}
	r := mux.NewRouter()
	r.Use(CORSMiddleware)
	r.Methods(http.MethodPost).Path("/api/order").HandlerFunc(apiServer.CreateOrder)
	r.Methods(http.MethodGet).Path("/hello").HandlerFunc(func(w http.ResponseWriter, r *http.Request) { w.Write([]byte("hello")) })
	srv := &http.Server{
		Handler: r,
		Addr:    "0.0.0.0:8000",
		// Good practice: enforce timeouts for servers you create!
		WriteTimeout: 15 * time.Second,
		ReadTimeout:  15 * time.Second,
	}

	log.Fatal(srv.ListenAndServe())
}

var AllowedMethods = []string{"GET", "POST", "PUT", "DELETE", "OPTIONS"}

func CORSMiddleware(next http.Handler) http.Handler {
	return http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		w.Header().Set("Access-Control-Allow-Origin", "*")
		w.Header().Set("Access-Control-Allow-Methods", strings.Join(AllowedMethods, ","))
		w.Header().Set("Access-Control-Allow-Headers", "*")
		if r.Method == "OPTIONS" {
			w.WriteHeader(http.StatusOK)
			return
		}
		next.ServeHTTP(w, r)
	})
}
