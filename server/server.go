package main

import (
	"fmt"
	"log"
	"net"
	"net/http"
	"os"
	"server/api"
	"server/background"
	"server/payment"
	"server/store"
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
	apikey := os.Getenv("NOWPAYMENT_API_KEY")
	if apikey == "" {
		log.Fatalln("empty nowpayment api key")
	}
	cstr := os.Getenv("ConnectStr")
	if cstr == "" {
		log.Fatalln("empty cosmos connect str")
	}
	payClient := payment.NewClient("https://api.nowpayments.io/v1/invoice", apikey)
	mstore := store.NewMongoStore(cstr)
	if err := mstore.Connect(); err != nil {
		log.Fatal(err)
	}
	apiServer, err := api.NewAPI(payClient, mstore)
	if err != nil {
		log.Fatalf("Failed to create api %s", err)
	}
	role := os.Getenv("Role")

	if role == "management" {
		log.Default().Println("start as management")
		go background.NewBackGround(mstore, payClient).Run()
		r := mux.NewRouter()
		r.Methods(http.MethodGet).Path("/api/order").HandlerFunc(apiServer.ListOrder)
		r.Methods(http.MethodPut).Path("/api/order").HandlerFunc(apiServer.UpdateOrder)
		r.Methods(http.MethodGet).Path("/hello").HandlerFunc(func(w http.ResponseWriter, r *http.Request) { w.Write([]byte("hello")) })
		srv := &http.Server{
			Handler:      r,
			Addr:         "0.0.0.0:8080",
			WriteTimeout: 15 * time.Second,
			ReadTimeout:  15 * time.Second,
		}
		log.Fatal(srv.ListenAndServe())
	} else {
		log.Default().Println("start as shopping")
		r := mux.NewRouter()
		r.Methods(http.MethodPost).Path("/api/order").HandlerFunc(apiServer.CreateOrder)
		r.Methods(http.MethodGet).Path("/hello").HandlerFunc(func(w http.ResponseWriter, r *http.Request) { w.Write([]byte("hello")) })
		srv := &http.Server{
			Handler:      IPBlockMiddleware(r),
			Addr:         "0.0.0.0:8080",
			WriteTimeout: 15 * time.Second,
			ReadTimeout:  15 * time.Second,
		}
		log.Fatal(srv.ListenAndServe())
	}

}

// This is not used now since CORS can be configured in Azure Web Service
var AllowedMethods = []string{"GET", "POST", "PUT", "DELETE", "OPTIONS"}

func CORSMiddleware(next http.Handler) http.Handler {
	return http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		// read this from env so that easy to update for debuging purpose
		allowOrigin := os.Getenv("ALLOW_ORIGIN")
		if allowOrigin == "" {
			allowOrigin = "https://www.ec-cube.io"
		}
		w.Header().Set("Access-Control-Allow-Origin", "https://www.ec-cube.io")
		w.Header().Set("Access-Control-Allow-Methods", strings.Join(AllowedMethods, ","))
		w.Header().Set("Access-Control-Allow-Headers", "*")
		if r.Method == http.MethodOptions {
			w.WriteHeader(http.StatusOK)
			return
		}
		next.ServeHTTP(w, r)
	})
}

func IPBlockMiddleware(next http.Handler) http.Handler {
	return http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		tcpAddr, ok := r.Context().Value(http.LocalAddrContextKey).(net.Addr)
		if !ok {
			fmt.Println("Failed to retrieve TCP address")
			return
		}
		// Extract IP address from the TCP address
		ipAddress, _, err := net.SplitHostPort(tcpAddr.String())
		if err != nil {
			fmt.Println("Error extracting IP address:", err)
			return
		}
		// Print the IP address
		fmt.Println("Incoming request from IP:", ipAddress)
		next.ServeHTTP(w, r)
	})
}
