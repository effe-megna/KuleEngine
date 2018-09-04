package models.entities

import models.columns.abstractions.IColumn
import models.entities.abstractions.IEntity
import models.entities.abstractions.IEntityListener
import models.entities.abstractions.IEntityRowListener
import models.entities.abstractions.IListEntity
import models.rows.abstractions.IEntityRow

class ListEntity private constructor(
        override val name: String,
        override val description: String,
        override val columns: List<IColumn>,
        override val rows: MutableList<IEntityRow>,
        private val listener: IEntityListener? = null,
        override var rowListener: IEntityRowListener? = null
) : IListEntity {
    override val type: IEntity.EntityType = IEntity.EntityType.List

    override val rowSelected: IEntityRow?
        get() = rows.singleOrNull { it.isSelected }

    override fun getRowByKey(key: String): IEntityRow? = rows.singleOrNull { it.key == key }

    override fun addRow(row: IEntityRow) {
        rows.add(row)
    }

    override fun addRows(rows: List<IEntityRow>) {
        this.rows.addAll(rows)
    }

    override fun deselectRows() {
       rows.forEach {
           if (it.isSelected) {
               it.apply {
                   isSelected = false
                   listener?.deselectedRow(it)
               }
           }
       }
    }

    class Builder {
        var name: String? = null
        var description: String? = null
        var columns: List<IColumn>? = null
        var rows: MutableList<IEntityRow>? = null
        var listener: IEntityListener? = null

        fun withName(name: String): Builder = apply { this.name = name }

        fun withDescription(description: String): Builder = apply { this.description = description }

        fun withColumns(columns: List<IColumn>): Builder {
            if (columns.count { it.type == IColumn.ColumnType.Key } != 1)
                throw IllegalStateException("An entity must be have one key column")

            return apply {
                this.columns = columns
            }
        }

        fun withRows(rows: List<IEntityRow>): Builder {
            rows.forEach { row ->
                if (row.columns.count { it.type == IColumn.ColumnType.Key }  != 1)
                    throw IllegalStateException("The row ${row.key} must be have one key column")
            }

            return apply {
                this.rows = rows.toMutableList()
            }
        }

        fun withEntityListener(listener: IEntityListener) = apply { this.listener = listener }

        fun build(): ListEntity {
            return ListEntity(
                    name ?: throw IllegalStateException("name must be initialized using withName method"),
                    description ?: throw IllegalStateException("description must be initialized using withDescription method"),
                    columns ?: throw IllegalStateException("columns must be initialized using withColumns method"),
                    rows ?: throw IllegalStateException("row must be initialized using using withRows method"),
                    listener
            )
        }
    }
}