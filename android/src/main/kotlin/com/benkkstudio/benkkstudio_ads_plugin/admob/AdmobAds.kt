package com.benkkstudio.benkkstudio_ads_plugin.admob

import android.app.Activity
import android.content.Context
import com.benkkstudio.benkkstudio_ads_plugin.Constant
import com.benkkstudio.benkkstudio_ads_plugin.Logging
import com.google.android.gms.ads.*
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.unity3d.ads.UnityAds
import io.flutter.plugin.common.MethodChannel


class AdmobAds(private val context: Context, private val channel: MethodChannel) {
    private var interstitialAd: InterstitialAd? = null
    private lateinit var activity: Activity
    private var enableAutoReload = true
    private var placementId: String? = null
    fun setActivity(activity: Activity){
        this.activity = activity
    }
    fun initialize(args: Map<*, *>): Boolean {
        enableAutoReload = args[Constant.enableAutoReload] as Boolean
        MobileAds.initialize(context) {
            Logging.log("admob initialize complete")
        }
        return true
    }

    fun loadInterstitial(args: Map<*, *>): Boolean {
        placementId = args[Constant.placementIdParam] as String?
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(context,placementId!!, adRequest, interstitialAdLoadCallback)
        return true
    }

    private fun loadInterstitial(): Boolean {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(context,placementId!!, adRequest, interstitialAdLoadCallback)
        return true
    }

    fun showInterstitial(): Boolean{

        if(enableAutoReload){
            if(interstitialAd != null){
                interstitialAd?.let {
                    it.fullScreenContentCallback = fullScreenContentCallback
                    it.show(activity)
                }
            } else {
                loadInterstitial()
            }
            return true
        }
        interstitialAd?.let {
            it.fullScreenContentCallback = fullScreenContentCallback
            it.show(activity)
        }
        return true
    }
    private val interstitialAdLoadCallback: InterstitialAdLoadCallback = object : InterstitialAdLoadCallback(){
        override fun onAdFailedToLoad(adError: LoadAdError) {
            Logging.log("interstitial load error $adError")
            interstitialAd = null
            channel.invokeMethod(Constant.onAdFailedToLoadInterAdmob, null)
        }

        override fun onAdLoaded(inter: InterstitialAd) {
            Logging.log("interstitial loaded successfully")
            interstitialAd = inter
            channel.invokeMethod(Constant.onAdLoadedInterAdmob, null)
        }
    }


    private val fullScreenContentCallback: FullScreenContentCallback = object : FullScreenContentCallback(){
        override fun onAdDismissedFullScreenContent() {
            super.onAdDismissedFullScreenContent()
            Logging.log("interstitial dismissed")
            channel.invokeMethod(Constant.onAdDismissedFullScreenContentInterAdmob, null)
            if(enableAutoReload) {
                loadInterstitial()
            }
        }

        override fun onAdFailedToShowFullScreenContent(p0: AdError) {
            super.onAdFailedToShowFullScreenContent(p0)
            Logging.log("interstitial failed to show $p0")
            channel.invokeMethod(Constant.onAdFailedToShowFullScreenContentInterAdmob, null)
            if(enableAutoReload) {
                loadInterstitial()
            }
        }
    }
}