package fi.t7.graphqltest.resolver

import fi.t7.graphqltest.type.Author
import fi.t7.graphqltest.type.Book
import fi.t7.graphqltest.repository.AuthorRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class BookResolverTests {

    @Mock
    lateinit var authorRepo: AuthorRepository

    val author: Author = Author(1, "Test")
    val book: Book = Book(1, "Test", 1)

    lateinit var bookResolver: BookResolver

    @BeforeEach
    fun setUp() {
        Mockito.`when`(authorRepo.findById(book.authorId)).thenReturn(author)

        bookResolver = BookResolver(authorRepo)
    }

    @Test
    fun givenBookResolver_whenAuthor_thenReturnAuthor() {
        val retAuthor: Author = bookResolver.author(book)

        Mockito.verify(authorRepo, Mockito.times(1))
                .findById(book.authorId)

        Assertions.assertThat(retAuthor).isEqualTo(author)
    }
}