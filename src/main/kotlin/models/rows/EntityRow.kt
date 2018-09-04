package models.rows

import models.columns.KeyColumn
import models.cells.abstractions.ICell
import models.columns.abstractions.IColumn
import models.rows.abstractions.IEntityRow

data class EntityRow(
        override val index: Int,
        override val cells: List<ICell>,
        override val columns: List<IColumn>
) : IEntityRow {
    override val key: String = getKeyCell().value as String

    override var isSelected: Boolean = false
        set(value) {
            if (field != value)
                field = value
        }

    override val keyColumn: KeyColumn = columns.single { it.type == IColumn.ColumnType.Key } as KeyColumn

    override fun select() {

    }

    override fun deselect() {

    }

    override fun getKeyCell(): ICell = cells.single { it.isKey }

    override fun toString(): String {
        return "EntityRow(index=$index, cells=$cells, columns=$columns, isSelected=$isSelected, key='$key', keyColumn=$keyColumn)"
    }
}

