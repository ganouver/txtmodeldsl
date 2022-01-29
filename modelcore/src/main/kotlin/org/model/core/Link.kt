package org.model.core

class Link (val from : Item,
            val to : Item,
            val label : String = "",
            val stereotype : LinkStereotypes = LinkStereotypes.default){

    var technology : String? = null
    private val attributes = mutableMapOf<String,String>()
    fun byTechnology(technology : String): Link {
        assert(this.technology == null)
        this.technology = technology

        return this
    }

    fun set(key: String, value: String): Link {
        attributes[key] = value
        return this
    }

    fun Attributes() : Map<String,String> {
        return attributes
    }
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

