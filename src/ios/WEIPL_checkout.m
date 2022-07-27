// TODO: Need to remove this file before go-live because this file is not in use right now. This is just for reference to invoke native functionality using Objective-C

#import <Cordova/CDV.h>

@interface WEIPL_checkout : CDVPlugin {
}
- (void)init:(CDVInvokedUrlCommand*)command;
@end
@implementation WEIPL_checkout
- (void)init:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;
    NSString* options = [command.arguments objectAtIndex:0];
    if (options == nil || [options length] == 0) {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
    } else {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:"Native SDK will be initialised"];
    }
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}
@end
