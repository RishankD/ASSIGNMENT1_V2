package ca.georgiancollege.assignment_1_v2

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object MovieRepository {
   fun searchMovies(query: String, apiKey: String): String {
      val url = URL("https://www.omdbapi.com/?s=$query&apikey=$apiKey")
      val conn = url.openConnection() as HttpURLConnection
      conn.requestMethod = "GET"

      val reader = BufferedReader(InputStreamReader(conn.inputStream))
      val result = reader.readText()
      reader.close()

      return result
   }

   fun fetchMovieDetails(imdbID: String, apiKey: String): String {
      val url = URL("https://www.omdbapi.com/?i=$imdbID&apikey=$apiKey")
      val conn = url.openConnection() as HttpURLConnection
      conn.requestMethod = "GET"

      val reader = BufferedReader(InputStreamReader(conn.inputStream))
      val result = reader.readText()
      reader.close()

      return result
   }
}