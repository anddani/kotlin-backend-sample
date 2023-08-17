package com.anddani.repository.smt

import app.cash.sqldelight.driver.jdbc.asJdbcDriver
import com.anddani.SmtDb
import com.google.common.truth.Truth.assertThat
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.junit.Test
import org.testcontainers.containers.GenericContainer
import org.testcontainers.utility.DockerImageName

class SmtRepositoryTest {

    private val postgresPort = 5432
    private val databaseName = "smtdb"
    private val databaseUsername = "user"
    private val databasePassword = "password"
    private val container = GenericContainer(DockerImageName.parse("postgres:15.4"))
        .withExposedPorts(postgresPort)
        .withReuse(true)
        .withEnv(mapOf(
            "POSTGRES_PASSWORD" to databasePassword,
            "POSTGRES_DB" to databaseName,
        ))

    init {
        container.start()
    }

    private val smtDb: SmtDb = HikariConfig().apply {
        jdbcUrl = "jdbc:postgresql://${container.host}:${container.getMappedPort(postgresPort)}/${databaseName}?user=postgres"
        driverClassName = "org.postgresql.Driver"
        username = databaseUsername
        password = databasePassword
    }
        .let(::HikariDataSource)
        .asJdbcDriver()
        .also { SmtDb.Schema.create(it) }
        .let(SmtDb::invoke)

    private val smtRepository: SmtRepository = SmtRepositoryImpl(smtDb)

    @Test
    fun `inserting demons with skills`() {
        smtRepository.insertDemonsWithSkills(
            demonWithSkills = listOf(
                DemonWithSkill("Decarabia", "Megido", 0),
                DemonWithSkill("Decarabia", "Tetrakarn", 49),
                DemonWithSkill("Demiurge", "Null Mind", 97),
            )
        )

        assertThat(smtDb.demonSkillQueries.selectAll().executeAsList())
            .isEqualTo(listOf(
                SelectAll(demon_name = "Decarabia", skill_name = "Megido", skill_cost = 0),
                SelectAll(demon_name = "Decarabia", skill_name = "Tetrakarn", skill_cost = 49),
                SelectAll(demon_name = "Demiurge", skill_name = "Null Mind", skill_cost = 97),
            ))
    }

    @Test
    fun `inserting existing demons with skills should have no effect`() {
        assertThat(smtDb.demonSkillQueries.selectAll().executeAsList())
            .isEmpty()

        smtRepository.insertDemonsWithSkills(
            demonWithSkills = listOf(
                DemonWithSkill("Decarabia", "Megido", 0),
                DemonWithSkill("Decarabia", "Tetrakarn", 49),
                DemonWithSkill("Demiurge", "Null Mind", 97),
            )
        )

        val afterFirstInsert = smtDb.demonSkillQueries.selectAll().executeAsList()
        assertThat(afterFirstInsert).isNotEmpty()

        smtRepository.insertDemonsWithSkills(
            demonWithSkills = listOf(
                DemonWithSkill("Decarabia", "Megido", 0),
                DemonWithSkill("Decarabia", "Tetrakarn", 49),
                DemonWithSkill("Demiurge", "Null Mind", 97),
            )
        )

        val afterSecondInsert = smtDb.demonSkillQueries.selectAll().executeAsList()
        assertThat(afterSecondInsert).isEqualTo(afterFirstInsert)
    }
}
