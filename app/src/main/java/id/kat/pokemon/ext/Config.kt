package com.kat.news.utils

import android.support.annotation.StringDef

/**
 *
 * Created by Wanhar Aderta Daeng Maro on 5/18/2018.
 * Email : wanhardaengmaro@gmail.com
 *
 */

@Retention(AnnotationRetention.SOURCE)
@StringDef(
        Config.BASE_URL,
        Config.API_KEY
)
annotation class Configs

object Config {
    const val BASE_URL = "https://newsapi.org/"
    const val API_KEY = "5dab24aff4f14b57b567608a51f8c256"
}