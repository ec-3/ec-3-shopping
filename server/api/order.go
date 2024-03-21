package api

import (
	"crypto/md5"
	"encoding/json"
	"fmt"
	"io"
	"net/http"
	"os"
	"server/model"
	"server/payment"
	"server/store"

	"github.com/go-playground/validator/v10"
)

var payClient = payment.NewClient("https://api.nowpayments.io/v1/invoice", os.Getenv("NOWPAYMENT_API_KEY"))
var mstore = store.NewMemStore()
var validate = validator.New(validator.WithRequiredStructEnabled())

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
	payUrl, err := createOrder(&order)
	if err != nil {
		http.Error(w, "Error reading request body", http.StatusInternalServerError)
		return
	}
	w.WriteHeader(http.StatusOK)
	fmt.Fprint(w, payUrl)
}

func createOrder(order *model.Order) (string, error) {
	id, url, err := payClient.Pay(order.Amount, "xxxx", "https://www.ec-cube.io/mall", "https://www.ec-cube.io/mall")
	if err != nil {
		return "", err
	}
	order.PaymentID = id
	order.PaymentURL = url
	if err := mstore.CreateOrder(order); err != nil {
		return "", err
	}
	return order.PaymentURL, nil
}

func generateID(order model.Order) string {
	jsonData, _ := json.Marshal(order)
	checksum := md5.Sum(jsonData)
	return fmt.Sprintf("%x", checksum)
}

func validateOrder(order model.Order) error {
	err := validate.Struct(order)
	if err != nil {
		return err
	}
	amount := 0
	// calculate price
	for product, count := range order.Details {
		price, ok := products[product]
		if !ok {
			return fmt.Errorf("%s not exists", product)
		}
		amount += price * count
	}
	if amount != order.Amount {
		return fmt.Errorf("Amount not matched, calculated %d, passed %d", amount, order.Amount)
	}
	return nil
}
