package org.wit.bookmark.console.main.models

data class BooktrackerModel(var id: Long = 0,
                            var title: String = "",
                            var author: String = "",
                            var isbn: Int = 0,
                            var comments: String =""

                            )