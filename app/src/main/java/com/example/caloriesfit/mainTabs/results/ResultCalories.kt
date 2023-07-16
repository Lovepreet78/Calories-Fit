package com.example.caloriesfit.mainTabs.results


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.caloriesfit.R
import com.google.android.material.button.MaterialButton
import kotlin.properties.Delegates

class ResultCalories : AppCompatActivity() {
    lateinit var  caloriesDisplayer:TextView
    lateinit var  myAdviceDisplayer1: TextView
    lateinit var onButtonClickDisplayer: TextView

    lateinit var  onButtonClickDisplayerCardView: CardView

    lateinit var weightGainButton: MaterialButton

    lateinit var weightLossButton: MaterialButton

    var calories:Int =0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_calories)

        caloriesDisplayer = findViewById(R.id.caloriesDisplayer)
        myAdviceDisplayer1 = findViewById(R.id.myAdviceDisplayer1)
        onButtonClickDisplayer = findViewById(R.id.cardviewTextViewResult)
        onButtonClickDisplayerCardView = findViewById(R.id.onButtonClickDisplayerCardView)
        weightGainButton = findViewById(R.id.weightGainButton)
        weightLossButton = findViewById(R.id.weightLossButton)

        calories = intent.getDoubleExtra("Calories", -1.0).toInt()
        val bmi = intent.getStringExtra("bmi")
        var advice = ""
        caloriesDisplayer.text = calories.toString()

        when (bmi) {
            "low" -> {
                advice =
                    "According to our Calculation you should Gain weight choose below to know more "
                weightGainButton.visibility = View.VISIBLE


            }
            "high" -> {
                advice =
                    "According to our Calculation you should Loose weight choose below to know more "
                weightLossButton.visibility = View.VISIBLE
            }
            "good" -> {
                advice =
                    "According to our You seems fit, you can choose one from below as you wish... "
                weightGainButton.visibility = View.VISIBLE
                weightLossButton.visibility = View.VISIBLE
            }
            "obese" -> {
                advice = "You are too fat , you must start exercise or gym or you will suffer."
            }
        }
        myAdviceDisplayer1.text = advice

        weightGainButton.setOnClickListener {
            weightGainResult()

        }
        weightLossButton.setOnClickListener {
            weightLossResult()

        }


    }

    private fun weightLossResult() {
        onButtonClickDisplayerCardView.visibility = View.GONE
        onButtonClickDisplayer.text = buildString {
            append(buildString {
                append("Your Calories intake should be -> ")
                append(calories - 700)
                append("\n ")
            })
            append("This will help you loss weight approximately 0.5 - 0.7 Kg every week \n")
            append("Instead of that you can burn 500-800 calories a day , if you don't want to reduce your calories intake.")
        }
        onButtonClickDisplayerCardView.visibility = View.VISIBLE
    }

    private fun weightGainResult() {
        onButtonClickDisplayerCardView.visibility = View.GONE
        onButtonClickDisplayer.text =
            buildString {
                append("Your Calories intake should be -> ")
                append(calories + 800)
                append("\n This will help you gain weight approximately 0.5 - 1.0 Kg every week ")
            }
        onButtonClickDisplayerCardView.visibility = View.VISIBLE
    }
}