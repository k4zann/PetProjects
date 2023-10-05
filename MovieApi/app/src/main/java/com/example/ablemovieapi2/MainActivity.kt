package com.example.ablemovieapi2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.example.ablemovieapi2.data.Image
import com.example.ablemovieapi2.data.Images
import com.example.ablemovieapi2.data.Movie
import com.example.ablemovieapi2.databinding.ActivityMainBinding
import com.example.ablemovieapi2.presenter.MoviePresenter
import com.example.ablemovieapi2.presenter.MoviePresenterImpl
import com.example.ablemovieapi2.vm.MovieImagesViewModel
import com.example.ablemovieapi2.vm.MovieViewModel


class MainActivity : AppCompatActivity() {

    private val movieIdEdit: EditText by lazy { findViewById(R.id.movie_id_edit) }
    private val loadButton: Button by lazy { findViewById(R.id.load_button) }
    private val movieTitle: TextView by lazy { findViewById(R.id.movie_name_value) }
    private val movieReleaseDate: TextView by lazy { findViewById(R.id.movie_release_date) }
    private val movieBudget: TextView by lazy { findViewById(R.id.movie_budget_value) }
    private lateinit var binding: ActivityMainBinding
//    private val presenter: MoviePresenter by lazy { MoviePresenterImpl() }

    //Model --> View --> Presenter
    //Model --> View --> ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        Log.d(TAG, "Activity -> OnCreate()")

        val imagesList = mutableListOf<Image>()
        binding.listOfImages.layoutManager = LinearLayoutManager(this)
//        binding.listOfImages.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.listOfImages.adapter = PosterAdapter(imagesList)
    }

    override fun onStart() {
        super.onStart()

//        presenter.setMovieView(this)
//        presenter.setMoviePostersView(this)
        val movieViewModel: MovieViewModel by viewModels()
        val movieImagesViewModel: MovieImagesViewModel by viewModels()

        movieViewModel.currentMovie.observe(this) { movie ->
            setMovie(movie)
        }
        movieImagesViewModel.currentMoviePosters.observe(this) { moviePosters ->
            setMovieImage(moviePosters)
        }
        loadButton.setOnClickListener {
            val movieId = movieIdEdit.text.toString().toLongOrNull()
            if (movieId != null) {
                movieViewModel.loadMovie(movieId)
                movieImagesViewModel.loadMovie(movieId)
            } else {
                Toast.makeText(applicationContext, "Invalid movie ID", Toast.LENGTH_SHORT).show()
            }
        }
        Log.d(TAG, "Activity -> onStart()")
    }

//    override fun onStop() {
//        super.onStop()
//
//        presenter.setMovieView(null)
//        presenter.setMoviePostersView(null)
//    }

    fun setMovie(movie: Movie?) {
        if (movie == null) {
            return
        }

        Log.i(TAG, "Movie arrived -> $movie")

        movieTitle.text = movie.title
        movieReleaseDate.text = movie.releaseDate
        movieBudget.text = movie.budget.toString()

    }

    fun setMovieImage(images: Images?){
        if(images == null){
            return
        }
        val newImages:Images = images
        val posterAdapter = binding.listOfImages.adapter as PosterAdapter
        posterAdapter.setData(newImages)
    }


    companion object {
        const val TAG = "MainActivity"
    }






    /*private val callback = object : Callback<Movie> {
        override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
            if (response.isSuccessful) {
                setMovie(response.body())
            } else {
                Toast.makeText(applicationContext, "Query Failed", Toast.LENGTH_SHORT).show()
            }
        }

        override fun onFailure(call: Call<Movie>, t: Throwable) {
            Toast.makeText(applicationContext, "Query Failed", Toast.LENGTH_SHORT).show()
        }
    }

    private val callbackPosters = object : Callback<Images> {
        override fun onResponse(call: Call<Images>, response: Response<Images>) {
            if (response.isSuccessful) {
                setMovieImage(response.body())
                response.body()?.backdrops!![0].filePath?.let { Log.e("pack", it.toString()) }
            } else {
                Toast.makeText(applicationContext, "Failed onResponse", Toast.LENGTH_SHORT).show()
            }
        }

        override fun onFailure(call: Call<Images>, t: Throwable) {
            Toast.makeText(applicationContext, "Failed onFailure", Toast.LENGTH_SHORT).show()
        }
    }*/




}
