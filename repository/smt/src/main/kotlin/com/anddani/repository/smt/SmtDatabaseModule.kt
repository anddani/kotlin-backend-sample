package com.anddani.repository.smt

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.asJdbcDriver
import com.anddani.SmtDb
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object SmtDatabaseModule {

    @Provides
    @Singleton
    fun provideHikariConfig(): HikariConfig = HikariConfig().apply {
        jdbcUrl = System.getenv("SMT_DATABASE_CONNECTION_STRING")
        username = System.getenv("SMT_DATABASE_USERNAME")
        password = System.getenv("SMT_DATABASE_PASSWORD")
        maximumPoolSize = System.getenv("SMT_DATABASE_POOL_SIZE").toInt()
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
