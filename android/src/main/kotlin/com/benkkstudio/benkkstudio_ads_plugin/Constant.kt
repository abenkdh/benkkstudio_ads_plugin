package com.benkkstudio.benkkstudio_ads_plugin

object Constant {
    const val mainChannel = "com.benkkstudios.xyz/adsplugin";

    // Applovin Max
    const val initMax = "initMax";
    const val loadInterMax = "${mainChannel}loadInterMax";
    const val showInterMax = "${mainChannel}showInterMax";
    const val isMaxInterLoaded = "${mainChannel}isMaxInterLoaded";
    const val maxBanner = "${mainChannel}maxBanner";

    //parameter
    const val enableLogging = "enableLogging";

    //max inter listener
    const val onAdLoadedMax = "onAdLoadedMax";
    const val onAdDisplayedMax = "onAdDisplayedMax";
    const val onAdHiddenMax = "onAdHiddenMax";
    const val onAdClickedMax = "onAdClickedMax";
    const val onAdLoadFailedMax = "onAdLoadFailedMax";
    const val onAdDisplayFailedMax = "onAdDisplayFailedMax";

    //max banner listener
    const val onAdLoadedBannerMax = "onAdDisplayFailedMax";
    const val onAdDisplayedBannerMax = "onAdDisplayedBannerMax";
    const val onAdHiddenBannerMax = "onAdHiddenBannerMax";
    const val onAdClickedBannerMax = "onAdClickedBannerMax";
    const val onAdLoadFailedBannerMax = "onAdLoadFailedBannerMax";
    const val onAdDisplayFailedBannerMax = "onAdDisplayFailedBannerMax";

    // Unity Ads
    const val initUnity = "initUnity";
    const val loadInterUnity = "${mainChannel}loadInterUnity";
    const val showInterUnity = "${mainChannel}showInterUnity";
    const val isUnityInterLoaded = "${mainChannel}isUnityInterLoaded";
    const val unityBanner = "${mainChannel}unityBanner";

    //parameter
    const val gameId = "gameId";
    const val testMode = "testMode";


    //unity inter listener
    const val onUnityAdsAdLoaded = "onUnityAdsAdLoaded";
    const val onUnityAdsFailedToLoad = "onUnityAdsFailedToLoad";

    const val onUnityAdsShowFailure = "onUnityAdsShowFailure";
    const val onUnityAdsShowStart = "onUnityAdsShowStart";
    const val onUnityAdsShowClick = "onUnityAdsShowClick";
    const val onUnityAdsShowComplete = "onUnityAdsShowComplete";
    const val onUnityAdsShowSkipped = "onUnityAdsShowSkipped";

    //unity banner listener
    const val onBannerLoadedUnity = "onBannerLoadedUnity";
    const val onBannerClickUnity = "onBannerClickUnity";
    const val onBannerFailedToLoadUnity = "onBannerFailedToLoadUnity";

    //shared parameter
    const val enableAutoReload = "enableAutoReload";
    const val placementIdParam = "placementIdParam";
    const val heightParam = "heightParam";
    const val widthParam = "widthParam";
}