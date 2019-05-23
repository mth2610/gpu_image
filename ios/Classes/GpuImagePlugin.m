#import "GpuImagePlugin.h"
#import <gpu_image/gpu_image-Swift.h>

@implementation GpuImagePlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftGpuImagePlugin registerWithRegistrar:registrar];
}
@end
