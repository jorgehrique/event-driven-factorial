import http from "k6/http";
import { check, sleep } from 'k6';
import { SharedArray } from 'k6/data';
import papaparse from 'https://jslib.k6.io/papaparse/5.1.1/index.js';

export const options = {
    scenarios: {
        request_factorial_scenario : {
            exec: 'request_factorial_scenario',
            executor: 'shared-iterations',
            vus: 50,
            iterations: 50,
            maxDuration: '90s'
        }
    }
};

const csvData = new SharedArray('factorials', function () {
    return papaparse.parse(open('./data.csv'), { header: true }).data;
});

export function request_factorial_scenario () {
    const url = 'http://factorial-service:8085/factorial';

    const random = csvData[Math.floor(Math.random() * csvData.length)];
    const payload = JSON.stringify({ number: random.number });

    const params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    // Create new factorial calc
    const factorialCreationResponse = http.post(url, payload, params);
    check(factorialCreationResponse, {
        'is status 200': (r) => r.status === 200,
        'is factorial status in [PROCESSING, DONE]': (r) =>
            (r.json('status') === 'PROCESSING' || r.json('status') === 'DONE')
    });

    sleep(1);

    const id = factorialCreationResponse.json('id');

    // Check if the new factorial was persisted consistently
    const factorialConfirmResponse = http.get(`${url}/${id}`, params);
    check(factorialConfirmResponse, {
        'is status 200': (r) => r.status === 200,
        'is factorial status in [PROCESSING, DONE]': (r) =>
            (r.json('status') === 'PROCESSING' || r.json('status') === 'DONE')
    });

    sleep(60); // 1 minute

    // Check if the load was processed in 1 minute
    const factorialDoneResponse = http.get(`${url}/${id}`, params);
    check(factorialDoneResponse, {
        'is status 200': (r) => r.status === 200,
        'is factorial status is DONE': (r) => r.json('status') === 'DONE',
        'is factorial result right': (r) => r.json('result') === random.result
    });
}
