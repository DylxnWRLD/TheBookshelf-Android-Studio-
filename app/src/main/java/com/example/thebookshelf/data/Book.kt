package com.example.thebookshelf.data

import com.google.gson.annotations.SerializedName

data class Book(
    val id: String,
    @SerializedName("volumeInfo")
    val volumeInfo: VolumeInfo? = null
)

data class VolumeInfo(
    val title: String? = null,
    val imageLinks: ImageLinks? = null
)

data class ImageLinks(
    val thumbnail: String? = null
)

data class BookResponse(
    val items: List<Book>? = null
)