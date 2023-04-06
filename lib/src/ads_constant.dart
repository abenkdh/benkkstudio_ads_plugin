class Constant {
  static const mainChannel = "com.benkkstudios.xyz/adsplugin";

  // Applovin Max
  static const initMax = "initMax";
  static const loadInterMax = "${mainChannel}loadInterMax";
  static const showInterMax = "${mainChannel}showInterMax";
  static const isMaxInterLoaded = "${mainChannel}isMaxInterLoaded";
  static const maxBanner = "${mainChannel}maxBanner";

  //parameter
  static const enableLogging = "enableLogging";

  //max inter listener
  static const onAdLoadedMax = "onAdLoadedMax";
  static const onAdDisplayedMax = "onAdDisplayedMax";
  static const onAdHiddenMax = "onAdHiddenMax";
  static const onAdClickedMax = "onAdClickedMax";
  static const onAdLoadFailedMax = "onAdLoadFailedMax";
  static const onAdDisplayFailedMax = "onAdDisplayFailedMax";

  //max banner listener
  static const onAdLoadedBannerMax = "onAdDisplayFailedMax";
  static const onAdDisplayedBannerMax = "onAdDisplayedBannerMax";
  static const onAdHiddenBannerMax = "onAdHiddenBannerMax";
  static const onAdClickedBannerMax = "onAdClickedBannerMax";
  static const onAdLoadFailedBannerMax = "onAdLoadFailedBannerMax";
  static const onAdDisplayFailedBannerMax = "onAdDisplayFailedBannerMax";

  // Unity Ads
  static const initUnity = "initUnity";
  static const loadInterUnity = "${mainChannel}loadInterUnity";
  static const showInterUnity = "${mainChannel}showInterUnity";
  static const isUnityInterLoaded = "${mainChannel}isUnityInterLoaded";
  static const unityBanner = "${mainChannel}unityBanner";

  //parameter
  static const gameId = "gameId";
  static const testMode = "testMode";

  //unity inter listener
  static const onUnityAdsAdLoaded = "onUnityAdsAdLoaded";
  static const onUnityAdsFailedToLoad = "onUnityAdsFailedToLoad";

  static const onUnityAdsShowFailure = "onUnityAdsShowFailure";
  static const onUnityAdsShowStart = "onUnityAdsShowStart";
  static const onUnityAdsShowClick = "onUnityAdsShowClick";
  static const onUnityAdsShowComplete = "onUnityAdsShowComplete";
  static const onUnityAdsShowSkipped = "onUnityAdsShowSkipped";

  //unity banner listener
  static const onBannerLoadedUnity = "onBannerLoadedUnity";
  static const onBannerClickUnity = "onBannerClickUnity";
  static const onBannerFailedToLoadUnity = "onBannerFailedToLoadUnity";

  // Admob
  static const initAdmob = "initAdmob";
  static const loadInterAdmob = "${mainChannel}loadInterAdmob";
  static const showInterAdmob = "${mainChannel}showInterAdmob";
  static const isAdmobInterLoaded = "${mainChannel}isAdmobInterLoaded";
  static const admobBanner = "${mainChannel}admobBanner";

  //Admob banner listener
  static const onAdFailedToLoadAdmob = "onAdFailedToLoadAdmob";
  static const onAdLoadedAdmob = "onAdLoadedAdmob";

  //Admob inter listener
  static const onAdFailedToLoadInterAdmob = "onAdFailedToLoadInterAdmob";
  static const onAdLoadedInterAdmob = "onAdLoadedInterAdmob";
  static const onAdDismissedFullScreenContentInterAdmob = "onAdDismissedFullScreenContentInterAdmob";
  static const onAdFailedToShowFullScreenContentInterAdmob = "onAdFailedToShowFullScreenContentInterAdmob";

  //shared parameter
  static const enableAutoReload = "enableAutoReload";
  static const placementIdParam = "placementIdParam";
  static const heightParam = "heightParam";
  static const widthParam = "widthParam";
}
