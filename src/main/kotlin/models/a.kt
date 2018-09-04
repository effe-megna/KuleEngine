package models

import models.entities.abstractions.IEntity
import models.rules.abstractions.IRule

interface Engine {
    val entities: List<IEntity>
    val rules: List<IRule>

    fun getEntity(name: String): IEntity?
    fun getRule(name: String): IRule?
}

class Kengine(
        override val entities: List<IEntity>,
        override val rules: List<IRule>
) : Engine {

    override fun getEntity(name: String): IEntity? = entities.singleOrNull{ it.name == name }

    override fun getRule(name: String): IRule? = rules.singleOrNull { it.name == name }
}