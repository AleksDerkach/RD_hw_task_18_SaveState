package com.example.task_18_hometask_savestate
//
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MyApplication: Application() {
    //Є системний Application.
    // Якщо ми створюємо свій клас від Application
    // то в маніфесті потрібно це вказати за допомогою
    // парметру name
//    val items = mutableListOf<String>()

    private lateinit var sharedPrefs: SharedPreferences
    private val gson = Gson()
    override fun onCreate() {
        super.onCreate()
        instance = this
        sharedPrefs = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
    }
//        fun addItem(){
//        items.add("Item ${items.size+1}")
//    }
//}
//
    fun savedData(count: Int) {
        val countToSave = gson.toJson(count)
        sharedPrefs.edit().putString("MyCount", countToSave).apply()
    }

    fun getSavedData():Int {
        val jsonCount = sharedPrefs.getString("MyCount", "")
        var result= 0
        if (jsonCount!!.isNotEmpty()) {
            val type = object : TypeToken<Int>(){}.type
            result = gson.fromJson(jsonCount, type)
        }
        return result
    }
    companion object {
        private lateinit var instance:MyApplication
        fun getApp() = instance
    }
}