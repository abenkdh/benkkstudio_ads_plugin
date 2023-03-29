import 'package:flutter/services.dart';

import '../ads_constant.dart';

class UnityAds {
  static const MethodChannel _channel = MethodChannel(Constant.mainChannel);
  static final Map<String, UnityLoadListener> unityLoadListener = <String, UnityLoadListener>{
    Constant.onUnityAdsAdLoaded: UnityLoadListener.onUnityAdsAdLoaded,
    Constant.onUnityAdsFailedToLoad: UnityLoadListener.onUnityAdsFailedToLoad
  };

  static final Map<String, UnityShowListener> unityShowListener = <String, UnityShowListener>{
    Constant.onUnityAdsShowFailure: UnityShowListener.onUnityAdsShowFailure,
    Constant.onUnityAdsShowStart: UnityShowListener.onUnityAdsShowStart,
    Constant.onUnityAdsShowClick: UnityShowListener.onUnityAdsShowClick,
    Constant.onUnityAdsShowComplete: UnityShowListener.onUnityAdsShowComplete,
    Constant.onUnityAdsShowSkipped: UnityShowListener.onUnityAdsShowSkipped
  };
  static Future<void> init({
    required String gameId,
    bool testMode = false,
    bool enableAutoReload = true,
  }) async {
    Map<String, dynamic> arguments = {
      Constant.gameId: gameId,
      Constant.testMode: testMode,
      Constant.enableAutoReload: enableAutoReload,
    };
    await _channel.invokeMethod(Constant.initUnity, arguments);
  }

  static Future<void> loadInterstitial({required String placementId, UnityLoadListeners? listener}) async {
    _channel.setMethodCallHandler((MethodCall call) async => _handleLoadMethod(call, listener!));
    final arguments = <String, dynamic>{
      Constant.placementIdParam: placementId,
    };
    await _channel.invokeMethod(Constant.loadInterUnity, arguments);
  }

  static Future<void> showInterstitial({UnityShowListeners? listener}) async {
    _channel.setMethodCallHandler((MethodCall call) async => _handleShowMethod(call, listener!));
    await _channel.invokeMethod(Constant.showInterUnity);
  }

  static Future<bool> isInterstitialLoaded() async {
    return await _channel.invokeMethod(Constant.isUnityInterLoaded);
  }

  static Future<void> _handleLoadMethod(MethodCall call, UnityLoadListeners listener) async {
    listener(unityLoadListener[call.method]);
  }

  static Future<void> _handleShowMethod(MethodCall call, UnityShowListeners listener) async {
    listener(unityShowListener[call.method]);
  }
}

enum UnityLoadListener {
  onUnityAdsAdLoaded,
  onUnityAdsFailedToLoad,
}

typedef UnityLoadListeners = Function(UnityLoadListener? listener);

enum UnityShowListener {
  onUnityAdsShowFailure,
  onUnityAdsShowStart,
  onUnityAdsShowClick,
  onUnityAdsShowComplete,
  onUnityAdsShowSkipped,
}

typedef UnityShowListeners = Function(UnityShowListener? listener);
