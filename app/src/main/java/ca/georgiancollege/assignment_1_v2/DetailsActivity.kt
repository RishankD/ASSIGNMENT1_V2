package ca.georgiancollege.assignment_1_v2

import MovieViewModel
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ca.georgiancollege.assignment_1_v2.databinding.ActivityDetailsBinding


class DetailsActivity : AppCompatActivity() {
   private lateinit var binding: ActivityDetailsBinding
   private val viewModel: MovieViewModel by viewModels()
   private val apiKey = "25c87dc7"

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      binding = ActivityDetailsBinding.inflate(layoutInflater)
      setContentView(binding.root)

      val imdbID = intent.getStringExtra("imdbID") ?: return
      viewModel.loadMovieDetails(imdbID, apiKey)

      viewModel.selectedMovieDetails.observe(this) { json ->
         binding.titleText.text = json.getString("Title")
         binding.descriptionText.text = json.getString("Plot")
      }

      binding.backButton.setOnClickListener {
         finish()
      }
   }
}