package org.model.core

import org.model.Link
import org.model.LinkStereotypes

//описывает абстрактный элемент модели
open class Item protected constructor (
    val parent : ContainerItem?,
    val ID : String,
    val Label : String,
    val Description : String) {

    init {
        parent?.rememberIt(this)
    }
}


open class ContainerItem protected constructor (parent : ContainerItem?, ID: String, Label: String, Description: String)
    : Item(parent, ID, Label,  Description) {

    companion object root : Package(null, "root", "", "")
    {}

    private val _children: MutableMap<String, Item> = HashMap()
    private val _linksFrom: MutableMap<String, Link> = HashMap()
    private val _linksTo: MutableMap<String, Link> = HashMap()

    fun children(): Collection<Item> {
        return _children.values
    }

    // создает связь между двумя элементами модели
    fun addLink(from: Item, to: Item,
                label : String = "",
                stereotype : LinkStereotypes = LinkStereotypes.default) {
        assert(_children.containsValue(from) && _children.containsValue(to))

        val l = Link(from.ID, to.ID, label, stereotype)
        _linksFrom[from.ID] = l
        _linksTo[to.ID] = l
    }

    internal fun <T : Item> rememberIt(s: T): T {
        _children[s.ID] = s
        return s
    }
}

open class Package(parent : Package?, ID: String, Label: String = "", Description: String = "")
    : ContainerItem(parent, ID, Label, Description)
{
}

