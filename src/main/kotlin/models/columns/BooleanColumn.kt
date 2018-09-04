package models.columns

import models.columns.abstractions.IColumn

data class BooleanColumn(
        override val name: String,
        override val description: String
) : IColumn {
    override val type: IColumn.ColumnType = IColumn.ColumnType.Boolean

    override fun toString(): String {
        return "BooleanColumn(name='$name', description='$description', type=$type)"
    }
}