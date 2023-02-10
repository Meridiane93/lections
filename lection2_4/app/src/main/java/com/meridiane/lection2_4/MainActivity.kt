package com.meridiane.lection2_4

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var textSave = "" // для записи цикла в строку
    private var prefActivity: SharedPreferences? = null
    private lateinit var textActivity: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefActivity = getSharedPreferences("TABLE2", Context.MODE_PRIVATE)
        textActivity = findViewById(R.id.textActivity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.contaner, FragmentLife.newInstance())
                .commit()
        }

        textSave += "onCreate_activity,"

        val textPref = prefActivity?.getString("KEY", "")!! // для сохранения данных из Preferences

        if (textPref != "") {
            textSave = (textPref + textSave).replace(",", "\n")
            textActivity.text = textSave
        } else {
            val textFull = textSave.replace(",", "\n")
            textActivity.text = textFull
        }
    }

    override fun onStart() {
        super.onStart()

        Log.d(FragmentLife.TAG, "onStart_activity")
        textSave += "onStart_activity,"
    }

    override fun onResume() {
        super.onResume()

        Log.d(FragmentLife.TAG, "onResume_activity")
        textSave += "onResume_activity,"
    }

    override fun onPause() {
        super.onPause()

        Log.d(FragmentLife.TAG, "onPause_activity")
        textSave += "onPause_activity,"
    }

    override fun onStop() {
        super.onStop()

        Log.d(FragmentLife.TAG, "onStop_activity")
        textSave += "onStop_activity,"
    }

    override fun onRestart() {
        super.onRestart()

        Log.d(FragmentLife.TAG, "onRestart_activity")
        textSave += "onRestart_activity,"
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d(FragmentLife.TAG, "onDestroy_activity")
        textSave += "onDestroy_activity,"
        saveData()
    }

    private fun saveData() {
        val editor = prefActivity?.edit()
        editor?.putString("KEY", textSave)
        editor?.apply()
    }
}