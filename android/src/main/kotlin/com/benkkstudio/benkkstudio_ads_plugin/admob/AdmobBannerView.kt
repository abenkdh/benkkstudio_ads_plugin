package com.benkkstudio.benkkstudio_ads_plugin.admob

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout

import com.benkkstudio.benkkstudio_ads_plugin.Constant
import com.benkkstudio.benkkstudio_ads_plugin.Logging
import com.google.android.gms.ads.*
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.platform.PlatformView

@SuppressLint("MissingPermission")
class AdmobBannerView(context: Context, viewId: Int, args: Map<*, *>, messenger: BinaryMessenger) : PlatformView, AdListener() {
    private var adView : AdView? = null
    private var channel: MethodChannel? = null
    init {
        channel = MethodChannel(
            messenger,
            Constant.admobBanner + "_" + viewId
        )
        val adRequest = AdRequest.Builder().build()
        adView = AdView(context)
        adView?.let {
            it.setAdSize(AdSize.BANNER)
            it.adUnitId = args[Constant.placementIdParam].toString()
            it.adListener = this
            it.loadAd(adRequest)
        }

    }

    override fun getView(): View? {
        return adView
    }

    override fun dispose() {
        adView?.let {
            it.visibility = View.GONE
            it.destroy()
        }
    }

    override fun onAdFailedToLoad(p0: LoadAdError) {
        super.onAdFailedToLoad(p0)
        Logging.log("admob banner failed to load $p0")
        channel!!.invokeMethod(Constant.onAdFailedToLoadAdmob, null)
    }

    override fun onAdLoaded() {
        super.onAdLoaded()
        Logging.log("admob banner loaded")
        channel!!.invokeMethod(Constant.onAdLoadedAdmob, null)
    }
}