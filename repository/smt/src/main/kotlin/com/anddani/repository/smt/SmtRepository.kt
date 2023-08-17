package com.anddani.repository.smt

data class DemonWithSkill(
    val demonName: String,
    val skillName: String,
    val skillCost: Int,
)

interface SmtRepository {
    fun insertDemonsWithSkills(demonWithSkills: List<DemonWithSkill>)
    fun selectDemonsWithName(query: String): List<SelectWithName>
}
