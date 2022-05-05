package com.mindorks.kotlinFlow.learn.retry

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mindorks.kotlinFlow.data.api.ApiHelperImpl
import com.mindorks.kotlinFlow.data.api.RetrofitBuilder
import com.mindorks.kotlinFlow.data.local.DatabaseBuilder
import com.mindorks.kotlinFlow.data.local.DatabaseHelperImpl
import com.mindorks.kotlinFlow.databinding.ActivityRetryBinding
import com.mindorks.kotlinFlow.utils.Status
import com.mindorks.kotlinFlow.utils.ViewModelFactory

class RetryActivity : AppCompatActivity() {

    private lateinit var viewModel: RetryViewModel
    private lateinit var binding: ActivityRetryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
        setupLongRunningTask()
    }

    private fun setupLongRunningTask() {
        viewModel.getStatus().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    binding.textView.text = it.data
                    binding.textView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.textView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
        viewModel.startTask()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService),
                DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext))
            )
        ).get(RetryViewModel::class.java)
    }
}
