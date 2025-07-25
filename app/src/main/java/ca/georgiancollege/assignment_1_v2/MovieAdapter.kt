package ca.georgiancollege.assignment_1_v2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ca.georgiancollege.assignment_1_v2.databinding.ItemMovieBinding

class MovieAdapter(private val onMovieClicked: (String) -> Unit) :
   ListAdapter<Movie, MovieAdapter.MovieViewHolder>(MovieDiffCallback()) {

   inner class MovieViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
      val layoutInflater = LayoutInflater.from(parent.context)
      val binding = ItemMovieBinding.inflate(layoutInflater, parent, false)
      return MovieViewHolder(binding)
   }

   override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
      val movieItem = getItem(position)
      holder.binding.titleText.text = movieItem.Title
      holder.binding.yearText.text = movieItem.Year
      holder.binding.root.setOnClickListener {
         onMovieClicked(movieItem.imdbID)
      }
   }

   class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
      override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
         oldItem.imdbID == newItem.imdbID

      override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
         oldItem == newItem
   }
}
