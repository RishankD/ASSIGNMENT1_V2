import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.georgiancollege.assignment_1_v2.Movie
import ca.georgiancollege.assignment_1_v2.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

class MovieViewModel : ViewModel() {
   val moviesLiveData = MutableLiveData<List<Movie>>()
   val selectedMovieDetails = MutableLiveData<JSONObject>()

   fun searchMovies(query: String, apiKey: String) {
      viewModelScope.launch(Dispatchers.IO) {
         try {
            val response = MovieRepository.searchMovies(query, apiKey)
            val json = JSONObject(response)
            val movieList = mutableListOf<Movie>()

            if (json.getString("Response") == "True") {
               val array = json.getJSONArray("Search")
               for (i in 0 until array.length()) {
                  val obj = array.getJSONObject(i)
                  movieList.add(
                     Movie(
                        obj.getString("Title"),
                        obj.getString("Year"),
                        obj.getString("imdbID"),
                        obj.getString("Type"),
                        obj.getString("Poster")
                     )
                  )
               }
            }
            moviesLiveData.postValue(movieList)
         } catch (e: Exception) {
            e.printStackTrace()
         }
      }
   }

   fun loadMovieDetails(imdbID: String, apiKey: String) {
      viewModelScope.launch(Dispatchers.IO) {
         try {
            val response = MovieRepository.fetchMovieDetails(imdbID, apiKey)
            selectedMovieDetails.postValue(JSONObject(response))
         } catch (e: Exception) {
            e.printStackTrace()
         }
      }
   }
}
