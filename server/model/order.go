package model

type Order struct {
	FirstName   string `validate:"required"`
	LastName    string `validate:"required"`
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
	Status      int // 0 for created. 1 for paid. 2 for notified. 3 for finished.  9 for failed
	StatusStr   string
}
