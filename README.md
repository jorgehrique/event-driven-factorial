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

### Docker compose

Assuming that are you running this project with docker compose, you can run the tests with the following command:

```
cat k6/script.js | docker run --rm -i --network=event-driven-factorial_fnet grafana/k6 run -
```

That will up a k6 container and do mass calls to the factorial request endpoint