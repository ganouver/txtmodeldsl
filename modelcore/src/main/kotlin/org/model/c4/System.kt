package org.model.sm

import org.model.core.*


open  class C4Item(Label: String, Description: String, val extern: Boolean)
    : Package(Label, Description) {
    fun accessTo(target: C4Item, label: String): Link {
        return linkTo(target, label, LinkStereotypes.access)
    }
}

open class Component(Label: String, Description: String = "", extern: Boolean = false)
    : C4Item(Label, Description, extern)

open class Container(Label: String, Description: String = "", val Technology: String = "", extern: Boolean = false)
    : C4Item(Label, Description, extern)

open class System(Label: String, Description: String = "", extern: Boolean = false)
    : C4Item(Label, Description, extern)

