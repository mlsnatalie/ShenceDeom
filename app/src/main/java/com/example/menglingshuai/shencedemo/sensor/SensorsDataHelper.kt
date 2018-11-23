package com.example.menglingshuai.shencedemo.sensor

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.telephony.TelephonyManager
import android.text.TextUtils
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI
import org.json.JSONException
import org.json.JSONObject

object SensorsDataHelper {

    var debug: Boolean = false

    fun init(context: Context, globalParams: HashMap<String, String>, ignoreClasses: List<Class<*>>?, serverUrl: String) {
        if (debug) {
            SensorsDataAPI.sharedInstance(context, serverUrl,
                SensorsDataAPI.DebugMode.DEBUG_AND_TRACK)
        } else {
            SensorsDataAPI.sharedInstance(context, serverUrl,
                SensorsDataAPI.DebugMode.DEBUG_OFF)
        }
        if (ignoreClasses != null && !ignoreClasses.isEmpty()) {
            SensorsDataAPI.sharedInstance(context).ignoreAutoTrackActivities(ignoreClasses)
        }
        try {
            //所有事件添加渠道id
            val properties = JSONObject()
            for ((key, value) in globalParams) {
                properties.put(key, value)
            }
//            properties.put("channelId", ChannelId)
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                if (telephonyManager != null) {
                    val imei = telephonyManager.deviceId
                    properties.put("imei", imei)
                }
            }
            SensorsDataAPI.sharedInstance(context).registerSuperProperties(properties)

            // 追踪渠道包的安装效果
            SensorsDataAPI.sharedInstance(context).trackInstallation("AppInstall", properties)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }

    fun setMaxCacheSize(context: Context, maxCacheSize: Long) {
        SensorsDataAPI.sharedInstance(context).maxCacheSize = maxCacheSize
    }

    fun enableAutoTrack(context: Context) {
        // 打开自动采集, 并指定追踪哪些 AutoTrack 事件
        val eventTypeList = ArrayList<SensorsDataAPI.AutoTrackEventType>()
        // $AppStart
        eventTypeList.add(SensorsDataAPI.AutoTrackEventType.APP_START)
        // $AppEnd
        eventTypeList.add(SensorsDataAPI.AutoTrackEventType.APP_END)
        // $AppViewScreen
        eventTypeList.add(SensorsDataAPI.AutoTrackEventType.APP_VIEW_SCREEN)
        // $AppClick
        eventTypeList.add(SensorsDataAPI.AutoTrackEventType.APP_CLICK)
        SensorsDataAPI.sharedInstance(context).enableAutoTrack(eventTypeList)
    }

    fun pageStart(context: Context, pageName: String) {
        SensorsDataAPI.sharedInstance(context).trackTimerBegin(pageName)
    }

    fun pageEnd(context: Context, pageName: String) {
        SensorsDataAPI.sharedInstance(context).trackTimerEnd(pageName)
    }

    fun login(context: Context, distinctId: String) {
        SensorsDataAPI.sharedInstance(context).login(distinctId)
    }

    fun logout(context: Context) {
        SensorsDataAPI.sharedInstance(context).logout()
    }

    fun unRegisterSuperProperties(context: Context, propertyName: String) {
        SensorsDataAPI.sharedInstance(context).unregisterSuperProperty(propertyName)
    }

    fun clearSuperProperties(context: Context) {
        SensorsDataAPI.sharedInstance(context).clearSuperProperties()
    }

    fun trackTimerBegin(context: Context, eventName: String) {
        SensorsDataAPI.sharedInstance(context).trackTimerBegin(eventName)
    }

    fun trackTimerEnd(context: Context, eventName: String, properties: JSONObject) {
        SensorsDataAPI.sharedInstance(context).trackTimerEnd(eventName, properties)
    }

    fun getAnonymousId(context: Context): String {
        return SensorsDataAPI.sharedInstance(context).anonymousId
    }

    fun getDistinctId(context: Context): String {
        return SensorsDataAPI.sharedInstance(context).distinctId
    }

    fun profileSet(context: Context, json: String) {
        var jsonObject: JSONObject? = null
        try {
            jsonObject = JSONObject(json)
            profileSet(context, jsonObject)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }

    fun profileSet(context: Context, jsonObject: JSONObject) {
        SensorsDataAPI.sharedInstance(context).profileSet(jsonObject)
    }

    fun profileUnset(context: Context, propertyName: String) {
        SensorsDataAPI.sharedInstance(context).profileUnset(propertyName)
    }

    fun profileSetOnce(context: Context, properties: JSONObject) {
        SensorsDataAPI.sharedInstance(context).profileSetOnce(properties)
    }

    fun profileIncrement(context: Context, name: String, number: Number) {
        SensorsDataAPI.sharedInstance(context).profileIncrement(name, number)
    }

    fun profileIncrement(context: Context, properties: HashMap<String, Number>) {
        SensorsDataAPI.sharedInstance(context).profileIncrement(properties)
    }

    fun profileAppend(context: Context, name: String, set: Set<String>) {
        SensorsDataAPI.sharedInstance(context).profileAppend(name, set)
    }

    fun profileAppend(context: Context, name: String, property: String) {
        SensorsDataAPI.sharedInstance(context).profileAppend(name, property)
    }

    fun identify(context: Context, distinctId: String) {
        SensorsDataAPI.sharedInstance(context).identify(distinctId)
    }

    fun setFlushNetworkPolicy(context: Context, policy: Int) {
        SensorsDataAPI.sharedInstance(context).setFlushNetworkPolicy(policy)
    }

    fun track(sensorsData: SensorsData?) {
        if (sensorsData != null) {
            SensorsDataAPI.sharedInstance().track(sensorsData.eventName, sensorsData.jsonObject)
        }
    }

    class SensorsDataBuilder {
        private val sensorsData = SensorsData()

        constructor(eventName: String) {
            sensorsData.eventName = if (TextUtils.isEmpty(eventName)) "" else eventName
        }

        fun withParam(key: String, value: Any?): SensorsDataBuilder {
            try {
                if (sensorsData.jsonObject == null) {
                    sensorsData.jsonObject = JSONObject()
                }
                this.sensorsData.jsonObject?.put(key, value)
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            return this
        }

        fun withMap(param: Map<String, Any>?): SensorsDataBuilder {
            if (param != null && param.isNotEmpty()) {
                try {
                    if (sensorsData.jsonObject == null) {
                        sensorsData.jsonObject = JSONObject()
                    }
                    val strings = param.keys
                    for (string in strings) {
                        sensorsData.jsonObject?.put(string, param[string])
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
            return this
        }

        fun withTitle(title: String): SensorsDataBuilder {
            return if (sensorsData.eventName.startsWith("$")) {
                withParam("$" + SensorsConstant.KEY_PAGE_TITLE, title)
            } else {
                withParam(SensorsConstant.KEY_PAGE_TITLE, title)
            }
        }

        fun withEventName(eventName: String): SensorsDataBuilder {
            return if (sensorsData.eventName.startsWith("$")) {
                withParam("$" + SensorsConstant.KEY_EVENT_NAME, eventName)
            } else {
                withParam(SensorsConstant.KEY_EVENT_NAME, eventName)
            }
        }


        fun build(): SensorsData {
            return sensorsData
        }

        fun track() {
            if (!TextUtils.isEmpty(sensorsData.eventName)) {
                if (sensorsData.jsonObject == null) {
                    return
                }
                SensorsDataHelper.track(sensorsData)
            }
        }
    }
}
