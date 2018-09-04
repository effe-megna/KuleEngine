package models.rows

import models.cells.abstractions.ICell
import models.columns.KeyColumn
import models.rows.abstractions.IRuleRow

data class RuleRow(
        override val index: Int,
        override val cells: List<ICell>,
        override val columns: List<KeyColumn>,
        override var isActive: Boolean
) : IRuleRow {

    override fun toString(): String {
        return "RuleRow(index=$index, cells=$cells, columns=$columns, isActive=$isActive)"
    }
}