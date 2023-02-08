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

        //PRODUCER, flow must be of the <type> you are EMITTING
        val flow = flow<String> {
            for(i in 1..10) {
                emit("Hello World!")
                delay(1000L)
            }
        }

        //CONSUMER, must be launched in coroutine. By default, producer/consumer use same coroutine.
        GlobalScope.launch{
            flow.collect {
                println(it)
                delay(2000L)
            }
        }

        //Output: "Hello World" is printed every 3 seconds because producer/consumer are in the same coroutine.
        // Other Info:
        // "Back pressure": server sends more data than client can process -> Flows make sure Producer only gives data to Consumer when Consumer can process it.

    }
}