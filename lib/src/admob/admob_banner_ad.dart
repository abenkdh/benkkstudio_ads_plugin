import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

import '../ads_constant.dart';

class AdmobBannerAd extends StatefulWidget {
  final String placementId;
  final double verticalPadding;
  const AdmobBannerAd({super.key, required this.placementId, this.verticalPadding = 0});

  @override
  State<AdmobBannerAd> createState() => _AdmobBannerAdState();
}

class _AdmobBannerAdState extends State<AdmobBannerAd> {
  bool _isLoaded = false;
  @override
  Widget build(BuildContext context) {
    final androidView = AndroidView(
      viewType: Constant.admobBanner,
      creationParams: <String, dynamic>{Constant.placementIdParam: widget.placementId},
      creationParamsCodec: const StandardMessageCodec(),
      onPlatformViewCreated: _onBannerAdViewCreated,
    );
    return Container(
      margin: EdgeInsets.symmetric(vertical: _isLoaded ? widget.verticalPadding : 0),
      height: _isLoaded ? 50 : 0,
      width: 320,
      child: OverflowBox(
        maxHeight: _isLoaded ? 50 : 1,
        minHeight: 0.1,
        alignment: Alignment.bottomCenter,
        child: androidView,
      ),
    );
  }

  void _onBannerAdViewCreated(int id) {
    final channel = MethodChannel('${Constant.admobBanner}_$id');

    channel.setMethodCallHandler((call) async {
      switch (call.method) {
        case Constant.onAdLoadedAdmob:
          setState(() {
            _isLoaded = true;
          });
          break;
        case Constant.onAdFailedToLoadAdmob:
          setState(() {
            _isLoaded = false;
          });
          break;
      }
    });
  }
}
