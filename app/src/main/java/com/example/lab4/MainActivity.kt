package com.example.lab4

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val button1: Button = findViewById(R.id.button)
        val button2: Button = findViewById(R.id.button2)

        var True: Int = 0
        var False: Int = 0

        val Quiz = Array(5) {"a"; "b"; "c"; "d"; "e"}

        for (i in 0..4) {

            button1.setOnClickListener {
                button1.visibility = Button.GONE
                button2.visibility = Button.GONE
            }

            button2.setOnClickListener {
                button1.visibility = Button.GONE
                button2.visibility = Button.GONE
            }
        }
    }
}