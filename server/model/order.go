package model

type Order struct {
	Name        string `validate:"required"`
	CompanyName string
	Country     string         `validate:"required"`
	Province    string         `validate:"required"`
	City        string         `validate:"required"`
	Street      string         `validate:"required"`
	Address     string         `validate:"required"`
	ZipCode     string         `validate:"required"`
	Phone       string         `validate:"required,e164"`
	Email       string         `validate:"required,email"`
	Details     map[string]int `validate:"required"`
	PaymentID   string
	PaymentURL  string
	Amount      int `validate:"required"`
	Status      int
	StatusStr   string
	SuccURL     string `validate:"required"`
	CancelURL   string `validate:"required"`

	CreateTime    int
	PaidTime      int
	DepartureTime int
	FinishTime    int
}

const (
	StatusFailed   = 9
	StatusCreated  = 0
	StatusPaid     = 1
	StatusNotified = 2
	StatusSent     = 3
	StatusDeliverd = 4
)
