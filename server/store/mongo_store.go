package store

import (
	"context"
	"server/model"

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
	err = c.Connect(context.Background())
	if err != nil {
		return err
	}
	return c.Ping(context.Background(), nil)
}

func (s *MongoStore) CreateOrder(order *model.Order) error {
	collection := s.client.Database(database).Collection(orderCollection)
	_, err := collection.InsertOne(context.Background(), order)
	return err
}
