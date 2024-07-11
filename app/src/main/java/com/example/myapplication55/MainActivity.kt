// MainActivity.kt
package com.example.myapplication55

import TimerViewModel
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication55.R

class MainActivity : AppCompatActivity() {

    private lateinit var startButton: Button
    private lateinit var timerTextView: TextView
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private lateinit var viewModel: TimerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startButton = findViewById(R.id.startButton)
        timerTextView = findViewById(R.id.timerTextView)


        viewModel = ViewModelProvider(this).get(TimerViewModel::class.java)



        handler = Handler(Looper.getMainLooper())
        runnable = object : Runnable {
            override fun run() {
                viewModel.secondsElapsed++
                timerTextView.text = "Timer: ${viewModel.secondsElapsed} seconds"
                handler.postDelayed(this, 1000)
            }
        }



        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // on below line setting text message according to the device mode
            // Başlat
            if(viewModel.isTimerRunning == true){
            handler.postDelayed(runnable, 1000)
            viewModel.isTimerRunning = true
            startButton.text = "Stop Timer"

            }
        } else {

            // on below line setting text message according to the device mode
            if(viewModel.isTimerRunning == true){
                handler.postDelayed(runnable, 1000)
                viewModel.isTimerRunning = true
                startButton.text = "Stop Timer"

            }

        }
        
        startButton.setOnClickListener {
            if (viewModel.isTimerRunning) {
                // Durdur
                handler.removeCallbacks(runnable)
                viewModel.isTimerRunning = false
                startButton.text = "Start Timer"
            } else {
                // Başlat
                handler.postDelayed(runnable, 1000)
                viewModel.isTimerRunning = true
                startButton.text = "Stop Timer"
            }
        }
        timerTextView.text = "Timer: ${viewModel.secondsElapsed} seconds"
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
    }
}
