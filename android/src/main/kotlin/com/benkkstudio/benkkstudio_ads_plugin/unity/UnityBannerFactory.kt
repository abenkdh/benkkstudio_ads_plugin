package com.benkkstudio.benkkstudio_ads_plugin.unity

import android.app.Activity
import android.content.Context
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.StandardMessageCodec
import io.flutter.plugin.platform.PlatformView
import io.flutter.plugin.platform.PlatformViewFactory

class UnityBannerFactory(private val messenger: BinaryMessenger) : PlatformViewFactory(StandardMessageCodec.INSTANCE) {
    private var activity: Activity? = null
    override fun create(context: Context?, viewId: Int, args: Any?): PlatformView {
        return UnityBannerView(activity, viewId, args as HashMap<*, *>, messenger)
    }

    fun setActivity(activity: Activity?) {
        this.activity = activity
    }
}