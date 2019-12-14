export default class ShorteningEventHandler {

    constructor(parent) {
        this.parent = parent;
        this.$generate = this.parent.$container.find('#generate');
        this.$redirect = this.parent.$container.find('#redirect');
        this.$clear = this.parent.$container.find('#clear');

        this.bindGenerate();
        this.bindRedirect();
        this.bindClear();
    }

    bindGenerate() {
        this.$generate.on('click', () => {
            this.parent.requestShortening();
        });
    }

    bindRedirect() {
        this.$redirect.on('click', () => {
            this.parent.requestRedirect();
        });
    }

    bindClear() {
        this.$clear.on('click', () => {
            this.parent.clear();
        });
    }
}