package fi.t7.graphqltest.repository.dao

import fi.t7.graphqltest.repository.BookRepository
import fi.t7.graphqltest.type.Book
import org.springframework.stereotype.Repository

@Repository
class BookFakeDAO : BookRepository {
    override fun findAll(): List<Book> {
        return listOf(
                Book(1, "test", 1),
                Book(2, "test2", 1)
        )
    }

    override fun newBook(name: String, authorId: Int): Book {
        return Book(1, "Test", authorId)
    }
}