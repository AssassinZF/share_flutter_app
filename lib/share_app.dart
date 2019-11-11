import 'dart:async';

import 'package:flutter/services.dart';

class ShareApp {
  static const MethodChannel _channel =
      const MethodChannel('share_app');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }
}
