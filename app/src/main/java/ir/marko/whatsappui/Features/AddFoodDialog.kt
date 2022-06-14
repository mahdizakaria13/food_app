package ir.marko.whatsappui.Features

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import ir.marko.whatsappui.Food
import ir.marko.whatsappui.databinding.AddFoodDialogBinding

class AddFoodDialog(val sendData: SendData, val data: ArrayList<Food>) : DialogFragment() {
    lateinit var binding: AddFoodDialogBinding
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = AddFoodDialogBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(context)
        dialog.setView(binding.root)
        binding.btnCancleAddFood.setOnClickListener {
            dismiss()
        }
        binding.btnDoneAddFood.setOnClickListener {
            val foodName = binding.edtAddFoodName.text.toString()
            val foodPrice = binding.edtAddFoodPrice.text.toString()
            val foodLocation = binding.edtAddFoodLocation.text.toString()
            val foodRate = binding.edtAddFoodRate.text.toString().toFloat()
            val foodRatings = (1..150).random()
            if (foodName.isNotEmpty() && foodLocation.isNotEmpty() && foodPrice.isNotEmpty() && foodRate > 0 && foodRatings > 0) {
                sendData.sendData(foodName, foodPrice, foodLocation, foodRate, foodRatings)
                dismiss()
            } else {
                Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
        return dialog.create()
    }

    interface SendData {
        fun sendData(
            foodName: String,
            foodPrice: String,
            foodPlace: String,
            foodRate: Float,
            foodRatingNum: Int
        )
    }
}

