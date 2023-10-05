package com.example.coroutinestest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.coroutinestest.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val testText: TextView by lazy { findViewById(R.id.test_text) }
    private val stopButton: Button by lazy { findViewById(R.id.stop_button) }
    private val asyncText: TextView by lazy { findViewById(R.id.async_text) }
    private val secondEditText: EditText by lazy { findViewById(R.id.test_editText) }
    private val startButton: Button by lazy { findViewById(R.id.start_button) }

    private var timer: Job? = null
    private lateinit var binding: ActivityMainBinding
    private val countdownFlow = MutableSharedFlow<Long>(replay = 1)
    private var countdownJob: Job? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


//        val scope = CoroutineScope(Dispatchers.IO)
//        startButton.setOnClickListener {
//            val timerValue = secondEditText.text.toString().toLongOrNull() ?: return@setOnClickListener
//
//            timer = countdownFlow(timerValue)
//                .takeWhile { it >= 0 }
//                .onEach { value ->
//                    val msSecond = "00"
//                    val seconds = value.toString().padStart(2, '0')
//
//                    withContext(Dispatchers.Main) {
//                        testText.text = seconds
//                        asyncText.text = msSecond.padStart(2, '0')
//                    }
//                }
//                .map { it - 10 }
//                .onCompletion { cause ->
//                    if (cause == null) {
//                        withContext(Dispatchers.Main) {
//                            testText.text = "00"
//                            asyncText.text = "00"
//                        }
//                    }
//                }
//                .debounce(100)
//                .flowOn(Dispatchers.Default)
//                .launchIn(GlobalScope)
//        }
//        stopButton.setOnClickListener {
//            timer?.cancel()
//        }
        startButton.setOnClickListener {
            val timerValue = secondEditText.text.toString().toLongOrNull() ?: return@setOnClickListener

            countdownJob?.cancel()
            countdownJob = startCountdown(timerValue)
        }

        stopButton.setOnClickListener {
            countdownJob?.cancel()
        }


//        startButton.setOnClickListener {
//            var timerValue = secondEditText.text.toString().toLongOrNull()
//            if (timerValue != null) {
//                timerValue--
//
//                timer = GlobalScope.launch(Dispatchers.Default) {
//                    var msSecond = 0
//                    while (timerValue >= 0) {
//                        if (msSecond < 0) msSecond = 90
//                        delay(100)
//
//                        if (msSecond <= 0 && timerValue >= 0 ) {
//                            withContext(Dispatchers.Main ) {
//                                var newSecond = ""
//                                if (timerValue<0) {
//                                    newSecond = "0"
//                                } else {
//                                    newSecond = timerValue.toString()
//                                }
//                                testText.text = newSecond.padStart(2,'0')
//                            }
//                            timerValue--
//                        }
//                        withContext(Dispatchers.Main) {
//                            val newMsSecond = msSecond.toString()
//                            asyncText.text = "${newMsSecond.padStart(2,'0')}"
//                        }
//                        msSecond-=10
//                    }
//                }
//            }
//        }
    }


    fun countdownFlow(initialValue: Long): Flow<Long> = flow {
        var timerValue = initialValue
        if (timerValue != null) {
            timerValue--

            timer = GlobalScope.launch(Dispatchers.Default) {
                var msSecond = 0
                while (timerValue >= 0) {
                    if (msSecond < 0) msSecond = 90
                    delay(100)

                    if (msSecond <= 0 && timerValue >= 0) {
                        withContext(Dispatchers.Main) {
                            var newSecond = ""
                            if (timerValue < 0) {
                                newSecond = "0"
                            } else {
                                newSecond = timerValue.toString()
                            }
                            testText.text = newSecond.padStart(2, '0')
                        }
                        timerValue--
                    }
                    withContext(Dispatchers.Main) {
                        val newMsSecond = msSecond.toString()
                        asyncText.text = "${newMsSecond.padStart(2, '0')}"
                    }
                    msSecond -= 10
                }
            }
        }
    }
    fun startCountdown(initialValue: Long): Job {
        return GlobalScope.launch(Dispatchers.Default) {
            var timerValue = initialValue
            if (timerValue != null) {
                timerValue--

                timer = GlobalScope.launch(Dispatchers.Default) {
                    var msSecond = 0
                    while (timerValue >= 0) {
                        if (msSecond < 0) msSecond = 90
                        delay(100)

                        if (msSecond <= 0 && timerValue >= 0) {
                            withContext(Dispatchers.Main) {
                                var newSecond = ""
                                if (timerValue < 0) {
                                    newSecond = "0"
                                } else {
                                    newSecond = timerValue.toString()
                                }
                                testText.text = newSecond.padStart(2, '0')
                            }
                            timerValue--
                        }
                        withContext(Dispatchers.Main) {
                            val newMsSecond = msSecond.toString()
                            asyncText.text = "${newMsSecond.padStart(2, '0')}"
                        }
                        msSecond -= 10
                    }
                }
            }

            countdownFlow.emit(0)
        }

        countdownFlow
            .onEach { value ->
                val msSecond = "0"
                val seconds = value.toString().padStart(2, '0')

                withContext(Dispatchers.Main) {
                    testText.text = seconds
                    asyncText.text = msSecond.padStart(2, '0')
                }
            }
            .launchIn(GlobalScope)
    }

    fun intFlow(): Flow<Int> = flow {
        var ticker = 0
        while (true) {
            delay(1000L)
            ticker++
            emit(ticker)
        }
    }

}