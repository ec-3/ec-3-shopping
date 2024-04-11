package mail

import (
	"testing"
)

func TestSendEmail(t *testing.T) {
	err := SendEmail("zyang210504@gmail.com", "test mail", "hello world")
	if err != nil {
		t.Error(err.Error())
	}
}
