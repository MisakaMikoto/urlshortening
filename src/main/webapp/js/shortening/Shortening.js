import RequestPromise from "../promise/RequestPromise.js";
import ShorteningEventHandler from "./ShorteningEventHandler.js";

export class Shortening {

    constructor() {
        this.shorteningKeyUrl = '/api/shortening-key';
        this.redirectingKeyUrl = '/api/redirecting-key';

        this.$container = $('#container');
        this.commonRequestPromise = new RequestPromise();
        new ShorteningEventHandler(this);
    }

    requestShortening() {
        this.commonRequestPromise.post(this.shorteningKeyUrl, this.createRequestParam()).then((response) => {
            const result = JSON.parse(response);
            this.$container.find('#response').val(result.shorteningKey);
            this.$container.find('#redirect').show();

        }, (error) => {
            this.$container.find('#response').val('');
            alert(error);
            console.error('Failed!', error);
        });
    }

    createRequestParam() {
        return {
            originUrl: this.$container.find('#request').val()
        };
    }

    requestRedirect() {
        this.commonRequestPromise.get(this.createRedirectUrl()).then((response) => {
            const result = JSON.parse(response);
            alert(result.originUrl + ' 로 이동합니다.');
            window.location.href = result.originUrl;

        }, (error) => {
            alert(error);
            console.error('Failed!', error);
        });
    }

    createRedirectUrl() {
        return this.redirectingKeyUrl + '?shorteningKey=' + this.$container.find('#response').val();
    }

    clear() {
        this.$container.find('#request').val('');
        this.$container.find('#response').val('');
        this.$container.find('#redirect').hide();
    }
}

new Shortening();