package org.model.core


//описывает абстрактный элемент модели
open class Item protected constructor (
    val Label : String,
    val Description : String) {

    protected fun linkTo(target : Item, label : String, type: LinkStereotypes): Link {
        return Link(this, target, label, type)
            .also { Links.addLink(it)  }
    }

    //возвращает исходящие ссылки из элемента
    public fun outgoingLinks() : List<Link> {
        return Links.linksFrom.safeGetListByKey(this)
    }

    //возвращает входящие ссылки
    public fun incomingLinks() : List<Link> {
        return Links.linksTo.safeGetListByKey(this)
    }
}

class Links {
    companion object maps {
        internal val linksFrom = mutableMapOf<Item, MutableList<Link>>()
        internal val linksTo = mutableMapOf<Item, MutableList<Link>>()


        fun addLink(l : Link){
            linksFrom.safeAddValueToMapOfLists(l.from, l)
            linksTo.safeAddValueToMapOfLists(l.to, l)
        }
    }
}

fun <K,V> MutableMap<K, MutableList<V>>.safeAddValueToMapOfLists(key : K, value : V) {
    if (!this.containsKey(key))
        this[key] = mutableListOf<V>(value)
    else
        this[key]?.add(value)

}

fun <K,V> MutableMap<K, MutableList<V>>.safeGetListByKey(key : K) : List<V> {
    if (this.containsKey(key))
        return this[key]!!
    return emptyList()
}



open class Package(Label: String, Description: String) : Item(Label, Description) {

    public final fun children() : List<Item> {
        val c = this.javaClass
        val itemType = Item::class.java

        val ms = c.methods
            .filter {
                it.parameters.isEmpty() &&
                        itemType.isAssignableFrom(it.returnType)
            }
        val vals =  ms.map { it.invoke(this) }

        return vals.filterIsInstance<Item>()
    }

    public inline fun<reified T> childrenOf() : List<T> {
        return  children().filterIsInstance<T>()
    }
}


