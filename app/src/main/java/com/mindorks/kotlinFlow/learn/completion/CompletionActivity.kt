package com.mindorks.kotlinFlow.learn.completion

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mindorks.kotlinFlow.R
import com.mindorks.kotlinFlow.data.api.ApiHelperImpl
import com.mindorks.kotlinFlow.data.api.RetrofitBuilder
import com.mindorks.kotlinFlow.data.local.DatabaseBuilder
import com.mindorks.kotlinFlow.data.local.DatabaseHelperImpl
import com.mindorks.kotlinFlow.databinding.ActivityCompletionBinding
import com.mindorks.kotlinFlow.learn.base.ApiUserAdapter
import com.mindorks.kotlinFlow.utils.Status
import com.mindorks.kotlinFlow.utils.ViewModelFactory
//import kotlinx.android.synthetic.main.activity_long_running_task.*
//import kotlinx.android.synthetic.main.activity_recycler_view.progressBar

class CompletionActivity : AppCompatActivity() {

    private lateinit var viewModel: CompletionViewModel
    private lateinit var adapter: ApiUserAdapter
    private lateinit var binding: ActivityCompletionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCompletionBinding.inflate(layoutInflater)
        val viewAll = binding.root
        setContentView(viewAll)
        setupViewModel()
        setupObserver()
    }

    private fun setupObserver() {
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
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService),
                DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext))
            )
        ).get(CompletionViewModel::class.java)
    }
}