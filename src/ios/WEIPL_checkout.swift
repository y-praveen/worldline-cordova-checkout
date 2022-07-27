import Paynimo_JS
import UIKit
import Foundation

@objc(WEIPL_checkout) class WEIPL_checkout : CDVPlugin {
    
    var callback : String?
    
    @objc(preloadComponent:)
    func preloadComponent(_ command: CDVInvokedUrlCommand?) {
        let WLCheckout = CheckoutViewController.preloadComponent()
        // WLCheckout.modalPresentationStyle = .fullScreen
        DispatchQueue.main.async { UIApplication.shared.keyWindow?.rootViewController?.present(WLCheckout, animated: true, completion: nil) }
    }

    @objc(init:)    
    func `init`(_ command: CDVInvokedUrlCommand?) {
        
        self.callback = command?.callbackId
        let options = command?.arguments[0] as? String ?? "Error"
        
        if #available(iOS 13.0, *) {
            
            do {
                let data = options.data(using: .utf8)
                let anyResult: Any = try JSONSerialization.jsonObject(with: data!, options: [])
                //print(anyResult)
                
                // ToDo : SDK should accept JSON data instead of SWIFT Dictionary.
                // SDK will convert JSON data into SWIFT Dictionary internally.
                let WLCheckout = CheckoutViewController.init(requestObj:anyResult as AnyObject)
                WLCheckout.modalPresentationStyle = .fullScreen
                DispatchQueue.main.async { UIApplication.shared.keyWindow?.rootViewController?.present(WLCheckout, animated: true, completion: nil) }
                
                NotificationCenter.default.addObserver(self, selector: #selector(self.ReceivedSDKCallBack(result:)), name: Notification.Name("NotificationIdentifier"), object: nil)
                
            } catch {
                
            }
            
        } else {
            // Fallback on earlier versions
            // TODO: handling needed
        }
    }
    
    @objc func ReceivedSDKCallBack(result: Notification) {
        
        print("DATA RECEIVED : \(String(describing: result.object))")
        
        DispatchQueue.main.async{
            
            // // create the alert
            // let alert = UIAlertController(title: "Wordline", message: (result.object) as? String, preferredStyle: UIAlertController.Style.alert)
            // // add an action (button)
            // alert.addAction(UIAlertAction(title: "OK", style: UIAlertAction.Style.default, handler: nil))
            // // show the alert
            // DispatchQueue.main.async { UIApplication.shared.keyWindow?.rootViewController?.present(alert, animated: true, completion: nil) }
            
            let jsonData = try! JSONSerialization.data(withJSONObject: result.object!, options: JSONSerialization.WritingOptions.prettyPrinted)
            let jsonString = NSString(data: jsonData, encoding: String.Encoding.utf8.rawValue)! as String
            print(jsonString)
            
            let pluginResult = CDVPluginResult(
                status: CDVCommandStatus_OK,
                messageAs: jsonString
            )
            
            let pluginResult = CDVPluginResult(
                status: CDVCommandStatus_ERROR,
                messageAs: jsonString
            )
            
            self.commandDelegate!.send(
                pluginResult,
                callbackId: self.callback
            )
        }
    }
}
