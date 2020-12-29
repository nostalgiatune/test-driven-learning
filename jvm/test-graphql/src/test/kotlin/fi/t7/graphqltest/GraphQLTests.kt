package fi.t7.graphqltest

import com.fasterxml.jackson.databind.ObjectMapper
import com.graphql.spring.boot.test.GraphQLTest
import com.graphql.spring.boot.test.GraphQLTestTemplate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.ComponentScan


@GraphQLTest
@ComponentScan(basePackages = [
    "fi.t7.graphqltest.repository",
    "fi.t7.graphqltest.resolver"
])
class GraphQLTests {

    @Autowired
    lateinit var template: GraphQLTestTemplate

    @BeforeEach
    fun setUp() {
    }

    @Test
    fun givenGraphQLClient_whenBooks_thenReturnSuccess() {
        val response = template.postForResource("graphql/test-books.graphql")
        assertThat(response.isOk).isTrue
    }

    @Test
    fun givenGraphQLClient_whenNewBook_thenReturnBook() {

        val vars = ObjectMapper().createObjectNode()
        vars.put("name", "Hello")
        vars.put("authorId", "1")

        val response = template.perform("graphql/test-new-book.graphql", vars)
        assertThat(response.isOk).isTrue
        assertThat(response.get("$.data.newBook.id")).isNotNull
    }
}