package org.wit.bookmark.console.main.views


import org.wit.bookmark.console.main.models.BooktrackerJSONStore
import org.wit.bookmark.console.main.models.BooktrackerMemStore
import org.wit.bookmark.console.main.models.BooktrackerModel
import java.lang.NumberFormatException


class BooktrackerView {
    fun menu() : Int {
        var option: Int
        var input: String

        com.github.mm.coloredconsole.println{"Main Menu".cyan.bold}
        com.github.mm.coloredconsole.println{"1. Add a Book".blue.bright}
        com.github.mm.coloredconsole.println{"2. Update a Book".blue.bright}
        com.github.mm.coloredconsole.println{"3. List all Books".blue.bright}
        com.github.mm.coloredconsole.println{"4. Search Books".blue.bright}
        com.github.mm.coloredconsole.println{"5. Delete Books".blue.bright}
        com.github.mm.coloredconsole.println{"-1. Exit".cyan.bright}
        println()
        com.github.mm.coloredconsole. print{"Enter Option : ".cyan.bold}
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }

    fun listBooks(books: BooktrackerJSONStore){
        com.github.mm.coloredconsole.println{"List All Books".cyan.bold}
        println()
        books.logAll()
        println()
    }

    fun showBook(books : BooktrackerModel){
        if (books != null)
            com.github.mm.coloredconsole.println{"Book Details $books".blue.bright}
        else
            com.github.mm.coloredconsole.println{"Book not Found...".yellow.bright}
    }
    fun addBookData(books: BooktrackerModel) : Boolean{
        //var input: String?
        //var num : Int
//        print("Enter an ISBN number : ")
//        books.isbn = readLine()
        //num = input.toInt()
       // print("Enter an ISBN number : ")
        try {
            println()
            com.github.mm.coloredconsole.print{"Enter a Title : ".blue.bright}
            books.title = readLine()!!
            com.github.mm.coloredconsole.print{"Enter an Author : ".blue.bright}
            books.author = readLine()!!
            com.github.mm.coloredconsole.print{"Enter an ISBN number : ".blue.bright}
            books.isbn = readLine()!!.toInt()
        }
        catch(e: NumberFormatException) { // Previously when input is empty is would out NumberFormat Exception, now it catches it and instead of breaking the console menu it outputs the below text to the console
            com.github.mm.coloredconsole.print{"Input must not be empty".yellow.bold.underline}
        }

        return books.title.isNotEmpty() && books.author.isNotEmpty() && books.isbn.toString().isNotEmpty()
    }
    fun updateBookData(books: BooktrackerModel) : Boolean{
        var tempTitle: String?
        var tempAuthor: String?
        var tempIsbn: Int

        if(books != null){
            com.github.mm.coloredconsole.print{"Enter an new book title for ".blue.bright + books.title.blue.bright + " :"}
            tempTitle = readLine()!!
            com.github.mm.coloredconsole.print{"Enter an new author for ".blue.bright + books.author.blue.bright + " :"}
            tempAuthor = readLine()!!
            com.github.mm.coloredconsole.print{"Enter an new Isbn for ".blue.bright + books.isbn.blue.bright + " :"}
            tempIsbn = readLine()!!.toInt()
            if (!tempTitle.isNullOrEmpty() && !tempAuthor.isNullOrEmpty() && !tempIsbn.toString().isNullOrEmpty()){
                books.title = tempTitle
                books.author = tempAuthor
                books.isbn = tempIsbn
                return true
            }
        }
        return false
    }
    fun getId(): Long {
        var strId : String?
        var searchId: Long
        com.github.mm.coloredconsole.print{"Enter id to Search/Update/Delete : ".cyan.bright}
        strId = readLine()!!
        searchId = if(strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9
        return searchId
    }
}

