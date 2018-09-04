package models.rows.abstractions

import models.columns.KeyColumn

interface IRuleRow : IRow {
    val columns: List<KeyColumn>
    var isActive: Boolean
}