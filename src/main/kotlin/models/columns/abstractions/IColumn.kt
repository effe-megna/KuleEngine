package models.columns.abstractions

interface IColumn {
    val name: String
    val type: ColumnType
    val description: String

    enum class ColumnType {
        Key,
        String,
        Numeric,
        Boolean
    }
}