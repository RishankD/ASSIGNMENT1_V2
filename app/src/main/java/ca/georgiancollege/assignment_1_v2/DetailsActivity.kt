package ca.georgiancollege.assignment_1_v2

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

      val movieId = intent.getStringExtra("imdbID") ?: return
      viewModel.loadDetailsForMovie(movieId, apiKey)

      viewModel.movieDetails.observe(this) { movieJson ->
         binding.titleText.text = movieJson.getString("Title")
         binding.descriptionText.text = movieJson.getString("Plot")
      }

      binding.backButton.setOnClickListener {
         finish()
      }
   }
}