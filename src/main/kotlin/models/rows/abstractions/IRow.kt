package models.rows.abstractions

import models.cells.abstractions.ICell

interface IRow {
    val index: Int
    val cells: List<ICell>
}

