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

type API struct {
	payClient *payment.Client
	store     *store.MongoStore
	validate  *validator.Validate
}

func NewAPI() (*API, error) {
	apikey := os.Getenv("NOWPAYMENT_API_KEY")
	if apikey == "" {
		return nil, fmt.Errorf("empty nowpayment api key")
	}
	cstr := os.Getenv("ConnectStr")
	if cstr == "" {
		return nil, fmt.Errorf("empty cosmos connect str")
	}
	payClient := payment.NewClient("https://api.nowpayments.io/v1/invoice", apikey)
	mstore := store.NewMongoStore(cstr)
	if err := mstore.Connect(); err != nil {
		return nil, err
	}
	return &API{
		payClient: payClient,
		store:     mstore,
		validate:  validator.New(validator.WithRequiredStructEnabled()),
	}, nil
}

func (api *API) CreateOrder(w http.ResponseWriter, r *http.Request) {
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
	if err := api.validateOrder(order); err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	order.Status = 0
	payUrl, err := api.createOrder(&order)
	if err != nil {
		http.Error(w, "Error reading request body", http.StatusInternalServerError)
		return
	}
	w.WriteHeader(http.StatusOK)
	fmt.Fprint(w, payUrl)
}

func (api *API) createOrder(order *model.Order) (string, error) {
	id, url, err := api.payClient.Pay(order.Amount, "xxxx", "https://www.ec-cube.io/mall", "https://www.ec-cube.io/mall")
	if err != nil {
		return "", err
	}
	order.PaymentID = id
	order.PaymentURL = url
	if err := api.store.CreateOrder(order); err != nil {
		return "", err
	}
	return order.PaymentURL, nil
}

func generateID(order model.Order) string {
	jsonData, _ := json.Marshal(order)
	checksum := md5.Sum(jsonData)
	return fmt.Sprintf("%x", checksum)
}

func (api *API) validateOrder(order model.Order) error {
	err := api.validate.Struct(order)
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
