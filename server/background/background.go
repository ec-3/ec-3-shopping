package background

import (
	"fmt"
	"log"
	"server/model"
	"server/payment"
	"server/store"
	"time"
)

func NewBackGround(store *store.MongoStore, payClient *payment.Client) *BackGround {
	return &BackGround{store: store, payClient: payClient}
}

type BackGround struct {
	store     *store.MongoStore
	payClient *payment.Client
}

func (bg *BackGround) Run() {
	// Update order status.
	// Check if order is paid and notify user by mail.
	time.Sleep(time.Second * 1)
	bg.processOrders()
}

func (bg *BackGround) processOrders() {
	unpaidOrders, err := bg.store.ListOrders(0)
	if err != nil {
		log.Default().Printf("Failed load orders, err %s", err)
		return
	}
	for _, order := range unpaidOrders {
		status, err := bg.payClient.GetStatus(fmt.Sprintf("https://api.nowpayments.io/v1/payment/%s", order.PaymentID))
		if err != nil {
			log.Default().Printf("Failed to get status for pay %s, err %s", order.PaymentURL, err)
			continue
		}
		if status == "" {
			log.Default().Printf("Failed to get status for pay %s, empty status", order.PaymentURL)
			continue
		}
		if status == "finished" {
			log.Default().Printf("Found 1 paid order. About to send email.")
			// send email and update cosmos as notified.
			//TODO: Send email
			order.Status = model.StatusNotified
			order.PaidTime = int(time.Now().Unix())
			bg.store.UpdateOrder(order)
			continue
		}
		if status == "failed" || status == "expired" {
			// update cosmos as failed
			order.Status = model.StatusFailed
			if err := bg.store.UpdateOrder(order); err != nil {
				log.Default().Printf("Failed to update order %s", err)
			}
		}
	}
}
