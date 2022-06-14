package ir.marko.whatsappui.Features

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import ir.marko.whatsappui.Food
import ir.marko.whatsappui.FoodAdapter
import ir.marko.whatsappui.databinding.UpdateFoodDialogBinding

class UpdateFoodDialog(
    private val adapter: FoodAdapter,
    private val con: Context,
    private val newFood: Food,
    private val position: Int,
    private val imageUrl :String
) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = AlertDialog.Builder(con)
        val dialogView = UpdateFoodDialogBinding.inflate(layoutInflater)
        dialog.setView(dialogView.root)
        dialogView.edtUpdateFoodName.setText(newFood.foodName)
        dialogView.edtUpdateFoodPrice.setText(newFood.foodPrice)
        dialogView.edtUpdateFoodLocation.setText(newFood.foodPlace)
        dialogView.edtUpdateFoodRate.setText(newFood.foodRate.toString())
        dialogView.btnCancleUpdateFood.setOnClickListener {
            dismiss()
        }
        dialogView.btnUpdateFood.setOnClickListener {
            dismiss()
            val foodName = dialogView.edtUpdateFoodName.text.toString()
            val foodPrice = dialogView.edtUpdateFoodPrice.text.toString()
            val foodRate = dialogView.edtUpdateFoodRate.text.toString().toFloat()
            val foodPlace = dialogView.edtUpdateFoodLocation.text.toString()
            val food = Food(
                foodName,
                foodPrice,
                foodPlace,
                foodRate,
                (0..150).random(),
                imageUrl
            )
            adapter.updateFood(food, position)
        }
        return dialog.create()
    }

}