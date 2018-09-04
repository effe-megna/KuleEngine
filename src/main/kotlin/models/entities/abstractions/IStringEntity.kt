package models.entities.abstractions

import models.rows.abstractions.IEntityRow

interface IStringEntity : IEntity {
    val row: IEntityRow
}