package ca.georgiancollege.assignment_1_v2

import okhttp3.OkHttpClient
import okhttp3.Request

object MovieRepository {
   private val httpClient = OkHttpClient()

   fun fetchSearchResults(query: String, apiKey: String): String {
      val apiUrl = "https://www.omdbapi.com/?s=$query&apikey=$apiKey"
      val request = Request.Builder().url(apiUrl).build()
      httpClient.newCall(request).execute().use { response ->
         return response.body?.string() ?: ""
      }
   }

   fun fetchMovieDetailsById(imdbID: String, apiKey: String): String {
      val apiUrl = "https://www.omdbapi.com/?i=$imdbID&apikey=$apiKey"
      val request = Request.Builder().url(apiUrl).build()
      httpClient.newCall(request).execute().use { response ->
         return response.body?.string() ?: ""
      }
   }
}