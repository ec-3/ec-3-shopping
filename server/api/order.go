package api

import (
	"encoding/json"
	"fmt"
	"io"
	"net/http"
	"server/model"
)

func CreateOrder(w http.ResponseWriter, r *http.Request) {
	body, err := io.ReadAll(r.Body)
	if err != nil {
		http.Error(w, "Error reading request body", http.StatusInternalServerError)
		return
	}

	var order model.Order
	err = json.Unmarshal(body, &order)
	if err != nil {
		http.Error(w, "Error unmarshalling JSON", http.StatusBadRequest)
		return
	}
	if err := validateOrder(order); err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	payUrl, err := createOrder(order)
	if err != nil {
		http.Error(w, "Error reading request body", http.StatusInternalServerError)
		return
	}
	w.WriteHeader(http.StatusOK)
	fmt.Fprint(w, payUrl)
}

func createOrder(order model.Order) (string, error) {
	return "", nil
}

func validateOrder(order model.Order) error {
	return nil
}
