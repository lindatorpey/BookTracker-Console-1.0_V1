package org.wit.booktracker.console.main

import mu.KotlinLogging

private val logger = KotlinLogging.logger{}

var title = ""
var author = ""

fun main(args: Array<String>){
    logger.info {"Launching Booktracker Console App"}
    println("BookTracker Kotlin App Version 1.0")
    var input: Int

    do {
        input = menu()
        when(input){
            1 -> addBook()
            2 -> updateBook()
            3 -> listBooks()
            -1 -> println("Exiting App, Bye Bye!")
            else -> println("This is an invalid option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down Booktracker App" }


}

fun menu() :Int{
    var option : Int
    var input : String? = null

    println("Main Menu")
    println("1. Add Book")
    println("2. Update Book")
    println("3. List all Books")
    println("-1. Exit")
    println()
    print("Enter an integer : ")
    input = readLine()!!
    option = if (input.toIntOrNull() != null && !input.isEmpty())
        input.toInt()
    else
        -9

    return option
}

fun addBook(){
    println("Add a Book")
    println()
    print("Enter a Book Title : ")
    title = readLine()!!
    println("Add an Author")
    println()
    print("Enter the Author's Name : ")
    author = readLine()!!
    println("You entered $title for book title and $author for author")
}

fun updateBook(){
    println("Update a book")
    println()
    print("Enter a new Title for $title : ")
    title = readLine()!!
    print("Enter a new Author for $author : ")
    author = readLine()!!
    println("You updated $title for Title and $author for Author")
}

fun listBooks(){
    println("Here is a list of your books")
}
