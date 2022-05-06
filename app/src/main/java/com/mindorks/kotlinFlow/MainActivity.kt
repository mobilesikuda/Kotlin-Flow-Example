package com.mindorks.kotlinFlow

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mindorks.kotlinFlow.databinding.ActivityMainBinding
import com.mindorks.kotlinFlow.learn.completion.CompletionActivity
import com.mindorks.kotlinFlow.learn.errorhandling.catch.CatchActivity
import com.mindorks.kotlinFlow.learn.errorhandling.emitall.EmitAllActivity
import com.mindorks.kotlinFlow.learn.filter.FilterActivity
import com.mindorks.kotlinFlow.learn.map.MapActivity
import com.mindorks.kotlinFlow.learn.reduce.ReduceActivity
import com.mindorks.kotlinFlow.learn.retrofit.parallel.ParallelNetworkCallsActivity
import com.mindorks.kotlinFlow.learn.retrofit.series.SeriesNetworkCallsActivity
import com.mindorks.kotlinFlow.learn.retrofit.single.SingleNetworkCallActivity
import com.mindorks.kotlinFlow.learn.retry.RetryActivity
import com.mindorks.kotlinFlow.learn.retryexponentialbackoff.RetryExponentialBackoffActivity
import com.mindorks.kotlinFlow.learn.retrywhen.RetryWhenActivity
import com.mindorks.kotlinFlow.learn.room.RoomDBActivity
import com.mindorks.kotlinFlow.learn.search.SearchActivity
import com.mindorks.kotlinFlow.learn.task.onetask.LongRunningTaskActivity
import com.mindorks.kotlinFlow.learn.task.twotasks.TwoLongRunningTasksActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val viewAll = binding.root
        setContentView(viewAll)

        binding.startSingleNetworkCallActivity.setOnClickListener {
            startActivity(Intent(this@MainActivity, SingleNetworkCallActivity::class.java))
        }

        binding.startSeriesNetworkCallsActivity.setOnClickListener {
            startActivity(Intent(this@MainActivity, SeriesNetworkCallsActivity::class.java))
        }

        binding.startParallelNetworkCallsActivity.setOnClickListener {
            startActivity(Intent(this@MainActivity, ParallelNetworkCallsActivity::class.java))
        }

        binding.startRoomDatabaseActivity.setOnClickListener {
            startActivity(Intent(this@MainActivity, RoomDBActivity::class.java))
        }

        binding.startCatchActivity.setOnClickListener {
            startActivity(Intent(this@MainActivity, CatchActivity::class.java))
        }

        binding.startEmitAllActivity.setOnClickListener {
            startActivity(Intent(this@MainActivity, EmitAllActivity::class.java))
        }

        binding.startCompletionActivity.setOnClickListener {
            startActivity(Intent(this@MainActivity, CompletionActivity::class.java))
        }

        binding.startLongRunningTaskActivity.setOnClickListener {
            startActivity(Intent(this@MainActivity, LongRunningTaskActivity::class.java))
        }

        binding.startTwoLongRunningTasksActivity.setOnClickListener {
            startActivity(Intent(this@MainActivity, TwoLongRunningTasksActivity::class.java))
        }

        binding.startFilterActivity.setOnClickListener {
            startActivity(Intent(this@MainActivity, FilterActivity::class.java))
        }

        binding.startMapActivity.setOnClickListener {
            startActivity(Intent(this@MainActivity, MapActivity::class.java))
        }

        binding.startReduceActivity.setOnClickListener {
            startActivity(Intent(this@MainActivity, ReduceActivity::class.java))
        }

        binding.startSearchActivity.setOnClickListener {
            startActivity(Intent(this@MainActivity, SearchActivity::class.java))
        }

        binding.startRetryActivity.setOnClickListener {
            startActivity(Intent(this@MainActivity, RetryActivity::class.java))
        }

        binding.startRetryWhenActivity.setOnClickListener {
            startActivity(Intent(this@MainActivity, RetryWhenActivity::class.java))
        }

        binding.startRetryExponentialBackoffActivity.setOnClickListener {
            startActivity(Intent(this@MainActivity, RetryExponentialBackoffActivity::class.java))
        }
    }

}
