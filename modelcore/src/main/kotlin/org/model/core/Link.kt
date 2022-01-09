package org.model

import org.model.sm.Person
import org.model.sm.System

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

fun Person.use(target : System, Label : String = "")
{
    this.parent!!.addLink(this, target, Label, LinkStereotypes.use)
}

fun System.accessTo(target : System, Label : String = "")
{
    this.parent!!.addLink(this, target, Label, LinkStereotypes.access)
}