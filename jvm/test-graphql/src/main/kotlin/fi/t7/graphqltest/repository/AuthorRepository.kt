package fi.t7.graphqltest.repository

import fi.t7.graphqltest.type.Author

interface AuthorRepository {
    abstract fun findById(id: Int): Author

}
