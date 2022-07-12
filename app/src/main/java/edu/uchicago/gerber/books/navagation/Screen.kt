package edu.uchicago.gerber.books.navagation

import edu.uchicago.gerber.favs.R


sealed class Screen(var route: String) {
    object Search : Screen("search")
    object Detail : Screen("detail")
}