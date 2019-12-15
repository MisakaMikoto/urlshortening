export default class RequestPromise {

    constructor(){};

    get(url) {
        return new Promise((resolve, reject) => {
            let req = new XMLHttpRequest();
            req.open('GET', url);

            req.onload = () => {
                if (req.status === 200) {
                    resolve(req.response);
                }
                else {
                    reject(Error(req.responseText));
                }
            };

            req.onerror = () => {
                reject(Error('Network Error'));
            };

            // Make the request
            req.send();
        });
    }

    post(url, params) {
        const data = JSON.stringify(params);
        return new Promise((resolve, reject) => {
            let req = new XMLHttpRequest();
            req.open('POST', url, true);
            req.setRequestHeader('Content-Type', 'application/json;charset=UTF-8');

            req.onload = () => {
                if (req.status === 200) {
                    resolve(req.response);
                }
                else {
                    reject(Error(req.responseText));
                }
            };

            req.onerror = () => {
                reject(Error('Network Error'));
            };

            // Make the request
            req.send(data);
        });
    }
}