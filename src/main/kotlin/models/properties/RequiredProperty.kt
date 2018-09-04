package models.properties

import models.properties.abstractions.Property

data class RequiredProperty(
        override val isActive: Boolean = false
) : Property {
    override val type: Property.PropertyType = Property.PropertyType.Required

    companion object {
        fun newInstance(): RequiredProperty {
            return RequiredProperty()
        }
    }

    override fun toString(): String {
        return "RequiredProperty(isActive=$isActive, type=$type)"
    }
}

