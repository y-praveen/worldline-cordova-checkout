/* module.exports.init = function (arg0, success, error) {
    exec(success, error, 'WEIPL_checkout', 'init', [arg0]);
}; */

var WLCheckout = module.exports = {
    init: function (options, successCallback, errorCallback) {
        this.callbacks.success = successCallback;
        this.callbacks.error = errorCallback;

        /* if (successCallback) {
            WLCheckout.callbacks['payment.success'] = function (response) {
                successCallback(response);
            }
        }

        if (errorCallback) {
            WLCheckout.callbacks['payment.cancel'] = errorCallback;
        } */

        cordova.exec(
            WLCheckout.successCallback,
            WLCheckout.errorCallback,
            'WEIPL_checkout',
            'init',
            [
                JSON.stringify(options)
            ]
        );
    },

    callbacks: {},

    successCallback: function (response) {
        this.callbacks.success(response);
    },

    errorCallback: function (response) {
        this.callbacks.error(response);
    },

    /* pluginCallback: function (response) {
        if ('razorpay_payment_id' in response) {
            WLCheckout.callbacks['payment.success'](response);
        }
        else if ('external_wallet_name' in response) {
            WLCheckout.callbacks['payment.external_wallet'](response);
        }
        else if ('code' in response) {
            WLCheckout.callbacks['payment.cancel'](response);
        }
    },

    on: function (event, callback) {
        if (typeof event === 'string' && typeof callback === 'function') {
            WLCheckout.callbacks[event] = callback;
        }
    },

    onResume: function (event) {
        if (event.pendingResult && event.pendingResult.pluginServiceName === 'Checkout') {
            WLCheckout.pluginCallback(event.pendingResult.result);
        }
    } */
}