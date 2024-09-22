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

    // 2D Подсказки
    private val hints = arrayOf(
        arrayOf("Вода - это жидкость.", "Она необходима для жизни."),
        arrayOf("Это простое арифметическое выражение.", "Попробуйте посчитать на пальцах."),
        arrayOf("Бумага действительно производится из древесины.", "Дерево - это основной материал для производства бумаги."),
        arrayOf("Небо обычно синего цвета.", "Это связано с атмосферным рассеиванием света."),
        arrayOf("Азот - это газ, а не металл.", "Он составляет большую часть атмосферы.")
    )

    // Для отслеживания индекса подсказок
    private val currentHintIndex = IntArray(quizQuestions.size) { 0 }

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
        val button4: Button = findViewById(R.id.button4)
        val text: TextView = findViewById(R.id.textView)
        val hintText: TextView = findViewById(R.id.textView2)

        // Вывод вопроса
        text.text = quizQuestions[currentQuestionIndex]

        button3.setOnClickListener {
            if (currentQuestionIndex < quizQuestions.size - 1) {
                currentQuestionIndex++
                text.text = quizQuestions[currentQuestionIndex]
                hintText.text = "" // Очищение для нового запроса
                currentHintIndex[currentQuestionIndex] = 0 // Обнуление индекса
            } else {
                text.text = "Опрос окончен! Правильных ответов: $trueCount, Ложных ответов: $falseCount"
                button1.isEnabled = false
                button2.isEnabled = false
                button3.isEnabled = false
                button4.isEnabled = false
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


        button4.setOnClickListener {
            if (currentHintIndex[currentQuestionIndex] < hints[currentQuestionIndex].size) {
                hintText.text = hints[currentQuestionIndex][currentHintIndex[currentQuestionIndex]]
                currentHintIndex[currentQuestionIndex]++ // Move to the next hint index
            } else {
                hintText.text = "Нет больше подсказок."
            }
        }
    }

    private fun checkNextQuestion() {
        if (currentQuestionIndex < quizQuestions.size - 1) {
            currentQuestionIndex++
            findViewById<TextView>(R.id.textView).text = quizQuestions[currentQuestionIndex]
            findViewById<TextView>(R.id.textView2).text = "" // Clear hint when moving to next question
            currentHintIndex[currentQuestionIndex] = 0 // Reset hint index for the new question
        } else {
            findViewById<TextView>(R.id.textView).text = "Опрос окончен! Правильных ответов: $trueCount, Ложных ответов: $falseCount"
            findViewById<Button>(R.id.button).isEnabled = false
            findViewById<Button>(R.id.button2).isEnabled = false
            findViewById<Button>(R.id.button3).isEnabled = false
            findViewById<Button>(R.id.button4).isEnabled = false // Disable hint button at the end of the quiz
        }
    }
}