package com.example.lab4

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var currentQuestionIndex = 0
    private var trueCount = 0
    private var falseCount = 0


    private val quizQuestions = arrayOf(
        "1: Вода сухая?",
        "2: 2 + 2 = 4?",
        "3: Бумага делается из дерева?",
        "4: Небо зеленого цвета?",
        "5: Азот это металл?"
    )

    private val correctAnswers = arrayOf(0, 1, 1, 0, 0) // Правильные индексы

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
        val button3: Button = findViewById(R.id.button3)
        val text: TextView = findViewById(R.id.textView)

        // Вывод вопроса
        text.text = quizQuestions[currentQuestionIndex]

        button3.setOnClickListener {
            if (currentQuestionIndex < quizQuestions.size - 1) {
                currentQuestionIndex++
                text.text = quizQuestions[currentQuestionIndex]
            } else {
                text.text = "Quiz Finished! True: $trueCount, False: $falseCount"
                button1.isEnabled = false
                button2.isEnabled = false
                button3.isEnabled = false
            }
        }

        button1.setOnClickListener {
            if (currentQuestionIndex < quizQuestions.size) {
                if (correctAnswers[currentQuestionIndex] == 0) trueCount++
                else falseCount++
                checkNextQuestion()
            }
        }

        button2.setOnClickListener {
            if (currentQuestionIndex < quizQuestions.size) {
                if (correctAnswers[currentQuestionIndex] == 1) trueCount++
                else falseCount++
                checkNextQuestion()
            }
        }
    }

    private fun checkNextQuestion() {
        if (currentQuestionIndex < quizQuestions.size - 1) {
            currentQuestionIndex++
            findViewById<TextView>(R.id.textView).text = quizQuestions[currentQuestionIndex]
        } else {
            findViewById<TextView>(R.id.textView).text = "Опрос окончен! Правельных ответов: $trueCount, Ложных ответов: $falseCount"
            findViewById<Button>(R.id.button).isEnabled = false
            findViewById<Button>(R.id.button2).isEnabled = false
            findViewById<Button>(R.id.button3).isEnabled = false
        }
    }
}