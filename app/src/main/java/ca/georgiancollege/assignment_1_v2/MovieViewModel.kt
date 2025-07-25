package ca.georgiancollege.assignment_1_v2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

class MovieViewModel : ViewModel() {

   val searchResults = MutableLiveData<List<Movie>>()
   val movieDetails = MutableLiveData<JSONObject>()

   fun searchForMovies(query: String, apiKey: String) {
      viewModelScope.launch(Dispatchers.IO) {
         try {
            val rawResponse = MovieRepository.fetchSearchResults(query, apiKey)
            val jsonResponse = JSONObject(rawResponse)
            val movieList = mutableListOf<Movie>()

            if (jsonResponse.optString("Response") == "True") {
               val resultsArray = jsonResponse.getJSONArray("Search")
               for (index in 0 until resultsArray.length()) {
                  val movieObject = resultsArray.getJSONObject(index)
                  val movie = Movie(
                     Title = movieObject.getString("Title"),
                     Year = movieObject.getString("Year"),
                     imdbID = movieObject.getString("imdbID"),
                     Type = movieObject.getString("Type"),
                     Poster = movieObject.getString("Poster")
                  )
                  movieList.add(movie)
               }
            }
            searchResults.postValue(movieList)
         } catch (ex: Exception) {
            ex.printStackTrace()
         }
      }
   }

   fun loadDetailsForMovie(imdbID: String, apiKey: String) {
      viewModelScope.launch(Dispatchers.IO) {
         try {
            val rawResponse = MovieRepository.fetchMovieDetailsById(imdbID, apiKey)
            movieDetails.postValue(JSONObject(rawResponse))
         } catch (ex: Exception) {
            ex.printStackTrace()
         }
      }
   }
}
