docker run -d --name redis -p 6379:6379 redis:latest
http://localhost:5050/browser/
Edureka
: http://localhost:8080/
pgAdmin : http://localhost:5050/browser/

rabit mq ui : http://localhost:15672

docker command to run the redis
 docker run -d --name redis -p 6379:6379 redis:latest

------------------------
docker run -d \
  --name zookeeper \
  -p 2181:2181 \
  -e ZOOKEEPER_CLIENT_PORT=2181 \
  -e ZOOKEEPER_TICK_TIME=2000 \
  confluentinc/cp-zookeeper:7.5.0

docker run -d \
  --name kafka \
  -p 9092:9092 \
  -e KAFKA_BROKER_ID=1 \
  -e KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181 \
  -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://localhost:9092 \
  -e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092 \
  -e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 \
  --link zookeeper \
  confluentinc/cp-kafka:7.5.0

  winpty docker exec -it kafka bash

  kafka-topics --create \
  >   --topic my-topic \
  >   --bootstrap-server localhost:9092 \
  >   --partitions 3 \
  >   --replication-factor 1

 kafka-topics --list --bootstrap-server localhost:9092

winpty exec -it kafka bash

 kafka-console-consumer \
>   --topic my-topic \
>   --bootstrap-server localhost:9092 \
>   --from-beginning


kafka-topic --describe --topic my-topic --bootstrap-server localhost:9092

------------------------