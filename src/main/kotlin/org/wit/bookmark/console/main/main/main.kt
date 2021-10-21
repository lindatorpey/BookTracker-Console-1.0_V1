package main

import mu.KotlinLogging
import org.wit.bookmark.console.main.models.BooktrackerMemStore
import org.wit.bookmark.console.main.models.BooktrackerModel
import org.wit.bookmark.console.main.views.BooktrackerView


private val logger = KotlinLogging.logger{}

val booktrackers = BooktrackerMemStore()
val booktrackerView = BooktrackerView()

fun main(args: Array<String>){
    logger.info {"Launching Booktracker Console App"}
    println("BookTracker Kotlin App Version 1.0")

    var input: Int

    do {
        input = booktrackerView.menu()
        when(input){
            1 -> addBook()
            2 -> updateBook()
            3 -> booktrackerView.listBooks(booktrackers)
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
    if (booktrackerView.addBookData((aBook)))
        booktrackers.create(aBook)

    else
        logger.info("Placemark Not Added")
}

fun updateBook() {
    booktrackerView.listBooks(booktrackers)
    var searchId = booktrackerView.getId()
    val aBook = search(searchId)

    if (aBook != null) {
        if (booktrackerView.updateBookData(aBook)) {
            booktrackers.update(aBook)
            booktrackerView.showBook(aBook)
            logger.info("Book updated :  $aBook")
        } else
            logger.info("Book not Updated")
    } else
        println("Book not Updated")
}

/*fun listBooks(){
    println("Here is a list of your books")
    println()
    booktrackers.forEach { logger.info("${it}") }
}*/
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
    var foundBook = booktrackers.findOne(id)
    return foundBook
}

fun searchBooktracker()  {
    var aBook = search(booktrackerView.getId())!!
    booktrackerView.showBook(aBook)

}