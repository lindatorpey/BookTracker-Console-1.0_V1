package org.wit.bookmark.console.main.models

interface BooktrackerStore {
    fun findAll(): List<BooktrackerModel>
    fun findOne(id: Long) : BooktrackerModel?
    fun create(book: BooktrackerModel)
    fun update(book: BooktrackerModel)
}