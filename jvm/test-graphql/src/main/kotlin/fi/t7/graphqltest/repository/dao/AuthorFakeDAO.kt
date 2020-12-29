package fi.t7.graphqltest.repository.dao

import fi.t7.graphqltest.repository.AuthorRepository
import fi.t7.graphqltest.type.Author
import org.springframework.stereotype.Repository

@Repository
class AuthorFakeDAO : AuthorRepository {
    override fun findById(id: Int): Author {
        return Author(id, "test")
    }
}