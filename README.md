# Event-driven Factorial

Educational proprose system to process multiple requests to calculate factorial.<br>
I use this repo to learn and validate a lot of things like load tests, optimizations and autoscaling.

## Getting Started

This instructions will give all you need to run this locally on your machine and do some tests.

### Prerequisites

- Git
- Docker (or any docker-compatible tools)
- Docker-compose and/or Kubernetes cluster

### Installing

First at all clone this repo with:

```
git clone https://github.com/jorgehrique/event-driven-factorial your-folder
```

Inside the cloned folder, build the docker images:

```
docker build -t calcltda/factorial-service:0.0.1 factorial-service
docker build -t calcltda/factorial-worker:0.0.1 factorial-worker
```

And now you can up all services with yours respective dependencies:

```
docker-compose up
```

[//]: # ()
[//]: # (### Kubernetes)

[//]: # ()
[//]: # (If you want to run this on a kubernetes cluster, ignore the docker-compose step and execute:)

[//]: # ()
[//]: # (```)

[//]: # (kubectl apply -f -all k8s/)

[//]: # (```)

## Load tests

The load tests as writeen with Grafana K6 and the content is on the k6 folder. 

The test will send _n_ requests to factorial-service, that will create _n_ messages in the queue. After that, will
do a request to verify if the request was really created. Then will wait for 60 seconds and will do _n_ requests to check
if the processes status was done. Default _n_ is 50.

At this point, each factorial-worker instance can handle 50 factorials per minute. (with 20 ms in calm down config)

### Docker compose

Assuming that are you running this project with docker compose, you can run the tests with the following commands

- Create the load test image:
```
 docker build -t calcltda/load-test:0.0.1 k6
```

- Run the load test:
```
  docker run --network=event-driven-factorial_fnet calcltda/load-test:0.0.1
```