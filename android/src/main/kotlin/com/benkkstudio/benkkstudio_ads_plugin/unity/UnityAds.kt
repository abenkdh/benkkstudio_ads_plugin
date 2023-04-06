package com.benkkstudio.benkkstudio_ads_plugin.unity

import android.app.Activity
import android.content.Context
import com.applovin.impl.sdk.c.b.ex
import com.benkkstudio.benkkstudio_ads_plugin.Constant
import com.benkkstudio.benkkstudio_ads_plugin.Logging
import com.unity3d.ads.IUnityAdsInitializationListener
import com.unity3d.ads.IUnityAdsLoadListener
import com.unity3d.ads.IUnityAdsShowListener
import com.unity3d.ads.UnityAds
import com.unity3d.ads.metadata.PlayerMetaData
import io.flutter.plugin.common.MethodChannel

class UnityAds(private val context: Context, private val channel: MethodChannel) : IUnityAdsLoadListener,
    IUnityAdsShowListener {
    private lateinit var activity: Activity
    private var placementId: String? = null
    private var enableAutoReload = true
    private var testMode = false
    private var isInterstitialLoaded = false
    fun setActivity(activity: Activity){
        this.activity = activity
    }

    fun initialize(args: Map<*, *>): Boolean {
        Logging.log(args)
        val gameId = args[Constant.gameId] as String?
        testMode = args[Constant.testMode] as Boolean
        enableAutoReload = args[Constant.enableAutoReload] as Boolean
        UnityAds.initialize(context, gameId, testMode,  null)
        return true
    }

    fun loadInterstitial(args: Map<*, *>): Boolean {
        placementId = args[Constant.placementIdParam] as String?
        UnityAds.load(placementId, this)
        return true
    }

    private fun loadInterstitial(): Boolean {
        UnityAds.load(placementId, this)
        return true
    }

    fun showInterstitial(): Boolean {
        if(enableAutoReload){
            if(isInterstitialLoaded){
                UnityAds.show(activity, placementId, this)
            } else {
                loadInterstitial()
            }
            return true
        }
        UnityAds.show(activity, placementId, this)
        return true
    }

    fun isInterstitialLoaded(): Boolean {
        return isInterstitialLoaded
    }

    override fun onUnityAdsAdLoaded(placementId: String?) {
        channel.invokeMethod(Constant.onUnityAdsAdLoaded, null)
        isInterstitialLoaded = true
    }

    override fun onUnityAdsFailedToLoad(
        placementId: String?,
        error: UnityAds.UnityAdsLoadError?,
        message: String?
    ) {
        Logging.log("Exception occurs during loading ad: $placementId $message")
        channel.invokeMethod(Constant.onUnityAdsFailedToLoad, null)
        isInterstitialLoaded = false
    }

    override fun onUnityAdsShowFailure(
        placementId: String?,
        error: UnityAds.UnityAdsShowError?,
        message: String?
    ) {
        Logging.log("Exception occurs during loading ad: $placementId $message")
        channel.invokeMethod(Constant.onUnityAdsShowFailure, null)
    }

    override fun onUnityAdsShowStart(placementId: String?) {
        channel.invokeMethod(Constant.onUnityAdsShowStart, null)
    }

    override fun onUnityAdsShowClick(placementId: String?) {
        channel.invokeMethod(Constant.onUnityAdsShowClick, null)
    }

    override fun onUnityAdsShowComplete(
        placementId: String?,
        state: UnityAds.UnityAdsShowCompletionState?
    ) {
        if (state == UnityAds.UnityAdsShowCompletionState.SKIPPED) {
            channel.invokeMethod(Constant.onUnityAdsShowSkipped, null)
        } else if (state == UnityAds.UnityAdsShowCompletionState.COMPLETED) {
            channel.invokeMethod(Constant.onUnityAdsShowComplete, null)
        }
        if(enableAutoReload){
            loadInterstitial()
        }
    }
}