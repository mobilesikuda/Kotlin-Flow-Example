package com.mindorks.kotlinFlow.learn.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mindorks.kotlinFlow.databinding.ActivitySearchBinding
import com.mindorks.kotlinFlow.utils.getQueryTextChangeStateFlow
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.coroutines.CoroutineContext

class SearchActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var binding: ActivitySearchBinding

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private lateinit var job: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        job = Job()
        setUpSearchStateFlow()
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }

    private fun setUpSearchStateFlow() {
        launch {
            binding.searchView.getQueryTextChangeStateFlow()
                .debounce(300)
                .filter { query ->
                    if (query.isEmpty()) {
                        binding.textViewResult.text = ""
                        return@filter false
                    } else {
                        return@filter true
                    }
                }
                .distinctUntilChanged()
                .flatMapLatest { query ->
                    dataFromNetwork(query)
                        .catch {
                            emitAll(flowOf(""))
                        }
                }
                .flowOn(Dispatchers.Default)
                .collect { result ->
                    binding.textViewResult.text = result
                }
        }
    }

    /**
     * Simulation of network data
     */
    private fun dataFromNetwork(query: String): Flow<String> {
        return flow {
            delay(2000)
            emit(query)
        }
    }

}