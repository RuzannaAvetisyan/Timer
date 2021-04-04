package ruzanna.game.timer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private var timeStart = 0
    private var startTimerTrue = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        time.text = timeStart.toString()

        nextPage.setOnClickListener {
            val intent = Intent(this, NextActivity::class.java)
            intent.putExtra("timer", timeStart.toString())
            startActivityForResult(intent, 1000)
            finish()
        }
        startTimer.setOnClickListener {
            if(startTimerTrue){
                startTimerTrue = false
                startT()
            }
        }
    }

    private fun startT() {
        GlobalScope.launch(Dispatchers.Main) {
            timeStart = withContext(Dispatchers.Default){
                delay(1000)
                timeStart + 1
            }
            time.text = timeStart.toString()
            startT()
        }
    }

}
