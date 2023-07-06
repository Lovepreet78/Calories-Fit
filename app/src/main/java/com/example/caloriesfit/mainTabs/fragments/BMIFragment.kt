package com.example.caloriesfit.mainTabs.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.example.caloriesfit.R
import com.google.android.material.button.MaterialButton


class BMIFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_b_m_i, container, false)


        val bmiDisplayer = view.findViewById<TextView>(R.id.BmiDisplayer)
        val spinnerWeight = view.findViewById<Spinner>(R.id.spinnerWeightBMI)
        val spinnerHeight = view.findViewById<Spinner>(R.id.spinnerHeightBMI)

        val editTextWeight = view.findViewById<EditText>(R.id.edittextWeightBMI)
        val editTextHeight = view.findViewById<EditText>(R.id.edittextHeightBMI)

        val bmiDisplayerCardView = view.findViewById<CardView>(R.id.BmiDisplayerCardView)
        val bmiStageDisplayer = view.findViewById<TextView>(R.id.BmiStageDisplayer)

        val calculateBMIButton = view.findViewById<MaterialButton>(R.id.calaculateBMIButton)

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
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerWeight.adapter = adapter1
        spinnerHeight.adapter = adapter2


        calculateBMIButton.setOnClickListener {


            if (editTextWeight.text.toString() == "" || editTextHeight.text.toString() == ""
            ) {
                Toast.makeText(context, "Please Enter all required parameters", Toast.LENGTH_SHORT)
                    .show()
            } else if (spinnerWeight.selectedItem.toString() == "Not selected" || spinnerHeight.selectedItem.toString() == "Not selected"
            ) {
                Toast.makeText(context, "Please Select all required parameters", Toast.LENGTH_SHORT)
                    .show()
            } else if (editTextWeight.text.toString()
                    .toDouble() < 1.0 || editTextHeight.text.toString().toDouble() < 1.0
            ) {
                Toast.makeText(context, "Enter valid Values", Toast.LENGTH_SHORT).show()
            } else {
                var weight = editTextWeight.text.toString().toDouble()
                var height = editTextHeight.text.toString().toDouble()
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
                val bmiValue: Double = (703) * (weight / (height * height))
                bmiDisplayer.text = bmiValue.toInt().toString()
                bmiDisplayer.visibility = View.VISIBLE

                if (bmiValue < 18.5) {
                    bmiStageDisplayer.text =
                        getString(R.string.low_bmi)
                    bmiDisplayerCardView.visibility = View.VISIBLE
                    bmiDisplayerCardView.setCardBackgroundColor(Color.rgb(255, 244, 155))


                } else if (bmiValue >= 18.5 && bmiValue < 25) {
                    bmiStageDisplayer.text =
                        getString(R.string.good_bmi)
                    bmiDisplayerCardView.visibility = View.VISIBLE
                    bmiDisplayerCardView.setCardBackgroundColor(Color.rgb(152, 255, 152))
                } else if (bmiValue >= 25 && bmiValue < 40) {
                    bmiStageDisplayer.text = getString(R.string.high_bmi)
                    bmiDisplayerCardView.visibility = View.VISIBLE
                    bmiDisplayerCardView.setCardBackgroundColor(Color.rgb(255, 105, 97))
                } else if (bmiValue >= 40) {
                    bmiStageDisplayer.text =
                        getString(R.string.very_high_bmi)
                    bmiDisplayerCardView.visibility = View.VISIBLE
                    bmiDisplayerCardView.setCardBackgroundColor(Color.rgb(255, 20, 20))
                }


            }
        }





        return view
    }


}