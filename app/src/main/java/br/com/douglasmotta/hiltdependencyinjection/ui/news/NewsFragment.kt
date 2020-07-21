package br.com.douglasmotta.hiltdependencyinjection.ui.news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.douglasmotta.hiltdependencyinjection.R
import br.com.douglasmotta.hiltdependencyinjection.data.WebApiAccess
import br.com.douglasmotta.hiltdependencyinjection.data.repository.NewsApiDataSource
import br.com.douglasmotta.hiltdependencyinjection.data.repository.NewsDbDataSource
import br.com.douglasmotta.hiltdependencyinjection.data.repository.NewsRepository
import kotlinx.android.synthetic.main.main_fragment.*

class NewsFragment : Fragment(R.layout.main_fragment) {

    private val viewModel by viewModels<NewsViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val newsDbDataSource = NewsDbDataSource()
                val newsApiDataString = NewsApiDataSource(WebApiAccess.newsApi)
                val newsRepository =
                    NewsRepository(requireContext(), newsDbDataSource, newsApiDataString)

                return NewsViewModel(newsRepository) as T
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.articlesEvent.observe(viewLifecycleOwner, Observer {
            with(recyclerArticles) {
                setHasFixedSize(true)
                adapter = NewsAdapter(it)
            }
        })

        viewModel.getNews()
    }

}