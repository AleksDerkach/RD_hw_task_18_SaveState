package com.example.task_18_hometask_savestate

object ModelImpl_SharedPrefs: Model {
    private var count:Int = 0

    init {
        val savedItems = MyApplication.getApp().getSavedData()
        if(savedItems != null) {
            count = savedItems
        }
    }

    override fun getItem(): Int = count

    override fun addItem() {
        count = count + 1
    }

}
