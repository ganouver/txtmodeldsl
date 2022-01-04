package org.model

class Link (val from : String,
            val to : String,
            val label : String = "",
            val stereotype : LinkStereotypes = LinkStereotypes.default){
}

enum class LinkStereotypes {
    default,
    use,
    access,
    depends
}

enum class lineTypes {
    SOLID, DASHED, DOTTED
}