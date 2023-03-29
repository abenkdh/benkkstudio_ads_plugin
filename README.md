# Flutter Ads Plugin for Unity & Applovin Max

This plugin just modification with auto hide banner when not loaded

## Initializing ads
```
await UnityAds.init(gameId: '5221823', testMode: true, enableAutoReload: true); // init unity ads
await MaxAds.init(enableLogging: false, enableAutoReload: true); // init applovin max ads

- gameId = unity game id
- testMode = test mode for unity
- enableAutoReload = this auto load interstitial after closed
```

## Loading & showing interstitial
```
await MaxAds.loadInterstitial(placementId: 'a5024290f917f63b', listener: (listener) => maxListener(listener));
await UnityAds.loadInterstitial(placementId: 'Interstitial_Android', listener: unityLoadListener);

MaxAds.showInterstitial(
   listener: (listener) => maxListener(listener),
);

UnityAds.showInterstitial(
   listener: (listener) => unityShowListener(listener),
);
```
## Interstitial Listener
```
maxListener(MaxAdListener? maxListener) {
  print('abenk : ' + maxListener!.name);
}

unityLoadListener(UnityLoadListener? unityShowListener) {
  print('abenk : ' + unityShowListener!.name);
}

unityShowListener(UnityShowListener? unityShowListener) {
  print('abenk : ' + unityShowListener!.name);
}
```
## Banner Ads
```
MaxBannerAd(placementId: 'APPLOVIN_BANNER_ID')
UnityBannerAd(placementId: 'UNITY_BANNER_ID')
```
## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License

[MIT](https://choosealicense.com/licenses/mit/)
