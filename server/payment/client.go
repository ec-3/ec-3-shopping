package payment

import (
	"bytes"
	"encoding/json"
	"fmt"
	"io"
	"net"
	"net/http"
	"time"
)

func NewClient(url, key string) *Client {
	t := &http.Transport{
		Dial: (&net.Dialer{
			Timeout:   60 * time.Second,
			KeepAlive: 30 * time.Second,
		}).Dial,
		TLSHandshakeTimeout: 60 * time.Second,
	}
	httpClient := &http.Client{
		Transport: t,
		Timeout:   60 * time.Second,
	}
	return &Client{
		apikey:     key,
		httpClient: httpClient,
		url:        url,
	}
}

type Client struct {
	url        string
	apikey     string
	httpClient *http.Client
}

func (c *Client) Pay(price int, desc string, succURL string, cancelURL string) (string, string, error) {
	req := PayReq{
		PriceAmount:      price,
		PriceCurrency:    "usd",
		OrderDescription: desc,
		SuccessURL:       succURL,
		CancelURL:        cancelURL,
	}
	resp, err := c.pay(&req)
	if err != nil {
		return "", "", err
	}
	return resp.ID, resp.InvoiceURL, nil
}

func (c *Client) GetStatus(url string) (string, error) {
	httpReq, err := http.NewRequest(http.MethodGet, url, nil)
	if err != nil {
		return "", err
	}
	httpReq.Header.Set("x-api-key", c.apikey)
	res, err := c.httpClient.Do(httpReq)
	if err != nil {
		return "", err
	}
	defer res.Body.Close()
	if res.StatusCode != 200 {
		msg, err := io.ReadAll(res.Body)
		if err != nil {
			return "", err
		}
		return "", fmt.Errorf("Retcode %d, msg %s", res.StatusCode, msg)
	}
	resp := &StatusResp{}
	err = json.NewDecoder(res.Body).Decode(resp)
	if err != nil {
		return "", err
	}
	return resp.PaymentStatus, nil
}

func (c *Client) pay(req *PayReq) (*PayResp, error) {
	bs, _ := json.Marshal(req)
	httpReq, err := http.NewRequest(http.MethodPost, c.url, bytes.NewBuffer(bs))
	if err != nil {
		return nil, err
	}
	httpReq.Header.Set("x-api-key", c.apikey)
	httpReq.Header.Set("Content-Type", "application/json")
	res, err := c.httpClient.Do(httpReq)
	if err != nil {
		return nil, err
	}
	defer res.Body.Close()
	if res.StatusCode != 200 {
		msg, err := io.ReadAll(res.Body)
		if err != nil {
			return nil, err
		}
		return nil, fmt.Errorf("Retcode %d, msg %s", res.StatusCode, msg)
	}
	resp := &PayResp{}
	err = json.NewDecoder(res.Body).Decode(resp)
	return resp, err
}

type PayReq struct {
	PriceAmount      int    `json:"price_amount"`
	PriceCurrency    string `json:"price_currency"`
	OrderDescription string `json:"order_description"`
	SuccessURL       string `json:"success_url"`
	CancelURL        string `json:"cancel_url"`
	OrderID          string `json:"order_id,omitempty"`
	IpnCallbackURL   string `json:"ipn_callback_url,omitempty"`
}
type PayResp struct {
	ID               string    `json:"id"`
	InvoiceURL       string    `json:"invoice_url"`
	OrderID          string    `json:"order_id"`
	OrderDescription string    `json:"order_description"`
	PriceAmount      string    `json:"price_amount"`
	PriceCurrency    string    `json:"price_currency"`
	PayCurrency      string    `json:"pay_currency"`
	IpnCallbackURL   string    `json:"ipn_callback_url"`
	SuccessURL       string    `json:"success_url"`
	CancelURL        string    `json:"cancel_url"`
	CreatedAt        time.Time `json:"created_at"`
	UpdatedAt        time.Time `json:"updated_at"`
}

type StatusResp struct {
	PaymentStatus string `json:"payment_status"`
}
