package org.wit.bookmark.console.main

import mu.KotlinLogging
import org.wit.bookmark.console.main.main.controller.BooktrackerController
import org.wit.bookmark.console.main.models.BooktrackerJSONStore
import org.wit.bookmark.console.main.models.BooktrackerMemStore
import org.wit.bookmark.console.main.models.BooktrackerModel
import org.wit.bookmark.console.main.views.BooktrackerView


private val logger = KotlinLogging.logger{}

val booktrackers = BooktrackerMemStore()
//val booktrackers = BooktrackerJSONStore()
val booktrackerView = BooktrackerView()

fun main(args: Array<String>){
    BooktrackerController().start()
}
