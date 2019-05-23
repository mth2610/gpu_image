import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:gpu_image/gpu_image.dart';

void main() {
  const MethodChannel channel = MethodChannel('gpu_image');

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await GpuImage.platformVersion, '42');
  });
}
