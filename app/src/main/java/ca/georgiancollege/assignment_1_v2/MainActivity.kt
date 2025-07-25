package ca.georgiancollege.assignment_1_v2

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ca.georgiancollege.assignment_1_v2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

   private lateinit var binding: ActivityMainBinding
   private val viewModel: MovieViewModel by viewModels()
   private lateinit var movieAdapter: MovieAdapter
   private val apiKey = "25c87dc7"

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      binding = ActivityMainBinding.inflate(layoutInflater)
      setContentView(binding.root)

      movieAdapter = MovieAdapter { imdbID ->
         val detailsIntent = Intent(this, DetailsActivity::class.java)
         detailsIntent.putExtra("imdbID", imdbID)
         startActivity(detailsIntent)
      }

      binding.movieRecycler.layoutManager = LinearLayoutManager(this)
      binding.movieRecycler.adapter = movieAdapter

      binding.searchButton.setOnClickListener {
         val searchQuery = binding.searchInput.text.toString()
         if (searchQuery.isNotBlank()) {
            viewModel.searchForMovies(searchQuery, apiKey)
         }
      }

      viewModel.searchResults.observe(this) { movieList ->
         movieAdapter.submitList(movieList)
      }
   }
}