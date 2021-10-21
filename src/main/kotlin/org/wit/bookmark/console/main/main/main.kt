package org.wit.booktracker.console.main

import mu.KotlinLogging
import org.wit.bookmark.console.main.models.BooktrackerModel

private val logger = KotlinLogging.logger{}

val booktrackers = ArrayList<BooktrackerModel>()

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
            4 -> searchBooktracker()
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
    print("Enter Option : ")
    input = readLine()!!
    option = if (input.toIntOrNull() != null && !input.isEmpty())
        input.toInt()
    else
        -9

    return option
}

fun addBook(){
    var aBook = BooktrackerModel()
    println("Add a Book")
    println()
    print("Enter a Book Title : ")
    aBook.title = readLine()!!
    println("Add an Author")
    println()
    print("Enter the Author's Name : ")
    aBook.author = readLine()!!
    if (aBook.title.isNotEmpty() && aBook.author.isNotEmpty()) {
        aBook.id = booktrackers.size.toLong()
        booktrackers.add(aBook.copy())
        aBook.id++
        logger.info("Book Added : [ $aBook ]")
    }
    else
        logger.info("Placemark Not Added")
}

fun updateBook() {
    println("Update a book")
    println()
    listBooks()
    var searchId = getId()
    val aBook = search(searchId)
    var tempTitle: String?
    var tempAuth: String?

    if (aBook != null) {
        print("Enter a new Title for $aBook.title : ")
        tempTitle = readLine()!!
        print("Enter a new Author for $aBook.author : ")
        tempAuth = readLine()!!

        if (!tempTitle.isNullOrEmpty() && !tempAuth.isNullOrEmpty()) {
            aBook.title = tempTitle
            aBook.author = tempAuth

            println("You updated $aBook.title for Title and $aBook.author for Author")
        } else
            logger.info("Details Not Updated")
    } else
        println("Details not updated..")
}

fun listBooks(){
    println("Here is a list of your books")
    println()
    booktrackers.forEach { logger.info("${it}") }
}
fun getId(): Long{
    var strId : String?
    var searchId : Long
    print("Enter id to Search/Update: ")
    strId = readLine()!!
    searchId = if(strId.toLongOrNull() != null && !strId.isEmpty())
        strId.toLong()
    else
        -9
    return searchId
}
fun search(id: Long) : BooktrackerModel? {
    var foundBook: BooktrackerModel? = booktrackers.find { b -> b.id == id }
    return foundBook
}

fun searchBooktracker(){
    var searchId = getId()
    val aBook= search(searchId)

    if(aBook != null)
        println("Book Details $aBook")
    else
        println("Book not Found")
}