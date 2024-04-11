package mail

import (
	"fmt"
	"net/smtp"
	"os"
)

type Config struct {
	SMTPHost string
	SMTPPort string
	Sender   string
}

var config = Config{
	SMTPHost: "smtpout.secureserver.net",
	SMTPPort: "587",
	Sender:   "support@ec-cube.io",
}

func SendEmail(receiver string, subject, body string) error {
	passwd := os.Getenv("MAIL_PASSWORD")
	if passwd == "" {
		return fmt.Errorf("Empty password for email")
	}
	auth := smtp.PlainAuth("", config.Sender, passwd, config.SMTPHost)
	message := []byte("To: " + receiver + "\r\n" +
		"Subject: " + subject + "\r\n" +
		"\r\n" +
		body + "\r\n")
	return smtp.SendMail(config.SMTPHost+":"+config.SMTPPort, auth, config.Sender, []string{receiver}, message)
}
