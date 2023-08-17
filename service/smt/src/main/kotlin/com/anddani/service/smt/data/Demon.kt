package com.anddani.service.smt.data

data class Demon(
    val name: String,
    val skills: List<Skill>,
) {
    data class Skill(
        val name: String,
        val cost: Int,
    )
}