package fi.t7.graphqltest.resolver.root

import fi.t7.graphqltest.type.Book
import fi.t7.graphqltest.repository.BookRepository
import graphql.kickstart.tools.GraphQLQueryResolver
import org.springframework.stereotype.Component

@Component
class Query(private val bookRepo: BookRepository) : GraphQLQueryResolver {

    fun books(): List<Book> {
        return bookRepo.findAll()
    }

}
