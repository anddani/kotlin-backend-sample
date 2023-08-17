package com.anddani.repository.smt

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.asJdbcDriver
import com.anddani.SmtDb
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import dagger.Module
import dagger.Provides
import org.testcontainers.containers.GenericContainer
import org.testcontainers.utility.DockerImageName
import javax.inject.Singleton

/**
 * This module will set up a local instance of the database using docker
 */
@Module
object SmtDatabaseModule {

    @Provides
    @Singleton
    fun provideHikariConfig(): HikariConfig = HikariConfig().apply {
        // FIXME: In production these should be provided by the environment
        val databaseUsername = "user"
        val databasePassword = "password"
        val databaseName = "smtdb"
        val requestedPort = 5432

        val container = GenericContainer(DockerImageName.parse("postgres:15.4"))
            .withExposedPorts(requestedPort)
            .withEnv(mapOf(
                "POSTGRES_PASSWORD" to databasePassword,
                "POSTGRES_DB" to databaseName,
            ))
        container.start()

        jdbcUrl = "jdbc:postgresql://${container.host}:${container.getMappedPort(requestedPort)}/${databaseName}?user=postgres"
        driverClassName = "org.postgresql.Driver"
        username = databaseUsername
        password = databasePassword
    }

    @Provides
    @Singleton
    fun provideSqlDriver(
        hikariConfig: HikariConfig
    ): SqlDriver = HikariDataSource(hikariConfig).asJdbcDriver()

    @Provides
    @Singleton
    fun provideDatabase(sqlDriver: SqlDriver): SmtDb = SmtDb(sqlDriver)
}
