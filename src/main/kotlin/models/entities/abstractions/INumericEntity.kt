package models.entities.abstractions

import models.rows.abstractions.IEntityRow

interface INumericEntity : IEntity {
    val row: IEntityRow
}