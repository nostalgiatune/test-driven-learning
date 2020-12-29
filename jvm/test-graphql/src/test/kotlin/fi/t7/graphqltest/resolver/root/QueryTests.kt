package fi.t7.graphqltest.resolver.root

import fi.t7.graphqltest.type.Author
import fi.t7.graphqltest.type.Book
import fi.t7.graphqltest.repository.BookRepository
import fi.t7.graphqltest.resolver.root.Query
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class QueryTests {

    @Mock
    lateinit var bookRepo: BookRepository

    val author1: Author = Author(1, "Test")
    val author2: Author = Author(2, "Test2")

    val bookByAuthor1: Book = Book(1, "Test", 1)
    val anotherBookByAuthor1: Book = Book(2, "Test2", 1)
    val bookByAuthor2: Book = Book(3, "Test3", 2)

    lateinit var books: List<Book>

    lateinit var query: Query

    @BeforeEach
    fun setUp() {

        books = listOf(bookByAuthor1, anotherBookByAuthor1, bookByAuthor2)

        Mockito.`when`(bookRepo.findAll()).thenReturn(books)

        query = Query(bookRepo)
    }

    @Test
    fun givenBookResolver_whenAuthor_thenReturnAuthor() {
        val retBooks: List<Book> = query.books()

        Mockito.verify(bookRepo, Mockito.times(1))
                .findAll()

        Assertions.assertThat(retBooks).isEqualTo(books)
    }
}