package models.rows.abstractions

import models.cells.abstractions.ICell
import models.columns.KeyColumn
import models.columns.abstractions.IColumn

interface IEntityRow : IRow {
    val key: String
    val columns: List<IColumn>
    var isSelected: Boolean
    val keyColumn: KeyColumn

    fun select()
    fun deselect()
    fun getKeyCell(): ICell
}