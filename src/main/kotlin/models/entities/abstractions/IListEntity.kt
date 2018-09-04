package models.entities.abstractions

import models.rows.abstractions.IEntityRow

interface IListEntity : IEntity {
    val rows: MutableList<IEntityRow>

    fun addRow(row: IEntityRow)
    fun addRows(rows: List<IEntityRow>)
}