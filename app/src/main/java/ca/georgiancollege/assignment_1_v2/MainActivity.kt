package ca.georgiancollege.assignment_1_v2

import MovieViewModel
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ca.georgiancollege.assignment_1_v2.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
   private lateinit var binding: ActivityMainBinding
   private val viewModel: MovieViewModel by viewModels()
   private lateinit var adapter: MovieAdapter
   private val apiKey = "25c87dc7"

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      binding = ActivityMainBinding.inflate(layoutInflater)
      setContentView(binding.root)

      adapter = MovieAdapter { imdbID ->
         val intent = Intent(this, DetailsActivity::class.java)
         intent.putExtra("imdbID", imdbID)
         startActivity(intent)
      }

      binding.movieRecycler.layoutManager = LinearLayoutManager(this)
      binding.movieRecycler.adapter = adapter

      binding.searchButton.setOnClickListener {
         val query = binding.searchInput.text.toString()
         if (query.isNotBlank()) {
            viewModel.searchMovies(query, apiKey)
         }
      }

      viewModel.moviesLiveData.observe(this) { movies ->
         adapter.submitList(movies)
      }
   }
}