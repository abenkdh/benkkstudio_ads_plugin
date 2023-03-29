import 'package:benkkstudio_ads_plugin/src/banner_size.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';
import 'package:flutter/widgets.dart';

import '../ads_constant.dart';

class MaxBannerAd extends StatefulWidget {
  final String placementId;
  final double verticalPadding;

  /// This widget is used to contain Banner Ads.
  const MaxBannerAd({
    Key? key,
    required this.placementId,
    this.verticalPadding = 0,
  }) : super(key: key);

  @override
  MaxBannerAdState createState() => MaxBannerAdState();
}

class MaxBannerAdState extends State<MaxBannerAd> {
  bool _isLoaded = false;

  @override
  Widget build(BuildContext context) {
    if (defaultTargetPlatform == TargetPlatform.android) {
      final androidView = AndroidView(
          viewType: Constant.maxBanner,
          creationParams: <String, dynamic>{
            Constant.placementIdParam: widget.placementId,
            Constant.heightParam: BannerSize.standard.width,
            Constant.widthParam: BannerSize.standard.height,
          },
          creationParamsCodec: const StandardMessageCodec(),
          onPlatformViewCreated: _onBannerAdViewCreated);
      return Container(
        margin: EdgeInsets.symmetric(vertical: _isLoaded ? widget.verticalPadding : 0),
        height: _isLoaded ? BannerSize.standard.height + 0.0 : 0,
        width: BannerSize.standard.width + 0.0,
        child: OverflowBox(
          maxHeight: _isLoaded ? BannerSize.standard.height + 0.0 : 1,
          minHeight: 0.1,
          alignment: Alignment.bottomCenter,
          child: androidView,
        ),
      );
    }
    return Container();
  }

  void _onBannerAdViewCreated(int id) {
    final channel = MethodChannel('${Constant.maxBanner}_$id');

    channel.setMethodCallHandler((call) async {
      switch (call.method) {
        case Constant.onAdLoadedBannerMax:
          setState(() {
            _isLoaded = true;
          });
          break;
        case Constant.onAdDisplayedBannerMax:
          break;
        case Constant.onAdHiddenBannerMax:
          break;
        case Constant.onAdClickedBannerMax:
          break;
        case Constant.onAdLoadFailedBannerMax:
          setState(() {
            _isLoaded = false;
          });
          break;
        case Constant.onAdDisplayFailedBannerMax:
          setState(() {
            _isLoaded = false;
          });
          break;
      }
    });
  }
}
