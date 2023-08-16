package com.anddani.service.smt

import com.anddani.client.smt.data.RemoteDemon
import com.anddani.repository.smt.DemonWithSkill

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