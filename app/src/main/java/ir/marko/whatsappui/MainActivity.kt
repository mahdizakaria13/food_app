package ir.marko.whatsappui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ir.marko.whatsappui.Features.AddFoodDialog
import ir.marko.whatsappui.Features.DeleteFoodDialog
import ir.marko.whatsappui.Features.UpdateFoodDialog
import ir.marko.whatsappui.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), AddFoodDialog.SendData , FoodAdapter.SendDeleteAndUpdateFood {
    lateinit var binding: ActivityMainBinding
    lateinit var myAdapter :FoodAdapter
    private val data = arrayListOf(
        Food(
            "Hamburger",
            "15",
            "Isfahan, Iran",
            4.5f,
            15,
            "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food1.jpg"
        ),
        Food(
            "Grilled fish",
            "20",
            "Tehran, Iran",
            4f,
            50,
            "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food2.jpg",
        ),
        Food(
            "Lasania",
            "40",
            "Isfahan, Iran",
            2f,
            30,
            "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food3.jpg",


            ),
        Food(
            "pizza",
            "10",
            "Zahedan, Iran",
            1.5f,
            80,
            "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food4.jpg",


            ),
        Food(
            "Sushi",
            "20",
            "Mashhad, Iran",
            3f,
            200,
            "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food5.jpg",


            ),
        Food(
            "Roasted Fish",
            "40",
            "Jolfa, Iran",
            3.5f,
            50,
            "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food6.jpg",


            ),
        Food(
            "Fried chicken",
            "70",
            "NewYork, USA",
            2.5f,
            70,
            "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food7.jpg",
        ),
        Food(
            "Vegetable salad",
            "12",
            "Berlin, Germany",
            3.5f,
            40,
            "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food8.jpg",

            ),
        Food(
            "Grilled chicken",
            "10",
            "Beijing, China",
            5f,
            15,
            "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food9.jpg",

            ),
        Food(
            "Baryooni",
            "16",
            "Ilam, Iran",
            4.5f,
            28,
            "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food10.jpg",

            ),
        Food(
            "Ghorme Sabzi",
            "11",
            "Karaj, Iran",
            5f,
            27,
            "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food11.jpg",

            ),
        Food(
            "Rice",
            "12" ,
            "Shiraz, Iran",
            2.5f,
            35,
            "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food12.jpg",

            ),
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        myAdapter = FoodAdapter(data.clone() as ArrayList<Food> , this)
        binding.mainRecyclerView.adapter = myAdapter
        binding.mainRecyclerView.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.imgAdd.setOnClickListener {
                val addDialog = AddFoodDialog(this, data)
            addDialog.show(supportFragmentManager, null)
        }
        binding.edtFoodSearch.addTextChangedListener {wordSearch ->
            if (wordSearch!!.isNotEmpty()){
                val cloneList :ArrayList<Food> = data.clone() as ArrayList<Food>
                val filtredFood = cloneList.filter { food ->
                    (food.foodName.contains(wordSearch))
                }
                myAdapter.setData(ArrayList(filtredFood))
            }else{
                myAdapter.setData(data.clone() as ArrayList<Food>)
            }
        }
    }
    override fun sendData(
        foodName: String,
        foodPrice: String,
        foodPlace: String,
        foodRate: Float,
        foodRatingNum: Int
    ) {
        val newFood = Food(
            foodName,
            foodPrice,
            foodPlace,
            foodRate,
            foodRatingNum,
            "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food1.jpg"
        )
        myAdapter.addFood(newFood)
        binding.mainRecyclerView.scrollToPosition(0)


    }
    override fun deleteFood(oldFood: Food, position: Int) {
        val dialog= DeleteFoodDialog(myAdapter , oldFood , position , this)
        dialog.show(supportFragmentManager , null)

    }

    override fun updateFood(newFood: Food, position: Int , image :String) {
        val dialog = UpdateFoodDialog( myAdapter, this , newFood , position  , image)
        dialog.show(supportFragmentManager, null)
    }

}