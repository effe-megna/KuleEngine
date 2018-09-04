package models.properties

import models.properties.abstractions.Property

data class AutoSelectProperty(
        override val isActive: Boolean = false
) : Property {
    override val type: Property.PropertyType = Property.PropertyType.AutoSelect

    companion object {
        fun newInstance(): AutoSelectProperty {
            return AutoSelectProperty()
        }
    }

    override fun toString(): String {
        return "AutoSelectProperty(isActive=$isActive, type=$type)"
    }
}