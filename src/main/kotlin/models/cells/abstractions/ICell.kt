package models.cells.abstractions

interface ICell {
    val rowIndex: Int
    val columnName: String
    var value: Any
    val isKey: Boolean
}