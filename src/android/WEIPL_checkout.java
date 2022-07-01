package com.weipl.cordova_checkout;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class WEIPL_checkout extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            return true;
        }

        if (action.equals("init")) {
            this.init(args, callbackContext);
            return true;
        }
        return false;
    }

    private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    private void init(JSONArray args, CallbackContext callbackContext) {
        if (args == null) {
            callbackContext.error("Expected checkout initialisation options");
        } else {
            try {
                // TODO: invoke native Android SDK checkout
                callbackContext.success("Native SDK will be initialised");                
            } catch (Exception e) {
                callbackContext.error("Spmething went wrong " + e);
            }
        }
    }

}
