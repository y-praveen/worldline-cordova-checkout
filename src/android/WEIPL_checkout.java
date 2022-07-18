package com.weipl.cordova_checkout;

import com.weipl.checkout.CheckoutActivity;
import com.weipl.checkout.utils.CheckoutConstants;

import org.apache.cordova.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class WEIPL_checkout extends CordovaPlugin implements CheckoutActivity.PaymentResponseListener {

    public CallbackContext callback;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.callback = callbackContext;
        if (action.equals("init")) {
            this.init(args);
            return true;
        }
        return false;
    }

    // TODO:
    // Response handling not working in android
    // Get UPI intent apps method handling
    // Response handling with callback methods

    private void init(JSONArray args) {
        if (args == null) {
            this.callback.error("Expected checkout initialisation options");
        } else {
            try {
                CheckoutActivity.Test act = CheckoutActivity.Test;
                act.setPaymentResponseListener(this);

                JSONObject convertedObject = new JSONObject(args.getString(0));
                act.init(this.cordova.getActivity(), convertedObject);

                this.callback.success("Native SDK will be initialised");
            } catch (Exception e) {
                this.callback.error("Something went wrong " + e);
            }
        }
    }

    @Override
    public void onPaymentError(JSONObject jsonObject) {
        this.callback.error(jsonObject);
    }

    @Override
    public void onPaymentSuccess(JSONObject jsonObject) {
        this.callback.success(jsonObject);
    }
}
