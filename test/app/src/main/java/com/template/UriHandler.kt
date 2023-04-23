package com.template

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log

class UriHandler : BroadcastReceiver() {
    companion object {
        const val KEY_ACTION_SOURCE = "org.chromium.customtabsdemos.ACTION_SOURCE"
    }


    override fun onReceive(context: Context?, intent: Intent?) {
        val uri: Uri? = intent?.data!!
        Log.d("Broadcast URL", uri.toString())
    }
}