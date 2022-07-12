package edu.uchicago.gerber.books.navagation

import edu.uchicago.gerber.favs.R


sealed class Screen(var route: String, var icon: Int, var title: String) {
    object Search : Screen("search", R.drawable.ic_search, "Search")
    object Detail : Screen("detail", 0, "Detail")
}