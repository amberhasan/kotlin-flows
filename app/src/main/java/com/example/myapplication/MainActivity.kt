package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //PRODUCER, the flow just infers that we are emitting an Int so we don't have to write flow<Int>
        val flow = flow {
            for(i in 1..10) {
                emit(i)
                delay(1000L)
            }
        }

        //CONSUMER,
        GlobalScope.launch{
            //filter is a lambda function, using booleans
            flow.buffer().filter {
                it % 2 == 0
            }.map {
                it * it
            }.collect {
                println(it)
                delay(2000L)
            }
        }

        //Output: Filter takes all the even numbers. Map squares them. Collect prints them (the squares of even numbers).
        //Just like lists, flows also use filter and map
    }
}