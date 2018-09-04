package models.entities.abstractions

import models.rows.abstractions.IEntityRow

interface IBooleanEntity : IEntity {
    val row: IEntityRow
}