package com.benkkstudio.benkkstudio_ads_plugin.max

import android.app.Activity
import android.content.Context
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxAdListener
import com.applovin.mediation.MaxError
import com.applovin.mediation.ads.MaxInterstitialAd
import com.applovin.sdk.AppLovinMediationProvider
import com.applovin.sdk.AppLovinSdk
import com.benkkstudio.benkkstudio_ads_plugin.Constant
import com.benkkstudio.benkkstudio_ads_plugin.Logging
import io.flutter.plugin.common.MethodChannel

class MaxAds(private val context: Context, private val channel: MethodChannel): MaxAdListener {
    private var interstitialAd: MaxInterstitialAd? = null
    private lateinit var activity: Activity
    private var enableLogging = true
    private var enableAutoReload = true
    private var placementId: String? = null
    fun setActivity(activity: Activity){
        this.activity = activity
    }

    fun initialize(args: Map<*, *>): Boolean {
        enableLogging = args[Constant.enableLogging] as Boolean
        enableAutoReload = args[Constant.enableAutoReload] as Boolean
        AppLovinSdk.getInstance(context).settings.setVerboseLogging(enableLogging)
        AppLovinSdk.getInstance(context).mediationProvider = AppLovinMediationProvider.MAX
        AppLovinSdk.initializeSdk(context)
        return true
    }


    fun loadInterstitial(args: Map<*, *>) : Boolean{
        placementId = args[Constant.placementIdParam] as String?
        interstitialAd = MaxInterstitialAd(placementId, activity)
        interstitialAd?.let {
            it.setListener(this)
            it.loadAd()
        }
        return true
    }

    private fun loadInterstitial() : Boolean{
        interstitialAd = MaxInterstitialAd(placementId, activity)
        interstitialAd?.let {
            it.setListener(this)
            it.loadAd()
        }
        return true
    }

    fun showInterstitial() : Boolean {
        if(enableAutoReload){
            if(interstitialAd == null){
                loadInterstitial()
            } else {
                interstitialAd?.showAd()
            }
            return true
        }
        interstitialAd?.showAd()
        return true
    }

    fun isInterstitialLoaded() : Boolean {
        return if(interstitialAd == null){
            false
        } else {
            interstitialAd?.isReady!!
        }
    }

    override fun onAdLoaded(p0: MaxAd?) {
        if(enableLogging){
            Logging.log("onAdLoaded ${p0!!.adUnitId!!}")
        }
        channel.invokeMethod(Constant.onAdLoadedMax, null)
    }

    override fun onAdDisplayed(p0: MaxAd?) {
        if(enableLogging){
            Logging.log("onAdDisplayed ${p0!!.adUnitId!!}")
        }
        channel.invokeMethod(Constant.onAdDisplayedMax, null)
    }

    override fun onAdHidden(p0: MaxAd?) {
        if(enableLogging){
            Logging.log("onAdHidden ${p0!!.adUnitId!!}")
        }
        channel.invokeMethod(Constant.onAdHiddenMax, null)
        if(enableAutoReload){
            loadInterstitial()
        }
    }

    override fun onAdClicked(p0: MaxAd?) {
        if(enableLogging){
            Logging.log("onAdClicked ${p0!!.adUnitId!!}")
        }
        channel.invokeMethod(Constant.onAdClickedMax, null)
    }

    override fun onAdLoadFailed(p0: String?, p1: MaxError?) {
        if(enableLogging){
            Logging.log("onAdLoadFailed ${p1!!.message!!}")
        }
        channel.invokeMethod(Constant.onAdLoadFailedMax, null)
        interstitialAd = null
    }

    override fun onAdDisplayFailed(p0: MaxAd?, p1: MaxError?) {
        if(enableLogging){
            Logging.log("onAdDisplayFailed ${p1!!.message!!}")
        }
        channel.invokeMethod(Constant.onAdDisplayFailedMax, null)
        interstitialAd = null
    }
}