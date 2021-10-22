package org.wit.bookmark.console.main.models

import mu.KotlinLogging

private val logger = KotlinLogging.logger{}
var lastId =0L

internal fun getId() : Long {
    return lastId++
}

class BooktrackerMemStore : BooktrackerStore {
    val books = ArrayList<BooktrackerModel>()

    override fun findAll(): List<BooktrackerModel> {
        return books
    }

    override fun findOne(id: Long): BooktrackerModel? {
        var foundBook: BooktrackerModel? = books.find { p-> p.id == id}
        return foundBook
    }

    override fun create(book: BooktrackerModel) {
        book.id = getId()
        books.add(book)
        logAll()
    }

    override fun update(book: BooktrackerModel) {
        var foundBook = findOne(book.id!!)
        if(foundBook != null){
            foundBook.title = book.title
            foundBook.author = book.author
        }
    }
    fun delete(book: BooktrackerModel ) {
        books.remove(book)
    }
    internal fun logAll(){
        books.forEach { logger.info("${it}")}
    }
}