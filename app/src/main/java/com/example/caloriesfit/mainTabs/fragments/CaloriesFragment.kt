package com.example.caloriesfit.mainTabs.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup

import android.widget.Spinner

import android.widget.Toast

import com.example.caloriesfit.R
import com.example.caloriesfit.mainTabs.results.ResultCalories

import com.google.android.material.button.MaterialButton


class CaloriesFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_calories, container, false)


        val spinnerWeight = view.findViewById<Spinner>(R.id.spinnerWeight)

        val spinnerHeight = view.findViewById<Spinner>(R.id.spinnerHeight)

        val spinnerExerciseIntensity = view.findViewById<Spinner>(R.id.spinnerExerciseIntensity)


        val radioGroup = view.findViewById<RadioGroup>(R.id.radioGroup)
        val radioButtonMale = view.findViewById<RadioButton>(R.id.radioButtonMale)
        val radioButtonFemale = view.findViewById<RadioButton>(R.id.radioButtonFemale)

        val editTextWeight = view.findViewById<EditText>(R.id.edittextWeight)
        val editTextHeight = view.findViewById<EditText>(R.id.edittextHeight)
        val editTextAge = view.findViewById<EditText>(R.id.edittextAge)


        val calculateCaloriesButton =
            view.findViewById<MaterialButton>(R.id.calaculateCaloriesButton)


        val adapter1 = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinner_items_weight,
            android.R.layout.simple_spinner_item
        )
        val adapter2 = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinner_items_height,
            android.R.layout.simple_spinner_item
        )
        val adapter3 = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinner_items_Exercise_intensity,
            android.R.layout.simple_spinner_item
        )
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerWeight.adapter = adapter1
        spinnerHeight.adapter = adapter2
        spinnerExerciseIntensity.adapter = adapter3

        calculateCaloriesButton.setOnClickListener {


            if (!radioButtonMale.isChecked && !radioButtonFemale.isChecked) {
                Toast.makeText(context, "Choose Gender", Toast.LENGTH_SHORT).show()
            }
            else if (editTextWeight.text.toString() == "" || editTextHeight.text.toString() == "" || editTextAge.text.toString() == ""
            ) {
                Toast.makeText(context, "Please Enter all required parameters", Toast.LENGTH_SHORT)
                    .show()
            } else if (spinnerWeight.selectedItem.toString() == "Not selected" || spinnerHeight.selectedItem.toString() == "Not selected"
                || spinnerExerciseIntensity.selectedItem.toString() == "Not selected"
            ) {
                Toast.makeText(context, "Please Select all required parameters", Toast.LENGTH_SHORT)
                    .show()
            } else if (editTextWeight.text.toString()
                    .toDouble() < 1.0 || editTextHeight.text.toString()
                    .toDouble() < 1.0 || editTextAge.text.toString().toDouble() < 1.0
            ) {
                Toast.makeText(context, "Enter valid Values", Toast.LENGTH_SHORT).show()
            }
            else {
                var weight = editTextWeight.text.toString().toDouble()
                var height = editTextHeight.text.toString().toDouble()
                val age = editTextAge.text.toString().toDouble()
                var exerciseIntensity = 1.0f

                when (radioGroup.checkedRadioButtonId) {
                    radioButtonMale.id -> {


                        when (spinnerWeight.selectedItem.toString()) {
                            "kgs" -> {
                                weight *= 2.205
                            }

                        }
                        when (spinnerHeight.selectedItem.toString()) {

                            "meters" -> {
                                height /= 0.0254
                            }
                        }
                        when (spinnerExerciseIntensity.selectedItem.toString()) {
                            "No Activity" -> {
                                exerciseIntensity = 1.2f
                            }
                            "Light exercise 1-3 days" -> {
                                exerciseIntensity = 1.37f
                            }
                            "Moderate exercise 3-5 days" -> {
                                exerciseIntensity = 1.55f
                            }
                            "Moderate exercise 5-7 days" -> {
                                exerciseIntensity = 1.725f
                            }
                            "Daily High Intensity Exercise" -> {
                                exerciseIntensity = 1.9f
                            }

                        }

                        val calories: Double =
                            (66 + (6.23 * weight) + (12.7 * height) - (6.76 * age)) * exerciseIntensity
                        val bmi = (703) * (weight / (height * height))
                        var bmiCondition = ""
                        if (bmi < 18.5) {
                            bmiCondition = "low"
                        } else if (bmi >= 18.5 && bmi < 25) {
                            bmiCondition = "good"
                        } else if (bmi >= 25 && bmi < 40) {
                            bmiCondition = "high"
                        } else if (bmi >= 40) {
                            bmiCondition = "obese"
                        }
                        Toast.makeText(requireContext(), "$calories", Toast.LENGTH_LONG).show()
                        val intentToResult = Intent(context, ResultCalories::class.java)
                        intentToResult.putExtra("Calories", calories)

                        intentToResult.putExtra("bmi", bmiCondition)
                        startActivity(intentToResult)

                    }

                    radioButtonFemale.id -> {

                        when (spinnerWeight.selectedItem.toString()) {
                            "kgs" -> {
                                weight *= 2.205
                            }
                        }
                        when (spinnerHeight.selectedItem.toString()) {

                            "meters" -> {
                                height /= 0.0254
                            }
                        }
                        when (spinnerExerciseIntensity.selectedItem.toString()) {
                            "No Activity" -> {
                                exerciseIntensity = 1.2f
                            }
                            "Light exercise 1-3 days" -> {
                                exerciseIntensity = 1.37f
                            }
                            "Moderate exercise 3-5 days" -> {
                                exerciseIntensity = 1.55f
                            }
                            "Moderate exercise 5-7 days" -> {
                                exerciseIntensity = 1.725f
                            }
                            "Daily High Intensity Exercise" -> {
                                exerciseIntensity = 1.9f
                            }

                        }
                        val calories = (655.1 + (4.35 * weight) + (4.7 * height) - (4.7 * age)) * exerciseIntensity
                        val bmi = (703) * (weight / (height * height))
                        var bmiConidtion = ""
                        if (bmi < 18.5) {
                            bmiConidtion = "low"
                        } else if (bmi >= 18.5 && bmi < 25) {
                            bmiConidtion = "good"
                        } else if (bmi >= 25 && bmi < 40) {
                            bmiConidtion = "high"
                        } else if (bmi >= 40) {
                            bmiConidtion = "obese"
                        }
                        val intentToResult = Intent(context, ResultCalories::class.java)
                        intentToResult.putExtra("Calories", calories)

                        intentToResult.putExtra("bmi", bmiConidtion)
                        startActivity(intentToResult)


                    }
                }
            }


        }






        return view
    }


}