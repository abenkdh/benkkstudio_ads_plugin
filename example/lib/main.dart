import 'package:benkkstudio_ads_plugin/benkkstudio_ads_plugin.dart';
import 'package:benkkstudio_ads_plugin_example/second_page.dart';
import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';

void main() {
  runApp(const MaterialApp(
    debugShowCheckedModeBanner: false,
    home: MyApp(),
  ));
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  void initState() {
    initAds();
    super.initState();
  }

  Future<void> initAds() async {
    await UnityAds.init(gameId: '5221823', testMode: true, enableAutoReload: true);
    await MaxAds.init(enableLogging: false, enableAutoReload: true);
    loadInterstitial();
  }

  loadInterstitial() async {
    await MaxAds.loadInterstitial(
      placementId: 'a5024290f917f63b',
      listener: (listener) => maxListener(listener),
    );
    await UnityAds.loadInterstitial(placementId: 'Interstitial_Android');
  }

  maxListener(MaxAdListener? maxListener) {
    print('abenk : ' + maxListener!.name);
  }

  unityListener(UnityShowListener? unityShowListener) {
    print('abenk : ' + unityShowListener!.name);
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            mainAxisSize: MainAxisSize.min,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              Container(
                color: Colors.black,
                child: const UnityBannerAd(placementId: 'Banner_Android'),
              ),
              Container(
                color: Colors.black,
                child: const MaxBannerAd(placementId: '26bd8607040a3e4b'),
              ),
              const SizedBox(
                height: 20,
              ),
              ElevatedButton(
                  onPressed: () {
                    MaxAds.showInterstitial(
                      listener: (listener) => maxListener(listener),
                    );
                  },
                  child: const Text('show interstitial max')),
              const SizedBox(
                height: 20,
              ),
              ElevatedButton(
                  onPressed: () {
                    UnityAds.showInterstitial(
                      listener: (listener) => unityListener(listener),
                    );
                  },
                  child: const Text('show interstitial unity')),
              const SizedBox(
                height: 20,
              ),
              ElevatedButton(
                  onPressed: () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(builder: (context) => const SecondPage()),
                    );
                  },
                  child: const Text('second page'))
            ],
          ),
        ),
      ),
    );
  }
}
