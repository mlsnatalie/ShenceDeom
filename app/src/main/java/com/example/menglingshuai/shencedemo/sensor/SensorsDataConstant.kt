package com.spero.vision.sensor


/**
 * introduce：这里写介绍
 * createBy：lingshuai.meng
 * email：mlsnatalie@163.com
 * time: 23/11/18
 */
object SensorsDataConstant {

    object Event {
        const val EVENT_NAME_CLICK = "NativeAppClick"
        const val EVENT_NAME_VIEW_SCREEN = "AppPageView"
    }

    object ScreenTitle {
        const val TEST_TITLE = "测试"
        const val page_search = "搜索页"
        const val PAGE_MAIN_HOME = "首页"
    }

    object EventName {
        const val TEST_CONTENT = "测试的内容"
        const val JUMP_TO_VIDEO_DETAIL = "点击进入视频详情页"
        const val NAME_SEARCH = "搜索"
        // 视频
        const val START_PLAY = "开始播放"
        const val PAUSE_PLAY = "暂停播放"

    }

    object ElementParamKey {
        const val NAME = "name"
        const val SearchMethod = "searchmethod"
        const val searchTerm = "searchterm"
        const val videoId = "videoid"

    }

    object ElementParamValue {
        const val TYPE_VIDEO = "视频"
        const val TYPE_COMMENT = "评论"
        const val YES = "是"
        const val NO = "否"

        const val channelidValue = "app"
    }
}