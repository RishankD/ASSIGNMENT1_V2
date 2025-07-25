package ca.georgiancollege.assignment_1_v2

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ca.georgiancollege.assignment_1_v2.databinding.ItemMovieBinding

class MovieAdapter(private val onClick: (String) -> Unit) :
   ListAdapter<Movie, MovieAdapter.MovieViewHolder>(DiffCallback()) {

   inner class MovieViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
      val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
      return MovieViewHolder(binding)
   }

   override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
      val movie = getItem(position)
      holder.binding.titleText.text = movie.Title
      holder.binding.yearText.text = movie.Year
      holder.binding.root.setOnClickListener { onClick(movie.imdbID) }
   }

   class DiffCallback : DiffUtil.ItemCallback<Movie>() {
      override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem.imdbID == newItem.imdbID
      @SuppressLint("DiffUtilEquals")
      override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = oldItem == newItem
   }
}
