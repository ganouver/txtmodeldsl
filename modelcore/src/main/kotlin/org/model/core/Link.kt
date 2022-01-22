package org.model.core

import org.model.core.Item
import org.model.sm.Need
import org.model.sm.Role
import org.model.sm.System
import org.model.sm.Usecase

class Link (val from : Item,
            val to : Item,
            val label : String = "",
            val stereotype : LinkStereotypes = LinkStereotypes.default){
}

enum class LinkStereotypes {
    default,
    use,   // role uses systems
    needs, // role has needs
    access,// system accesses systems
    depends,
    inheritance, // Item inherited from Item
    participate  // Role participates in usecase scenario
}

enum class lineTypes {
    SOLID, DASHED, DOTTED
}

