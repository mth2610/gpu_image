import 'dart:async';

import 'package:flutter/services.dart';

class GpuImage {
  static const MethodChannel _channel = const MethodChannel('gpu_image');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  int textureId;

  Future<int> initialize(double width, double height) async {
    textureId = await _channel.invokeMethod('create', {
      'width': width,
      'height': height,
    });
    return textureId;
  }

  Future<String> process({String inputFilePath, String outputFilePath, double width, double height, int filter}) async {
    String outPutFilePath = await _channel.invokeMethod('process', {
      'inputFilePath': inputFilePath,
      'outputFilePath': outputFilePath,
      'filter': filter,
      'width': width,
      'height': height,
    });
    return outPutFilePath;
  }

  Future<Null> dispose() =>
      _channel.invokeMethod('dispose', {'textureId': textureId});

  bool get isInitialized => textureId != null;
}
