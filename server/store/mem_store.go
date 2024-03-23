package store

import (
	"server/model"
	"sync"
)

func NewMemStore() *MemStore {
	return &MemStore{
		orders: make(map[string]*model.Order),
	}
}

// For test purpose
type MemStore struct {
	orders map[string]*model.Order
	lock   sync.Mutex
}

func (s *MemStore) CreateOrder(o *model.Order) error {
	s.lock.Lock()
	defer s.lock.Unlock()
	s.orders[o.PaymentID] = o
	return nil
}
