package org.wit.bookmark.console.main.models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging

import org.wit.bookmark.console.main.helpers.*
import java.util.*

private val logger = KotlinLogging.logger{}

val JSON_FILE = "booktracker.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<BooktrackerModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}
class BooktrackerJSONStore : BooktrackerStore{
    var books = mutableListOf<BooktrackerModel>()
    init {
        if(exists(JSON_FILE)){
            deserialize()
        }
    }

    override fun findAll(): MutableList<BooktrackerModel> {
        return books
    }

    override fun findOne(id: Long): BooktrackerModel? {
        var foundBook: BooktrackerModel? = books.find { b -> b.id == id }
        return foundBook
    }

    override fun create(book: BooktrackerModel) {
        book.id = generateRandomId()
        books.add(book)
        serialize()
    }

    override fun update(book: BooktrackerModel) {
        var foundBook = findOne(book.id!!)
        if (foundBook != null){
            foundBook.title = book.title
            foundBook.author = book.author
        }
        serialize()
    }
    fun delete(book: BooktrackerModel){
        books.remove(book)
        serialize()
    }
    internal fun logAll(){
        books.forEach { logger.info ("${it}")}
    }
    private fun serialize(){
        val jsonString = gsonBuilder.toJson(books, listType)
        write(JSON_FILE, jsonString)
    }
    private fun deserialize(){
        val jsonString = read (JSON_FILE)
        books = Gson().fromJson(jsonString, listType)
    }
}

interface BooktrackerStore {
    fun findAll(): List<BooktrackerModel>
    fun findOne(id: Long) : BooktrackerModel?
    fun create(book: BooktrackerModel)
    fun update(book: BooktrackerModel)
}