package ir.marko.whatsappui


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ir.marko.whatsappui.databinding.FoodItemBinding
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class FoodAdapter(
    private val foodList: ArrayList<Food>,
    private val sendDeleteFood: SendDeleteAndUpdateFood
) : RecyclerView.Adapter<FoodAdapter.FoodHolder>() {
    inner class FoodHolder(private val binding: FoodItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(position: Int) {
            binding.txtFoodName.text = foodList[position].foodName
            binding.txtFoodPlace.text = foodList[position].foodPlace
            binding.txtRatingNum.text = "${foodList[position].foodRateNums} ratings"
            binding.txtFoodPrice.text = " ${foodList[position].foodPrice} T"
            binding.ratingBar.rating = foodList[position].foodRate
            Glide.with(binding.root.context)
                .load(foodList[position].imgUrl)
                .transform(RoundedCornersTransformation(8, 4))
                .into(binding.imgFood)
            itemView.setOnLongClickListener {
                sendDeleteFood.deleteFood(foodList[position], adapterPosition)
                true
            }
            itemView.setOnClickListener {
               sendDeleteFood.updateFood(foodList[position] , adapterPosition , foodList[position].imgUrl)
             }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder {
        val binding = FoodItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodHolder, position: Int) {
        holder.bindData(position)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    fun addFood(newFood: Food) {
        foodList.add(0, newFood)
        notifyItemInserted(0)

    }

    fun deleteFood(oldFood: Food, position: Int) {
        foodList.remove(oldFood)
        notifyItemRemoved(position)
    }

    interface SendDeleteAndUpdateFood {
        fun deleteFood(oldFood: Food, position: Int)
        fun updateFood(newFood: Food, position: Int, imageUrl: String)
    }

    fun updateFood(newFood: Food, position: Int) {
        foodList[position] = newFood
        notifyItemChanged(position)
    }

    fun setData(newFoodList: ArrayList<Food>) {
        foodList.clear()
        foodList.addAll(newFoodList)
        notifyDataSetChanged()
    }
}