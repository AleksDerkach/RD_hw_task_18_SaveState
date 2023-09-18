package com.example.task_18_hometask_savestate

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private var tvCount: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)

        // При перестворені актівіті дані (items) створюються заново
        // Переворот в альбом - даних немає
        // Дані треба хранити окремо
        // 1. Singleton
        // 2. Application
        // 3. Bundle
        // 4. HeadLessFragment
        // 5. MVP - проблема в memopry managment.Класно розділяє логіку
        // 6. MVVM - LivaData
        tvCount = findViewById(R.id.count)
        val fab:FloatingActionButton = findViewById(R.id.fab)

        val viewModel:MyViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        viewModel.itemsLiveData.observe(this) {
            tvCount?.text = it.toString()
        }
        fab.setOnClickListener {
            viewModel.addItem()
        }
    }
}
