package fi.t7.graphqltest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class GraphqlTestApplication

fun main(args: Array<String>) {
	runApplication<GraphqlTestApplication>(*args)
}