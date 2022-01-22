package org.model.sm

import org.model.core.Item
import org.model.core.LinkStereotypes

open class Role(Label: String, Description: String) : Item(Label, Description) {
    fun inherits(role: Role): Role {
        super.linkTo(role, "включает", LinkStereotypes.inheritance)
        return this
    }

}


