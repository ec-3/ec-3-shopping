package store

import (
	"context"
	"server/model"
	"time"

	"go.mongodb.org/mongo-driver/bson"
	"go.mongodb.org/mongo-driver/mongo"
	"go.mongodb.org/mongo-driver/mongo/options"
)

type MongoStore struct {
	cstr   string
	client *mongo.Client
}

const database = "cube"
const orderCollection = "order"

func NewMongoStore(cstr string) *MongoStore {
	return &MongoStore{cstr: cstr}
}

func (s *MongoStore) Connect() error {
	clientOptions := options.Client().ApplyURI(s.cstr).SetDirect(true)
	c, err := mongo.NewClient(clientOptions)
	if err != nil {
		return err
	}
	s.client = c
	ctx, cancel := context.WithTimeout(context.Background(), 60*time.Second)
	defer cancel()
	err = c.Connect(ctx)
	if err != nil {
		return err
	}
	// reuse the ctx. The total latency should be less than 60s.
	return c.Ping(ctx, nil)
}

func (s *MongoStore) CreateOrder(order *model.Order) error {
	ctx, cancel := context.WithTimeout(context.Background(), 10*time.Second)
	defer cancel()
	collection := s.client.Database(database).Collection(orderCollection)
	_, err := collection.InsertOne(ctx, order)
	return err
}

func (s *MongoStore) ListOrders(status int) ([]*model.Order, error) {
	ctx, cancel := context.WithTimeout(context.Background(), 20*time.Second)
	defer cancel()
	collection := s.client.Database(database).Collection(orderCollection)
	filter := bson.D{{"status", status}}
	rs, err := collection.Find(ctx, filter)
	if err != nil {
		return nil, err
	}
	var ret []*model.Order
	err = rs.All(ctx, &ret)
	if err != nil {
		return nil, err
	}
	return ret, nil
}

func (s *MongoStore) ListOrdersByFilter(filter bson.D) ([]*model.Order, error) {
	ctx, cancel := context.WithTimeout(context.Background(), 20*time.Second)
	defer cancel()
	collection := s.client.Database(database).Collection(orderCollection)
	rs, err := collection.Find(ctx, filter)
	if err != nil {
		return nil, err
	}
	var ret []*model.Order
	err = rs.All(ctx, &ret)
	if err != nil {
		return nil, err
	}
	return ret, nil
}

func (s *MongoStore) UpdateOrder(order *model.Order) error {
	ctx, cancel := context.WithTimeout(context.Background(), 10*time.Second)
	defer cancel()
	filter := bson.D{{"paymentid", order.PaymentID}}
	update := bson.D{{"$set", order}}
	collection := s.client.Database(database).Collection(orderCollection)
	_, err := collection.UpdateOne(ctx, filter, update)
	return err
}

func (s *MongoStore) UpdateOrderWithFilter(order *model.Order, filter bson.D) error {
	ctx, cancel := context.WithTimeout(context.Background(), 10*time.Second)
	defer cancel()
	update := bson.D{{"$set", order}}
	collection := s.client.Database(database).Collection(orderCollection)
	_, err := collection.UpdateOne(ctx, filter, update)
	return err
}
