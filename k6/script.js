import http from "k6/http";
import { SharedArray } from 'k6/data';
import { check } from 'k6';

export const options = {
  vus: 10,
  duration: '20s'
};

//const factorials = new SharedArray('factorials', function () {
//  return JSON.parse(open('./data.json')).factorials;
//});

export default function () {
  const url = 'http://factorial-service:8085/factorial';

  const payload = JSON.stringify({
    number: 30,
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
