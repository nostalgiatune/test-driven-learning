package fi.t7.graphqltest.resolver

import fi.t7.graphqltest.type.Author
import fi.t7.graphqltest.repository.AuthorRepository
import fi.t7.graphqltest.type.Book
import graphql.kickstart.tools.GraphQLResolver
import org.springframework.stereotype.Component

@Component
class BookResolver(private val authorRepo: AuthorRepository) : GraphQLResolver<Book> {

    fun author(book: Book): Author {
        return authorRepo.findById(book.authorId)
    }
}