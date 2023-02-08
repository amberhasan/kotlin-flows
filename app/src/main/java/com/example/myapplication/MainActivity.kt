package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //PRODUCER
        val flow = flow<String> {
            for(i in 1..10) {
                emit("Hello World!")
                delay(1000L)
            }
        }

        //CONSUMER,
        GlobalScope.launch{
            // You can also pass a parameter in .buffer() as how much you want to carry in the buffer, in bytes. 
            flow.buffer().collect {//consumes in a different coroutine than the producer
                println(it)
                delay(2000L)
            }
        }

        //Output: "Hello World" is printed every 2 seconds because producer/consumer are in the different coroutines.
    }
}