package main

import (
	"log"
	"net/http"
	"server/api"
	"time"

	"github.com/gorilla/mux"
)

// TODO:
// 1. Run this as WebService. Https.
// 2. Send email.
// 3. Create COSMOS DB.
// 4. Order management. Anthentication.

func main() {
	r := mux.NewRouter()
	r.Methods(http.MethodPost).Path("/api/order").HandlerFunc(api.CreateOrder)
	srv := &http.Server{
		Handler: r,
		Addr:    "0.0.0.0:8000",
		// Good practice: enforce timeouts for servers you create!
		WriteTimeout: 15 * time.Second,
		ReadTimeout:  15 * time.Second,
	}

	log.Fatal(srv.ListenAndServe())
}
