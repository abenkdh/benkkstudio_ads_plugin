package com.benkkstudio.benkkstudio_ads_plugin

import android.content.Context
import com.benkkstudio.benkkstudio_ads_plugin.max.MaxAds
import com.benkkstudio.benkkstudio_ads_plugin.max.MaxBannerFactory
import com.benkkstudio.benkkstudio_ads_plugin.unity.UnityAds
import com.benkkstudio.benkkstudio_ads_plugin.unity.UnityBannerFactory
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result


/** BenkkstudioAdsPlugin */
class BenkkstudioAdsPlugin : FlutterPlugin, MethodCallHandler, ActivityAware {
    private lateinit var context: Context
    private lateinit var channel: MethodChannel
    private lateinit var unityAds: UnityAds
    private lateinit var maxAds: MaxAds
    private lateinit var maxBannerFactory: MaxBannerFactory
    private lateinit var unityBannerFactory: UnityBannerFactory
    override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, Constant.mainChannel)
        channel.setMethodCallHandler(this)
        context = flutterPluginBinding.applicationContext
        unityAds = UnityAds(context, channel)
        maxAds = MaxAds(context, channel)
        maxBannerFactory = MaxBannerFactory(flutterPluginBinding.binaryMessenger)
        unityBannerFactory = UnityBannerFactory(flutterPluginBinding.binaryMessenger)
        flutterPluginBinding.platformViewRegistry
            .registerViewFactory(Constant.maxBanner, maxBannerFactory)
        flutterPluginBinding.platformViewRegistry
            .registerViewFactory(Constant.unityBanner, unityBannerFactory)
    }

    override fun onMethodCall(call: MethodCall, result: Result) {
        val arguments = if (call.arguments != null) {
            call.arguments as Map<*, *>
        } else {
            null
        }
        when (call.method) {
            Constant.initMax -> result.success(maxAds.initialize(arguments!!))
            Constant.loadInterMax -> result.success(maxAds.loadInterstitial(arguments!!))
            Constant.showInterMax -> result.success(maxAds.showInterstitial())
            Constant.isMaxInterLoaded -> result.success(maxAds.isInterstitialLoaded())

            Constant.initUnity -> result.success(unityAds.initialize(arguments!!))
            Constant.loadInterUnity -> result.success(unityAds.loadInterstitial(arguments!!))
            Constant.showInterUnity -> result.success(unityAds.showInterstitial())
            Constant.isUnityInterLoaded -> result.success(unityAds.isInterstitialLoaded())
            else -> result.notImplemented()
        }
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        maxAds.setActivity(binding.activity)
        unityAds.setActivity(binding.activity)
        maxBannerFactory.setActivity(binding.activity)
        unityBannerFactory.setActivity(binding.activity)
    }

    override fun onDetachedFromActivityForConfigChanges() {}

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {}

    override fun onDetachedFromActivity() {}
}
