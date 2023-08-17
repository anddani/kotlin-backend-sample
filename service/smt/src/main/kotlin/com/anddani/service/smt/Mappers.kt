package com.anddani.service.smt

import com.anddani.client.smt.data.RemoteDemon
import com.anddani.repository.smt.DemonWithSkill
import com.anddani.repository.smt.SelectWithName
import com.anddani.service.smt.data.Demon

fun Map<String, RemoteDemon>.toDemonWithSkills(): List<DemonWithSkill> =
    entries.flatMap { (demonName: String, remoteDemon: RemoteDemon) ->
        remoteDemon.skills.entries.map { (skillName: String, skillCost: Int) ->
            DemonWithSkill(
                demonName = demonName,
                skillName = skillName,
                skillCost = skillCost,
            )
        }
    }

fun List<SelectWithName>.toDemons(): List<Demon> =
    groupBy { it.demon_id }
        .values
        .map { demonRows ->
            val firstRow = demonRows.first()
            Demon(
                name = firstRow.demon_name,
                skills = demonRows.map { row ->
                    Demon.Skill(
                        name = row.skill_name,
                        cost = row.skill_cost,
                    )
                }
            )
        }