package fi.t7.graphqltest.repository

import fi.t7.graphqltest.type.Book

interface BookRepository {
    abstract fun findAll(): List<Book>
    abstract fun newBook(name: String, authorId: Int): Book
}
