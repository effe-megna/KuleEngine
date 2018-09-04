package models.columns

import models.columns.abstractions.IColumn

data class NumericColumn(
        override val name: String,
        override val description: String
) : IColumn {
    override val type: IColumn.ColumnType = IColumn.ColumnType.Numeric

    override fun toString(): String {
        return "NumericColumn(name='$name', description='$description', type=$type)"
    }
}