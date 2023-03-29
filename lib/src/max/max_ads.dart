import 'package:flutter/services.dart';

import '../ads_constant.dart';

class MaxAds {
  static const MethodChannel _channel = MethodChannel(Constant.mainChannel);
  static final Map<String, MaxAdListener> appLovinAdListener = <String, MaxAdListener>{
    Constant.onAdLoadedMax: MaxAdListener.adLoaded,
    Constant.onAdDisplayedMax: MaxAdListener.adDisplayed,
    Constant.onAdHiddenMax: MaxAdListener.adHidden,
    Constant.onAdClickedMax: MaxAdListener.adClicked,
    Constant.onAdLoadFailedMax: MaxAdListener.adLoadFailed,
    Constant.onAdDisplayFailedMax: MaxAdListener.onAdDisplayFailed,
  };
  static Future<void> init({
    bool enableLogging = false,
    bool enableAutoReload = false,
  }) async {
    Map<String, dynamic> arguments = {
      Constant.enableLogging: enableLogging,
      Constant.enableAutoReload: enableAutoReload,
    };
    await _channel.invokeMethod(Constant.initMax, arguments);
  }

  static Future<void> loadInterstitial({required String placementId, MaxListener? listener}) async {
    _channel.setMethodCallHandler((MethodCall call) async => _handleMethod(call, listener!));
    final arguments = <String, dynamic>{
      Constant.placementIdParam: placementId,
    };
    await _channel.invokeMethod(Constant.loadInterMax, arguments);
  }

  static Future<void> showInterstitial({MaxListener? listener}) async {
    _channel.setMethodCallHandler((MethodCall call) async => _handleMethod(call, listener!));
    await _channel.invokeMethod(Constant.showInterMax);
  }

  static Future<bool> isInterstitialLoaded() async {
    return await _channel.invokeMethod(Constant.isMaxInterLoaded);
  }

  static Future<void> _handleMethod(MethodCall call, MaxListener listener) async {
    listener(appLovinAdListener[call.method]);
  }
}

enum MaxAdListener {
  adLoaded,
  adLoadFailed,
  adDisplayed,
  adHidden,
  adClicked,
  onAdDisplayFailed,
  onRewardedVideoStarted,
  onRewardedVideoCompleted,
  onUserRewarded
}

typedef MaxListener = Function(MaxAdListener? listener);
