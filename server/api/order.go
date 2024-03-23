package api

import (
	"crypto/md5"
	"encoding/json"
	"fmt"
	"io"
	"net/http"
	"server/model"
	"server/payment"
	"server/store"
	"strconv"
	"time"

	"github.com/go-playground/validator/v10"
	"go.mongodb.org/mongo-driver/bson"
)

type API struct {
	payClient *payment.Client
	store     *store.MongoStore
	validate  *validator.Validate
}

func NewAPI(payClient *payment.Client, mstore *store.MongoStore) (*API, error) {
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
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	if err := api.validateOrder(order); err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	order.Status = 0
	order.CreateTime = int(time.Now().Unix())
	payUrl, err := api.createOrder(&order)
	if err != nil {
		http.Error(w, "Error reading request body", http.StatusInternalServerError)
		return
	}
	w.WriteHeader(http.StatusOK)
	fmt.Fprint(w, payUrl)
}

func (api *API) ListOrder(w http.ResponseWriter, r *http.Request) {
	starttime := r.URL.Query().Get("starttime")
	endtime := r.URL.Query().Get("endtime")
	id := r.URL.Query().Get("id")
	name := r.URL.Query().Get("name")
	phone := r.URL.Query().Get("phone")
	status := r.URL.Query().Get("status")

	filter := bson.D{{}}
	if starttime != "" {
		s, err := strconv.Atoi(starttime)
		if err != nil {
			http.Error(w, err.Error(), http.StatusBadRequest)
			return
		}
		filter = append(filter, bson.E{"createtime", bson.D{{"$gt", s}}})
	}
	if endtime != "" {
		s, err := strconv.Atoi(endtime)
		if err != nil {
			http.Error(w, err.Error(), http.StatusBadRequest)
			return
		}
		filter = append(filter, bson.E{"createtime", bson.D{{"$lt", s}}})
	}
	if id != "" {
		filter = append(filter, bson.E{"paymentid", id})
	}
	if phone != "" {
		filter = append(filter, bson.E{"phone", phone})
	}
	if name != "" {
		filter = append(filter, bson.E{"name", name})
	}
	if status != "" {
		s, err := strconv.Atoi(status)
		if err != nil {
			http.Error(w, err.Error(), http.StatusBadRequest)
			return
		}
		filter = append(filter, bson.E{"status", s})
	}
	orders, err := api.store.ListOrdersByFilter(filter)
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}
	if len(orders) == 0 {
		http.Error(w, "Not Found", http.StatusNotFound)
		return
	}
	bs, _ := json.Marshal(orders)
	w.WriteHeader(http.StatusOK)
	w.Write(bs)
}

type UpdateReq struct {
	ID     string
	Status int
}

func (api *API) UpdateOrder(w http.ResponseWriter, r *http.Request) {
	body, err := io.ReadAll(r.Body)
	if err != nil {
		http.Error(w, "Error reading request body", http.StatusInternalServerError)
		return
	}
	var req UpdateReq
	err = json.Unmarshal(body, &req)
	if err != nil {
		http.Error(w, "Error unmarshalling JSON", http.StatusBadRequest)
		return
	}
	if req.ID == "" {
		http.Error(w, "empty id", http.StatusBadRequest)
		return
	}
	filter := bson.D{{"paymentid", req.ID}}
	orders, err := api.store.ListOrdersByFilter(filter)
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}
	if len(orders) == 0 {
		http.Error(w, "Not Found", http.StatusNotFound)
		return
	}
	if len(orders) > 1 {
		http.Error(w, fmt.Sprintf("More than 1 order found for %s", req.ID), http.StatusInternalServerError)
		return
	}
	order := orders[0]
	order.Status = req.Status
	if req.Status == model.StatusSent {
		order.DepartureTime = int(time.Now().Unix())
	}
	if req.Status == model.StatusDeliverd {
		order.FinishTime = int(time.Now().Unix())
	}
	api.store.UpdateOrder(order)
	bs, _ := json.Marshal(order)
	w.WriteHeader(200)
	w.Write(bs)
}

func (api *API) createOrder(order *model.Order) (string, error) {
	id, url, err := api.payClient.Pay(order.Amount, "xxxx", order.SuccURL, order.CancelURL)
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
