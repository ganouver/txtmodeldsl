package org.model.core

class Link (val from : Item,
            val to : Item,
            val label : String = "",
            val stereotype : LinkStereotypes = LinkStereotypes.default){
}

enum class LinkStereotypes {
    default,
    usage,   // role uses systems
    needs, // role has needs
    access,// system accesses systems
    depends,
    // Item inherited from Item, renders as --|>
    inheritance,
    // from usecase to participant roles, renders as --
    participant,
    //реализует  --> : <<realize>>
    realize
}

enum class lineTypes {
    SOLID, DASHED, DOTTED
}

