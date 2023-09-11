package com.geeks.youtube_6.utils

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import java.io.Serializable

fun <T : Serializable> Activity.sendData(activity: Activity, key: String?, model: T?) {
    val intent = Intent(this, activity::class.java)
    val bundle = Bundle()
    bundle.putSerializable(key, model)
    intent.putExtras(bundle)
    startActivity(intent)
}