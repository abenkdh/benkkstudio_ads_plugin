package com.benkkstudio.benkkstudio_ads_plugin.admob

import android.app.Activity
import android.content.Context
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.StandardMessageCodec
import io.flutter.plugin.platform.PlatformView
import io.flutter.plugin.platform.PlatformViewFactory

class AdmobBannerFactory (private val messenger: BinaryMessenger) : PlatformViewFactory(StandardMessageCodec.INSTANCE){
    private lateinit var activity: Activity
    fun setActivity(activity: Activity){
        this.activity = activity
    }
    override fun create(context: Context?, viewId: Int, args: Any?): PlatformView {
        return AdmobBannerView(activity, viewId, args as HashMap<*, *>, messenger)
    }
}