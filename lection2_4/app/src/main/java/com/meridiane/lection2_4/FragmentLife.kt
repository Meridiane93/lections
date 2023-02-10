package com.meridiane.lection2_4

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class FragmentLife : Fragment() {

    private var textSave = ""
    private var prefFragment: SharedPreferences? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        Log.d(TAG, "onAttach_fragment")
        textSave += "onAttach_fragment,"

        prefFragment = context.getSharedPreferences("TABLE", Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate_fragment")
        textSave += "onCreate_fragment,"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d(TAG, "onCreateView_fragment")
        textSave += "onCreateView_fragment,"

        return inflater.inflate(R.layout.fragment_life, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "onViewCreated_fragment")
        textSave += "onViewCreated_fragment,"
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        Log.d(TAG, "onViewStateRestored_fragment")
        textSave += "onViewStateRestored_fragment,"

        val textView: TextView = requireActivity().findViewById(R.id.textView)

        val textPref = prefFragment?.getString("KEY", "")!!


        if (textPref != "") {
            textSave = (textPref + textSave).replace(",", "\n")
            textView.text = textSave
        } else {
            val textFull = textSave.replace(",", "\n")
            textView.text = textFull
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart_fragment")
        textSave += "onStart_fragment,"
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume_fragment")
        textSave += "onResume_fragment,"
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause_fragment")
        textSave += "onPause_fragment,"
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop_fragment")
        textSave += "onStop_fragment,"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView_fragment")
        textSave += "onDestroyView_fragment,"
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy_fragment")
        textSave += "onDestroy_fragment,"
    }

    override fun onDetach() {
        super.onDetach()

        Log.d(TAG, "onDetach_fragment")
        textSave += "onDetach_fragment,"

        saveData()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState_fragment,")
        textSave += "onSaveInstanceState_fragment,"
    }

    private fun saveData() {
        val editor = prefFragment?.edit()
        editor?.putString("KEY", textSave)
        editor?.apply()
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentLife()

        @JvmStatic
        val TAG: String = "MyLog"
    }

    // Bundle не поможет отобразить полный жизненный цикл,
    // вместо retainInstance можно было бы добавить ViewModel и хранить список там
    // и ещё вариант с SharedPreferences ( с которым и делал )
}