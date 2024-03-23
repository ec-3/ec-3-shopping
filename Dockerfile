# Start from the latest Golang base image
FROM golang:latest

# Set the Current Working Directory inside the container
WORKDIR /app

# Copy the server directory contents to the Working Directory inside the container
COPY ./server /app

# Build the Go app
RUN go build -o server .

# Expose port 8080 to the outside world
EXPOSE 8080

# Command to run the executable
CMD ["./server"]