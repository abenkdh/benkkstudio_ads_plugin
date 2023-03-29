package com.benkkstudio.benkkstudio_ads_plugin.max

import android.app.Activity
import android.content.Context
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.StandardMessageCodec
import io.flutter.plugin.platform.PlatformView
import io.flutter.plugin.platform.PlatformViewFactory


class MaxBannerFactory(val messenger: BinaryMessenger) : PlatformViewFactory(StandardMessageCodec.INSTANCE){
    private lateinit var activity: Activity
    fun setActivity(activity: Activity){
        this.activity = activity
    }
    override fun create(context: Context?, viewId: Int, args: Any?): PlatformView {
        return MaxBannerView(activity, viewId, args as HashMap<*, *>, messenger)
    }
}