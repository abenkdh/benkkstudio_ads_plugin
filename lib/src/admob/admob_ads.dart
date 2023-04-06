import 'package:flutter/services.dart';

import '../ads_constant.dart';

class AdmobAds {
  static const MethodChannel _channel = MethodChannel(Constant.mainChannel);
  static final Map<String, AdmobAdListener> admobAdListener = <String, AdmobAdListener>{
    Constant.onAdFailedToLoadInterAdmob: AdmobAdListener.onAdFailedToLoadInterAdmob,
    Constant.onAdLoadedInterAdmob: AdmobAdListener.onAdLoadedInterAdmob,
    Constant.onAdDismissedFullScreenContentInterAdmob: AdmobAdListener.onAdDismissedFullScreenContentInterAdmob,
    Constant.onAdFailedToShowFullScreenContentInterAdmob: AdmobAdListener.onAdFailedToShowFullScreenContentInterAdmob
  };

  static Future<void> init({
    bool enableAutoReload = false,
  }) async {
    Map<String, dynamic> arguments = {Constant.enableAutoReload: enableAutoReload};
    await _channel.invokeMethod(Constant.initAdmob, arguments);
  }

  static Future<void> loadInterstitial({required String placementId, AdmobListener? listener}) async {
    _channel.setMethodCallHandler((MethodCall call) async => _handleMethod(call, listener!));
    final arguments = <String, dynamic>{Constant.placementIdParam: placementId};
    await _channel.invokeMethod(Constant.loadInterAdmob, arguments);
  }

  static Future<void> showInterstitial({AdmobListener? listener}) async {
    _channel.setMethodCallHandler((MethodCall call) async => _handleMethod(call, listener!));
    await _channel.invokeMethod(Constant.showInterAdmob);
  }

  static Future<void> _handleMethod(MethodCall call, AdmobListener listener) async {
    listener(admobAdListener[call.method]);
  }
}

enum AdmobAdListener {
  onAdFailedToLoadInterAdmob,
  onAdLoadedInterAdmob,
  onAdDismissedFullScreenContentInterAdmob,
  onAdFailedToShowFullScreenContentInterAdmob
}

typedef AdmobListener = Function(AdmobAdListener? listener);
