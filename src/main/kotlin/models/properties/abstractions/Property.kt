package models.properties.abstractions

interface Property {
    val type: PropertyType
    val isActive: Boolean

    enum class PropertyType {
        Required,
        AutoSelect
    }
}