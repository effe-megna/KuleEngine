package models.entities

import models.columns.abstractions.IColumn
import models.entities.abstractions.IEntity
import models.entities.abstractions.IEntityRowListener
import models.entities.abstractions.IStringEntity
import models.rows.abstractions.IEntityRow

class StringEntity private constructor(
        override val name: String,
        override val description: String,
        override val columns: List<IColumn>,
        override val row: IEntityRow,
        override var rowListener: IEntityRowListener? = null
) : IStringEntity {
    override val rowSelected: IEntityRow?
        get() = if (row.isSelected) row else null

    override fun getRowByKey(key: String): IEntityRow? = if (row.key == key) row else null

    override fun deselectRows() {
        if (row.isSelected) {
            row.apply {
                isSelected = false

            }
        }
    }

    override val type: IEntity.EntityType = IEntity.EntityType.String

    class Builder {
        var name: String? = null
        var description: String? = null
        var columns: List<IColumn>? = null
        var row: IEntityRow? = null

        fun withName(name: String): Builder = apply { this.name = name }

        fun withDescription(description: String): Builder = apply { this.description = description }

        fun withColumns(columns: List<IColumn>): Builder {
            if (columns.count { it.type == IColumn.ColumnType.Key }  != 1)
                throw IllegalStateException("An entity must be have one key column")

            return apply {
                this.columns = columns
            }
        }

        fun withRow(row: IEntityRow): Builder {
            if (row.columns.count { it.type == IColumn.ColumnType.Key }  != 1)
                throw IllegalStateException("A row must be have one key column")

            return apply {
                this.row = row
            }
        }

        fun build(): StringEntity {
            return StringEntity(
                    name ?: throw IllegalStateException("name must be initialized using withName method"),
                    description ?: throw IllegalStateException("description must be initialized using withDescription method"),
                    columns ?: throw IllegalStateException("columns must be initialized using withColumns method"),
                    row ?: throw IllegalStateException("row must be initialized using using withRows method")
            )
        }
    }
}

