package com.anddani.repository.smt

import com.anddani.SmtDb
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SmtRepositoryImpl @Inject constructor(
    private val smtDb: SmtDb,
) : SmtRepository {

    override fun insertDemonsWithSkills(demonWithSkills: List<DemonWithSkill>) {
        smtDb.transaction {
            for (demonAndSkill in demonWithSkills) {
                smtDb.demonSkillQueries.insertDemonWithSkill(
                    demon_name = demonAndSkill.demonName,
                    skill_name = demonAndSkill.skillName,
                    skill_cost = demonAndSkill.skillCost,
                )
            }
        }
    }

    override fun selectDemonsWithName(query: String): List<SelectWithName> {
        return smtDb.demonSkillQueries.selectWithName(query)
            .executeAsList()
    }
}
