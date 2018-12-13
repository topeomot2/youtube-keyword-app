package com.topeomot.youtubekeywordapp.util

import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.view.WindowManager
import com.topeomot.youtubekeywordapp.di.ActivityScope
import github.nisrulz.easydeviceinfo.base.DeviceType
import github.nisrulz.easydeviceinfo.base.EasyDeviceMod
import github.nisrulz.easydeviceinfo.base.OrientationType
import github.nisrulz.easydeviceinfo.base.PhoneType
import javax.inject.Inject


@ActivityScope
class DeviceUtils @Inject constructor(val context: Context) {

    var easyDeviceMod: EasyDeviceMod? = null

    init {
        easyDeviceMod = EasyDeviceMod(context)

    }

    fun getFormFactor(activity: Activity): String{
        @DeviceType
        val deviceType = easyDeviceMod?.getDeviceType(activity)

        return when {
            deviceType == DeviceType.WATCH -> "watch"
            deviceType == DeviceType.PHABLET -> "phablet"
            deviceType == DeviceType.TABLET -> "tablet"
            deviceType == DeviceType.TV -> "tv"
            else -> "phone"
        }
    }

    fun getPhoneType(): String{
        @PhoneType
        val phoneType = easyDeviceMod?.phoneType

        return when(phoneType){
            PhoneType.CDMA -> "CDMA"
            PhoneType.GSM -> "GSM"
            PhoneType.NONE -> "None"
            else -> "Unknown"
        }
    }

    fun getOrientation(activity: Activity): String {
        @OrientationType
        val orientationType = easyDeviceMod?.getOrientation(activity)

        return when {
            orientationType == OrientationType.LANDSCAPE -> "landscape"
            orientationType == OrientationType.PORTRAIT -> "portrait"
            else -> "unknown"
        }
    }

    fun getScreenSize(w: WindowManager): IntArray {
        var measuredwidth = 0
        var measuredheight = 0
        val size = Point()

        w.defaultDisplay.getSize(size)
        measuredwidth = size.x
        measuredheight = size.y

        val a = IntArray(2)
        a[0] = measuredwidth
        a[1] = measuredheight

        return a
    }

    fun getScreenDensity(): Int {
        val metrics = context.getResources().getDisplayMetrics()
        return metrics.densityDpi
    }


    fun getDpi(px: Int): Int {
        val screenDensity = getScreenDensity()
        return px * 160 / screenDensity
    }


    fun getPx(dp: Int): Int {
        val screenDensity = getScreenDensity()
        return dp * screenDensity / 160
    }
}