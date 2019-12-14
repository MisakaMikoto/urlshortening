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
            this.$container.find('#response').val(response.shorteningKey);
            this.$container.find('#redirect').show();

        }, (error) => {
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
            alert(response.originUrl + ' 로 이동합니다.');
            window.location.href = response.originUrl;

        }, (error) => {
            return error;
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