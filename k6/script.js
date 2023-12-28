import http from "k6/http";
import { check } from 'k6';
import { SharedArray } from 'k6/data';
import papaparse from 'https://jslib.k6.io/papaparse/5.1.1/index.js';

export const options = {
  vus: 1,
  duration: '1s'
};

const csvData = new SharedArray('factorials', function () {
  return papaparse.parse(open('./data.csv'), { header: true }).data;
});

export default function () {
  const url = 'http://factorial-service:8085/factorial';

  const random = csvData[Math.floor(Math.random() * csvData.length)];
  const payload = JSON.stringify({
    number: random.number,
  });

  const params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };

  const res = http.post(url, payload, params);

  check(res, {
      'is status 200': (r) => r.status === 200
  });
}
