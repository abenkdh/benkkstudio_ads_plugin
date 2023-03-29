package com.benkkstudio.benkkstudio_ads_plugin.unity

import android.app.Activity
import android.content.Context
import android.view.View
import com.benkkstudio.benkkstudio_ads_plugin.Constant
import com.benkkstudio.benkkstudio_ads_plugin.Logging
import com.unity3d.services.banners.BannerErrorInfo
import com.unity3d.services.banners.BannerView
import com.unity3d.services.banners.UnityBannerSize
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.platform.PlatformView

class UnityBannerView(private val context: Context?, viewId: Int, args: Map<*, *>, messenger: BinaryMessenger) :
    PlatformView, BannerView.IListener {
    private var bannerView: BannerView? = null
    private var channel: MethodChannel? = null
    init {
        channel = MethodChannel(
            messenger,
            Constant.unityBanner + "_" + viewId
        )

        val width = args[Constant.widthParam] as Int?
        val height = args[Constant.heightParam] as Int?
        val size =
            if (width == null || height == null) UnityBannerSize(320, 50) else UnityBannerSize(width, height)
        bannerView = BannerView(context as Activity, args[Constant.placementIdParam].toString(), size)
        bannerView?.let {
            it.listener = this
            it.load()
        }
    }

    override fun getView(): View? {
        return if (bannerView != null) {
            bannerView
        } else {
            View(context)
        }
    }

    override fun dispose() {
        bannerView?.let {
            it.visibility = View.GONE
            it.destroy()
        }
    }
    override fun onBannerLoaded(bannerAdView: BannerView?) {
        channel!!.invokeMethod(Constant.onBannerLoadedUnity, null)
    }

    override fun onBannerClick(bannerAdView: BannerView?) {
        channel!!.invokeMethod(Constant.onBannerClickUnity, null)
    }

    override fun onBannerFailedToLoad(bannerAdView: BannerView?, errorInfo: BannerErrorInfo?) {
        Logging.log("Exception occurs during loading ad unity : ${errorInfo!!.errorMessage}")
        channel!!.invokeMethod(Constant.onBannerFailedToLoadUnity, null)
        bannerView = null
    }

    override fun onBannerLeftApplication(bannerView: BannerView?) {
    }
}