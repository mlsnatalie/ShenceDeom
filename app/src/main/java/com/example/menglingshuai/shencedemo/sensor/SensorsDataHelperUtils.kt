package com.example.menglingshuai.shencedemo.sensor

import com.spero.vision.sensor.SensorsDataConstant


/**
 * introduce：这里写介绍
 * createBy：lingshuai.meng
 * email：mlsnatalie@163.com
 * time: 23/11/18
 */
object SensorsDataHelperUtils {

    /**
     * 点击事件不带参数
     * @param title   标题
     * @param content 内容
     */
    fun sensorsDataWithTitleContent(title: String, content: String) {
        SensorsDataHelper.SensorsDataBuilder(SensorsDataConstant.Event.EVENT_NAME_CLICK)
            .withTitle(title)
            .withEventName(content)
            .track()
    }

    /**
     * 点击事件自定义参数一个
     * @param title   标题
     * @param content 内容
     * @param key     params key
     * @param value   params value
     */
    fun sensorsDataWithCodeContentParams(title: String, content: String, key: String, value: String) {
        SensorsDataHelper.SensorsDataBuilder(SensorsDataConstant.Event.EVENT_NAME_CLICK)
            .withTitle(title)
            .withEventName(content)
            .withParam(key, value)
            .track()
    }

    /**
     * 点击事件带参数俩个
     * @param title
     * @param content
     * @param name
     * @param symbol
     */
    fun sensorsDataWithNameSymbol(title: String, content: String, name: String, symbol: String) {
        SensorsDataHelper.SensorsDataBuilder(SensorsDataConstant.Event.EVENT_NAME_CLICK)
            .withTitle(title)
            .withEventName(content)
            .withParam("name", name)
            .withParam("symbol", symbol)
            .track()
    }


    /**
     * 点击事件
     * @param title   标题
     * @param paramsMap 带有参数的map
     */
    fun sensorsDataParamsWithHashMap(title: String, content: String, paramsMap: HashMap<String, String>) {
        var build = SensorsDataHelper.SensorsDataBuilder(SensorsDataConstant.Event.EVENT_NAME_CLICK).withTitle(title)
            .withEventName(content)
        for ((key, value) in paramsMap) {
            build.withParam(key, value)
        }
        build.track()
    }

    /**
     * 页面浏览事件自定义参数
     *
     * @param title   标题
     * @param content 内容
     * @param key     params key
     * @param value   params value
     */
    fun sensorsDataViewWithTitle(title: String) {
        SensorsDataHelper.SensorsDataBuilder(SensorsDataConstant.Event.EVENT_NAME_VIEW_SCREEN)
            .withTitle(title)
            .track()
    }

}
