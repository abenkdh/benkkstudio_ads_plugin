package com.benkkstudio.benkkstudio_ads_plugin.max

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxAdViewAdListener
import com.applovin.mediation.MaxError
import com.applovin.mediation.ads.MaxAdView
import com.benkkstudio.benkkstudio_ads_plugin.Constant
import com.benkkstudio.benkkstudio_ads_plugin.Logging
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.platform.PlatformView

class MaxBannerView(context: Context?, viewId: Int, args: Map<*, *>, messenger: BinaryMessenger) : PlatformView, MaxAdViewAdListener {
    private var maxAdView: MaxAdView? = null
    private var channel: MethodChannel? = null
    init {
        channel = MethodChannel(
            messenger,
            Constant.maxBanner + "_" + viewId
        )
        val width = args[Constant.widthParam] as Int?
        val height = args[Constant.heightParam] as Int?
        val layout: FrameLayout.LayoutParams = if (width == null || height == null) FrameLayout.LayoutParams(
            320,
            50
        ) else  FrameLayout.LayoutParams(
            width,
            height
        )
        layout.gravity = Gravity.CENTER
        maxAdView = MaxAdView(args[Constant.placementIdParam].toString(), context)
        maxAdView?.layoutParams = layout
        maxAdView?.setListener(this)
        maxAdView?.loadAd()
    }

    override fun getView(): View? {
        return maxAdView
    }

    override fun dispose() {
        maxAdView?.let {
            it.visibility = View.GONE
            it.destroy()
        }
    }


    override fun onAdLoaded(p0: MaxAd?) {
        channel!!.invokeMethod(Constant.onAdLoadedBannerMax, null)
    }

    override fun onAdDisplayed(p0: MaxAd?) {
        channel!!.invokeMethod(Constant.onAdDisplayedBannerMax, null)
    }

    override fun onAdHidden(p0: MaxAd?) {
        channel!!.invokeMethod(Constant.onAdHiddenBannerMax, null)
    }

    override fun onAdClicked(p0: MaxAd?) {
        channel!!.invokeMethod(Constant.onAdClickedBannerMax, null)
    }

    override fun onAdLoadFailed(message: String?, maxError: MaxError?) {
        channel!!.invokeMethod(Constant.onAdLoadFailedBannerMax, null)
        Logging.log("Exception occurs during loading ad max : $message ${maxError!!.message}")
        maxAdView = null
    }

    override fun onAdDisplayFailed(message: MaxAd?, maxError: MaxError?) {
        channel!!.invokeMethod(Constant.onAdDisplayFailedBannerMax, null)
        Logging.log("Exception occurs during display ad max : $message ${maxError!!.message}")
    }

    override fun onAdExpanded(p0: MaxAd?) {
    }

    override fun onAdCollapsed(p0: MaxAd?) {
    }
}