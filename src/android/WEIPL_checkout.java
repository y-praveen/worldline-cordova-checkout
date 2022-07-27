package com.weipl.cordova_checkout;

import com.weipl.checkout.CheckoutActivity;
import com.weipl.checkout.upi_intent.UPIIntentResponse;

import org.apache.cordova.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class WEIPL_checkout extends CordovaPlugin implements CheckoutActivity.PaymentResponseListener {

    public CallbackContext callback;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        // Preload checkout dependencies on app launch
        CheckoutActivity.preloadData(cordova.getActivity().getApplicationContext());
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        this.callback = callbackContext;

        if (action.equals("upiIntentAppsList")) {

            this.upiIntentAppsList();
            return true;

        } /*
           * else if (action.equals("preloadComponent")) {
           * 
           * cordova.getActivity().runOnUiThread(new Runnable() {
           * public void run() {
           * preloadComponent();
           * callbackContext.success();
           * }
           * });
           * return true;
           * 
           * }
           */ else if (action.equals("init")) {

            this.init(args);
            return true;

        }
        return false;
    }

    // TODO:
    // Get UPI intent apps method handling
    // Request & response part for Android & iOS should be in JSON object

    private void upiIntentAppsList() {
        /*
         * UPIIntentResponse intentResponse =
         * CheckoutActivity.getUPIResponse(this.cordova.getActivity());
         * if(intentResponse == null) {
         * this.callback.error(jsonObject);
         * return;
         * }
         * 
         * this.callback.success(intentResponse);
         */
    }

    /*
     * private void preloadComponent() {
     * CheckoutActivity.preloadData(cordova.getActivity().getApplicationContext());
     * }
     */

    private void init(JSONArray args) {
        if (args == null) {
            this.callback.error("Expected checkout initialisation options");
            return;
        }

        try {
            CheckoutActivity.setPaymentResponseListener(this);

            JSONObject convertedObject = new JSONObject(args.getString(0));
            CheckoutActivity.init(this.cordova.getActivity(), convertedObject);
        } catch (Exception e) {
            this.callback.error("Something went wrong " + e);
        }
    }

    @Override
    public void onPaymentError(JSONObject jsonObject) {
        this.callback.error(jsonObject);
    }

    @Override
    public void onPaymentResponse(JSONObject jsonObject) {
        this.callback.success(jsonObject);
    }
}
