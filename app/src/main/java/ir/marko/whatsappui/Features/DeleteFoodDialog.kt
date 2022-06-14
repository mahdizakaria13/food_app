package ir.marko.whatsappui.Features

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import ir.marko.whatsappui.Food
import ir.marko.whatsappui.FoodAdapter
import ir.marko.whatsappui.databinding.DeleteFoodDialogBinding

class DeleteFoodDialog(val myAdapter: FoodAdapter, val  oldFood: Food, val position:Int, val con :Context) :DialogFragment()  {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = androidx.appcompat.app.AlertDialog.Builder(con)
        val dialogView = DeleteFoodDialogBinding.inflate(layoutInflater)
        dialog.setView(dialogView.root)
        dialogView.btnDeleteFoodCancle.setOnClickListener {
            dismiss()
        }
        dialogView.btnDeleteFood.setOnClickListener {
            dismiss()
            myAdapter.deleteFood(oldFood , position)
        }

        return dialog.create()
    }



}