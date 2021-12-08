package com.gio.cursoudemi.sliderutility

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.gio.cursoudemi.R
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.listener.CarouselListener
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class slider : AppCompatActivity() {

    val list = mutableListOf<CarouselItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slider)
        val carousel: ImageCarousel = findViewById(R.id.carousel)
        list.add(CarouselItem("https://lh3.googleusercontent.com/proxy/DZWqNHDGFBfZYB_Sbnc8yo0vTjwl0jDtTTYvWJ7sP6ufRRq6ALHjw9l2vkHdRKqDSmAIywvcSzVxBIndyl8sQvbNmOIpWJeh_qnWfsZsE2RDNXrY9wWQLzZRqfbnTCP8eWmTmsk"))
        list.add(CarouselItem("https://visualstudio.microsoft.com/wp-content/uploads/2016/06/ToolsForBuildingApps_636x300-op.png"))
        list.add(CarouselItem("https://www.alvantia.com/wp-content/uploads/2012/11/android.jpg"))

        carousel.addData(list)
        
        carousel.carouselListener = object : CarouselListener {
            override fun onClick(position: Int, carouselItem: CarouselItem) {
                super.onClick(position, carouselItem)
                Toast.makeText(this@slider,"position $position",Toast.LENGTH_LONG).show()
            }
        }
    }

    }
