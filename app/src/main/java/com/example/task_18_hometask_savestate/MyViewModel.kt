package com.example.task_18_hometask_savestate

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

class MyViewModel:ViewModel() {
    //private val model:Model = ModelImpl
    private val model:Model = ModelImpl_SharedPrefs
    val itemsLiveData = MutableLiveData(model.getItem())
    // MVI
    val uiStateLiveData = MutableLiveData<UIState>(UIState.EmptyList)
    val intentLiveData = MutableLiveData<Intent>()
    private val observer = Observer<Intent> {
        addItem()
    }

    init {
        intentLiveData.observeForever(observer)
        if(model.getItem() != null) {
            uiStateLiveData.value = UIState.FilledList(model.getItem())
        }
    }

    fun addItem() {
        model.addItem()
        itemsLiveData.value = model.getItem()
        // MVI
        uiStateLiveData.value = UIState.FilledList(model.getItem())
    }

    // del memory app
    override fun onCleared() {
        super.onCleared()
        intentLiveData.removeObserver(observer)
        MyApplication.getApp().savedData(model.getItem())
    }
}
//
//// MVI
sealed class UIState {
    object EmptyList : UIState()
    class FilledList(val count:Int): UIState()
}
