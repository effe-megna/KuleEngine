package models.cells

import models.cells.abstractions.ICell

data class Cell(
        override val rowIndex: Int,
        override val columnName: String,
        override var value: Any,
        override val isKey: Boolean
) : ICell {

    override fun toString(): String {
        return "Cell(rowIndex=$rowIndex, columnName='$columnName', value=$value, isKey=$isKey)"
    }
}
