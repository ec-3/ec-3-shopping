package model

type Order struct {
	FirstName   string
	LastName    string
	CompanyName string
	Country     string
	Address     string
	City        string
	State       string
	ZipCode     string
	Phone       string
	Email       string
	Note        string
	Details     map[string]int
	Status      int
	PaymentID   string
}
