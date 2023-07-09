package com.ranosan.challenge.boof.presentation.screen.home

import androidx.lifecycle.ViewModel
import com.ranosan.challenge.boof.domain.Poster
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {
    val itemPoster = listOf(
        Poster(
            poster = "https://m.media-amazon.com/images/M/MV5BZmJlZDE1NDgtYjJiZS00NWY1LWFlZDEtZGVhM2U5ODhjMDNmXkEyXkFqcGdeQXVyNTIzOTk5ODM@._V1_FMjpg_UX800_.jpg",
            title = "Brave",
            year = 2022,
            country = "America",
            genre = "Adventure"
        ),
        Poster(
            poster = "https://upload.wikimedia.org/wikipedia/commons/f/f8/Time_Teens_Cinema_Poster.png",
            title = "Time Teens",
            year = 2023,
            country = "America",
            genre = "Action"
        )
    )
}