package edu.uchicago.gerber.favs.models

data class Paginate(
    val query: String = "",
    val maxResult: Int = 10,
    val startIndex: Int = 1,
)
