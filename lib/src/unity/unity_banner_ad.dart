import 'package:flutter/services.dart';
import 'package:flutter/widgets.dart';

import '../ads_constant.dart';
import '../banner_size.dart';

class UnityBannerAd extends StatefulWidget {
  final String placementId;
  final BannerSize size;
  final double verticalPadding;
  const UnityBannerAd({
    Key? key,
    required this.placementId,
    this.size = BannerSize.standard,
    this.verticalPadding = 0,
  }) : super(key: key);

  @override
  State<UnityBannerAd> createState() => _UnityBannerAdState();
}

class _UnityBannerAdState extends State<UnityBannerAd> {
  bool _isLoaded = false;
  @override
  Widget build(BuildContext context) {
    final androidView = AndroidView(
      viewType: Constant.unityBanner,
      creationParams: <String, dynamic>{
        Constant.placementIdParam: widget.placementId,
        Constant.widthParam: widget.size.width,
        Constant.heightParam: widget.size.height,
      },
      creationParamsCodec: const StandardMessageCodec(),
      onPlatformViewCreated: _onBannerAdViewCreated,
    );
    return Container(
      padding: EdgeInsets.symmetric(vertical: _isLoaded ? widget.verticalPadding : 0),
      height: _isLoaded ? widget.size.height + 0.0 : 0,
      width: widget.size.width + 0.0,
      child: OverflowBox(
        maxHeight: _isLoaded ? widget.size.height + 0.0 : 1,
        minHeight: 0.1,
        alignment: Alignment.bottomCenter,
        child: androidView,
      ),
    );
  }

  void _onBannerAdViewCreated(int id) {
    final channel = MethodChannel('${Constant.unityBanner}_$id');

    channel.setMethodCallHandler((call) async {
      switch (call.method) {
        case Constant.onBannerLoadedUnity:
          setState(() {
            _isLoaded = true;
          });
          break;
        case Constant.onBannerClickUnity:
          break;
        case Constant.onBannerFailedToLoadUnity:
          setState(() {
            _isLoaded = false;
          });
          break;
      }
    });
  }
}
