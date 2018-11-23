package com.example.menglingshuai.shencedemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.menglingshuai.shencedemo.sensor.SensorsDataHelper
import com.example.menglingshuai.shencedemo.sensor.SensorsDataHelperUtils
import com.spero.vision.sensor.SensorsDataConstant
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //
        SensorsDataHelperUtils.sensorsDataViewWithTitle("主activity页面")

        button.setOnClickListener {
            val hashMap: HashMap<String, String> = hashMapOf()
            hashMap[SensorsDataConstant.ElementParamKey.videoId] = SensorsDataConstant.ElementParamValue.TYPE_VIDEO
            sensorData(SensorsDataConstant.EventName.NAME_SEARCH,hashMap)

            //另一种点击事件打点方式
            sensorData1("s", "type")

        }

    }

    private fun sensorData(content:String,hashMap: HashMap<String, String> = hashMapOf()) {
        SensorsDataHelperUtils.sensorsDataParamsWithHashMap(SensorsDataConstant.ScreenTitle.PAGE_MAIN_HOME,content,hashMap)
    }

    //另一种点击事件打点方式
    private fun sensorData1(string: String, type: String) {
        SensorsDataHelper.SensorsDataBuilder(SensorsDataConstant.Event.EVENT_NAME_CLICK)
            .withEventName(SensorsDataConstant.EventName.NAME_SEARCH)
            .withTitle(SensorsDataConstant.ScreenTitle.page_search)
            .withParam(SensorsDataConstant.ElementParamKey.searchTerm,string)
            .withParam(SensorsDataConstant.ElementParamKey.SearchMethod, type)
            .track()
    }
}
