package models.entities.abstractions

import models.columns.abstractions.IColumn
import models.rows.abstractions.IEntityRow

interface IEntity {
    val name: String
    val type: EntityType
    val description: String
    val columns: List<IColumn>
    val rowSelected: IEntityRow?

    var rowListener: IEntityRowListener?

    fun getRowByKey(key: String): IEntityRow?
    fun deselectRows()

    enum class EntityType {
        List,
        String,
        Numeric,
        Boolean
    }
}

interface IEntityRowListener {
    fun onSelectedRow(row: IEntityRow)
}

interface IEntityListener {
    fun deselectedRow(row: IEntityRow)
}
