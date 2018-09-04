package models.columns

import models.columns.abstractions.IColumn

data class StringColumn(
        override val name: String,
        override val description: String
) : IColumn {
    override val type: IColumn.ColumnType = IColumn.ColumnType.String

    override fun toString(): String {
        return "StringColumn(name='$name', description='$description', type=$type)"
    }
}