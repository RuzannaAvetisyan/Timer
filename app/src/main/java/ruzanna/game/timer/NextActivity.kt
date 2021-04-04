package ruzanna.game.timer

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.time
import kotlinx.android.synthetic.main.activity_next.*
import kotlinx.coroutines.*


class NextActivity:  AppCompatActivity()  {

    private var timeStart = 0
    private var startTimerTrue = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)
        val extras = intent.extras
        if(extras != null){
            val res = extras.get("timer") as String
            time.text = res
            timeStart = res.toInt()
        }else{
            time.text = timeStart.toString()
        }
        start.setOnClickListener {
            Log.i("ibsjh", "sdnkjdm")
            startTimerTrue = true
            startT()
            start.isClickable = false
        }
        stop.setOnClickListener {
            startTimerTrue = false
            start.isClickable = true
        }
    }
    private fun startT() {
        if(startTimerTrue) {
            GlobalScope.launch(Dispatchers.Main) {
                timeStart = withContext(Dispatchers.Default) {
                    delay(1000)
                    timeStart + 1
                }
                time.text = timeStart.toString()
                startT()
            }
        }
    }

}