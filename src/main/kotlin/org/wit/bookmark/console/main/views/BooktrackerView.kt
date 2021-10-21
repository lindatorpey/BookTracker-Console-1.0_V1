package org.wit.bookmark.console.main.views


import org.wit.bookmark.console.main.models.BooktrackerJSONStore
import org.wit.bookmark.console.main.models.BooktrackerMemStore
import org.wit.bookmark.console.main.models.BooktrackerModel


class BooktrackerView {
    fun menu() : Int {
        var option: Int
        var input: String

        println("Main Menu")
        println("1. Add a Book")
        println("2. Update a Book")
        println("3. List all Books")
        println("4. Search Books")
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

    fun listBooks(books: BooktrackerJSONStore){
        println("List All Books")
        println()
        books.logAll()
        println()
    }

    fun showBook(books : BooktrackerModel){
        if (books != null)
            println("Book Details $books")
        else
            println("Book not Found...")
    }
    fun addBookData(books: BooktrackerModel) : Boolean{
        println()
        print("Enter a Title : ")
        books.title = readLine()!!
        print("Enter an Author : ")
        books.author = readLine()!!

        return books.title.isNotEmpty() && books.author.isNotEmpty()
    }
    fun updateBookData(books: BooktrackerModel) : Boolean{
        var tempTitle: String?
        var tempAuthor: String?

        if(books != null){
            print("Enter an new book title for " + books.title + " :")
            tempTitle = readLine()!!
            print("Enter an new author for " + books.author + " :")
            tempAuthor = readLine()!!

            if (!tempTitle.isNullOrEmpty() && !tempAuthor.isNullOrEmpty()){
                books.title = tempTitle
                books.author = tempAuthor
                return true
            }
        }
        return false
    }
    fun getId(): Long {
        var strId : String?
        var searchId: Long
        print("Enter id to Search/Update : ")
        strId = readLine()!!
        searchId = if(strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9
        return searchId
    }
}

