package org.wit.bookmark.console.main.controller

import mu.KotlinLogging
import org.wit.bookmark.console.main.models.BooktrackerJSONStore
import org.wit.bookmark.console.main.models.BooktrackerModel
import org.wit.bookmark.console.main.views.BooktrackerView

class BooktrackerController {
    //val booktrackers = BooktrackerMemStore()
    val booktrackers = BooktrackerJSONStore()
    val booktrackerView = BooktrackerView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Booktracker Console App" }
        com.github.mm.coloredconsole.println{"Booktracker Kotlin App Version 1.0".bold.underline.white}
    }

    fun start(){
        var input: Int
        do {
            input = menu()
            when (input){
                1 -> add()
                2 -> update()
                3 -> list()
                4 -> search()
                5 -> delete()
                -99 -> dummyData()
                -1 -> com.github.mm.coloredconsole.println{"Exiting App, Bye Bye!".white.bright}
                else -> com.github.mm.coloredconsole.println{"Invalid Option".yellow.bright}
            }
            println()
        }while (input != -1)
                logger.info { "Shutting Down BookTracker Console App" }

    }

    fun menu(): Int {
        return booktrackerView.menu()
    }

    fun add() {
        var aBook = BooktrackerModel()

        if (booktrackerView.addBookData(aBook)) {
            booktrackers.create(aBook)
            com.github.mm.coloredconsole.println { "Book Added".yellow.bright }
        }
        else{
            com.github.mm.coloredconsole.println{"Book Not Added".yellow.bright}
            logger.info("Book not Added")}

    }

    fun list() {
        booktrackerView.listBooks(booktrackers)
    }

    fun update() {
        booktrackerView.listBooks(booktrackers)
        var searchId = booktrackerView.getId()
        val aBook = search(searchId)

        if (aBook != null) {
            if (booktrackerView.updateBookData(aBook)) {
                booktrackers.update(aBook)
                booktrackerView.showBook(aBook)
                com.github.mm.coloredconsole.println{"Book Updated...".yellow.bright}
                logger.info("Book Updated : $aBook")
            } else
            {
                com.github.mm.coloredconsole.println{"Book Not Updated...".yellow.bright}
                logger.info("Book Not Updated")}
        }
        else println("Book not Updated")
    }

    fun search() {
        val aBook = search(booktrackerView.getId())
        if (aBook != null) {
            booktrackerView.showBook(aBook)
        }
        else
            print("This id is not valid")
    }

    fun search(id: Long): BooktrackerModel? {
        var foundBook = booktrackers.findOne(id)
        return foundBook
    }
    fun delete() {
        booktrackerView.listBooks(booktrackers)
        var searchId = booktrackerView.getId()
        val aBook = search(searchId)

        if(aBook != null) {
            booktrackers.delete(aBook)
            com.github.mm.coloredconsole.println{"Book Deleted...".yellow.bright}
            booktrackerView.listBooks(booktrackers)
        }
        else
            com.github.mm.coloredconsole.println{"Book Not Deleted...".yellow.bright}
    }
    fun dummyData(){
        booktrackers.create(BooktrackerModel(title ="Pride and Prejudice", author = "Jane Austen", isbn = 129456,comments = "Wonderful"))
        booktrackers.create(BooktrackerModel(title ="Lord of the Rings", author = "J.R.R. Tolkien",  isbn = 124156, comments =  "My favourite book"))
        booktrackers.create(BooktrackerModel(title ="The Secret", author = "Rhonda Byrne",  isbn = 124906, comments =  "Good book"))

    }
}
