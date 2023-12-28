FROM grafana/k6:0.48.0
MAINTAINER jorgehasd@gmail.com

COPY script.js .
COPY data.csv .

ENTRYPOINT ["k6", "run", "script.js"]