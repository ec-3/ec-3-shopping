package payment

import (
	"fmt"
	"testing"
)

func TestOrderInfoUnmarshal(t *testing.T) {
	c := NewClient("https://api.nowpayments.io/v1/invoice", "T2YQ5ZE-5R1M9RD-MF53DEJ-7HAB3V0")
	id, url, err := c.Pay(100, "xx", "baidu.com", "google.com")
	if err != nil {
		t.Error(err.Error())
	}
	fmt.Println(id, url)
}
