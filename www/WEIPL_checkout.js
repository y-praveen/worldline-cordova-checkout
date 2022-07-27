var WLCheckout = module.exports = {
    // Plugin method to get list of UPI intalled applications
    upiIntentAppsList: function (options, successCallback, errorCallback) {
        cordova.exec(
            successCallback,
            errorCallback,
            'WEIPL_checkout',
            'upiIntentAppsList',
            []
        );
    },

    // Plugin method to preLoad checkout components
    preloadComponent: function (options, successCallback, errorCallback) {
        cordova.exec(
            successCallback,
            errorCallback,
            'WEIPL_checkout',
            'preloadComponent',
            []
        );
    },

    // Plugin method to invoke SDK from front-end
    init: function (options, successCallback, errorCallback) {
        this.callbacks.success = successCallback;
        this.callbacks.error = errorCallback;

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

    // Plugin callback object for caller callbacks
    callbacks: {},

    // Plugin succes callback function
    successCallback: function (response) {
        WLCheckout.callbacks.success(response);
    },

    // Plugin error callback function
    errorCallback: function (response) {
        WLCheckout.callbacks.error(response);
    }
};

// WLCheckout.preloadComponent();