package com.ticonsys.memoryleakdemo.presentation.activities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ticonsys.memoryleakdemo.domain.models.Video
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
) : ViewModel() {

    private val _videos = MutableLiveData<List<Video>>()
    val videos: LiveData<List<Video>>
        get() = _videos

    private val images = listOf(
        "https://m.media-amazon.com/images/M/MV5BNTc2NzE0OTA0OF5BMl5BanBnXkFtZTYwNjUzNTY5._V1_.jpg",
        "https://m.media-amazon.com/images/M/MV5BMjE4NzU1Nzc0NV5BMl5BanBnXkFtZTcwNTY0MzIyMQ@@._V1_.jpg",
        "https://m.media-amazon.com/images/I/41PS5vnJsML._AC_UF1000,1000_QL80_.jpg",
        "https://m.media-amazon.com/images/M/MV5BMTY3ODc3MjMzM15BMl5BanBnXkFtZTcwMjIwNzIyMQ@@._V1_QL75_UY207_CR3,0,140,207_.jpg",
        "https://i.ytimg.com/vi/p_Xf7K9rR1A/hq720.jpg?sqp=-oaymwEhCK4FEIIDSFryq4qpAxMIARUAAAAAGAElAADIQj0AgKJD&rs=AOn4CLD3Abfys5qswIjXZJSUohCDjBYr4w",
        "https://img.freepik.com/free-vector/cinema-realistic-poster-with-illuminated-bucket-popcorn-drink-3d-glasses-reel-tickets-blue-background-with-tapes-vector-illustration_1284-77070.jpg?size=626&ext=jpg&ga=GA1.1.1413502914.1696896000&semt=ais",
        "https://thumbs.dreamstime.com/b/movie-slate-film-reel-wood-clapper-wooden-backgorund-36502412.jpg",
    )

    fun getVideos() {
        viewModelScope.launch {
            delay(2000)
            val length = images.size
            val videos = List(20) { index ->
                val id = index + 1
                Video(id, "Video $id", images[id % length])
            }
            _videos.postValue(videos)
        }
    }
}