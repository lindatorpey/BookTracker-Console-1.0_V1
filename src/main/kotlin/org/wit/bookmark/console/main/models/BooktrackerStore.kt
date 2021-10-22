import org.wit.bookmark.console.main.models.BooktrackerModel

interface BooktrackerStore {
    fun findAll(): List<BooktrackerModel>
    fun findOne(id: Long): BooktrackerModel?
    fun create(booktracker: BooktrackerModel)
    fun update(booktracker: BooktrackerModel)
    fun delete(booktracker: BooktrackerModel)
}