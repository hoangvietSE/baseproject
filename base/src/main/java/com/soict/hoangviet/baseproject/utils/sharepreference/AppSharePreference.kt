package com.soict.hoangviet.baseproject.utils.sharepreference

import android.content.Context
import android.content.SharedPreferences
import com.soict.hoangviet.baseproject.utils.AppConstant

class AppSharePreference private constructor(val context: Context) : SharePreference {
    companion object {
        private lateinit var instances: AppSharePreference
        fun getInstance(context: Context): AppSharePreference {
            if (instances == null) {
                instances = AppSharePreference(context)
            }
            return instances
        }
    }

    private val mPrefs: SharedPreferences = context.getSharedPreferences(AppConstant.PREF_NAME, Context.MODE_PRIVATE)

    override fun setString(key: String, value: String) {
        mPrefs.edit().putString(key, value).apply()
    }

    override fun getString(key: String): String {
        return mPrefs.getString(key, "")
    }

    override fun setBoolean(key: String, value: Boolean) {
        mPrefs.edit().putBoolean(key, value).apply()
    }

    override fun getBoolean(key: String): Boolean {
        return mPrefs.getBoolean(key, false)
    }

    override fun setArrayListString(arrayName: String, list: ArrayList<String>) {
        val editor = mPrefs.edit()
        editor.putInt(arrayName + "_size", list.size)
        list.forEachIndexed { index, item ->
            editor.putString(arrayName + "_${index}", item)
        }
        editor.apply()
    }

    override fun getArrayListString(arrayName: String): ArrayList<String> {
        val list = arrayListOf<String>()
        for (index in 0..mPrefs.getInt(arrayName + "_size", -1)) {
            list.add(mPrefs.getString(arrayName + "_${index}", ""))
        }
        return list
    }


}