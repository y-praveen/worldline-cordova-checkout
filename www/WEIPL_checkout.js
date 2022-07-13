// var exec = require('cordova/exec');

exports.coolMethod = function (arg0, success, error) {
    exec(success, error, 'WEIPL_checkout', 'coolMethod', [arg0]);
};

module.exports.init = function (arg0, success, error) {
    exec(success, error, 'WEIPL_checkout', 'init', [arg0]);
};

var WLCheckout = module.exports = {
    init: function (options, successCallback, errorCallback) {
        if (successCallback) {
            WLCheckout.callbacks['payment.success'] = function (response) {
                successCallback(response);
            }
        }

        if (errorCallback) {
            WLCheckout.callbacks['payment.cancel'] = errorCallback;
        }

        cordova.exec(
            WLCheckout.pluginCallback,
            WLCheckout.pluginCallback,
            'WEIPL_checkout',
            'init',
            [
                JSON.stringify(options)
            ]
        );
    },

    pluginCallback: function (response) {
        /* if ('razorpay_payment_id' in response) {
            WLCheckout.callbacks['payment.success'](response);
        }
        else if ('external_wallet_name' in response) {
            WLCheckout.callbacks['payment.external_wallet'](response);
        }
        else if ('code' in response) {
            WLCheckout.callbacks['payment.cancel'](response);
        } */
    },

    callbacks: {},

    on: function (event, callback) {
        if (typeof event === 'string' && typeof callback === 'function') {
            WLCheckout.callbacks[event] = callback;
        }
    },

    onResume: function (event) {
        if (event.pendingResult && event.pendingResult.pluginServiceName === 'Checkout') {
            WLCheckout.pluginCallback(event.pendingResult.result);
        }
    }
}