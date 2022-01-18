package org.model

import org.model.core.Item
import org.model.sm.Need
import org.model.sm.Role
import org.model.sm.System
import org.model.sm.Usecase

class Link (val from : String,
            val to : String,
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

fun Item.inherit(target : Item)
{
    this.parent!!.addLink(this, target, "", LinkStereotypes.inheritance)
}

fun Role.use(target : System, Label : String = "")
{
    this.parent!!.addLink(this, target, Label, LinkStereotypes.use)
}

fun Role.act(target : Usecase, Label : String = "") : Role
{
    this.parent!!.addLink(this, target, Label, LinkStereotypes.participate)
    return this
}


fun Role.need(ID: String, Label: String, Description: String = "") : Role {
    val n = Need(this.parent!!, ID, Label, Description)
    this.parent!!.addLink(this, n, "needs", LinkStereotypes.needs)

    function(n)

    return this
}

fun System.accessTo(target : System, Label : String = "")
{
    this.parent!!.addLink(this, target, Label, LinkStereotypes.access)
}