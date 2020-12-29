package fi.t7.graphqltest.resolver.root

import fi.t7.graphqltest.type.Book
import fi.t7.graphqltest.repository.BookRepository
import graphql.kickstart.tools.GraphQLMutationResolver
import org.springframework.stereotype.Component

@Component
class Mutation(private val bookRepo: BookRepository) : GraphQLMutationResolver {

    fun newBook(name: String, authorId: Int): Book {
        return bookRepo.newBook(name, authorId)
    }

}
