package org.model.core

//описывает абстрактный элемент модели
open class Item protected constructor (
    val Label : String,
    val Description : String) {

    private companion object Links {
        val linksFrom = mutableMapOf<Item, MutableList<Link>>()
        val linksTo = mutableMapOf<Item, MutableList<Link>>()

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

        fun addLink(l : Link){
            linksFrom.safeAddValueToMapOfLists(l.from, l)
            linksTo.safeAddValueToMapOfLists(l.to, l)
        }
    }

    protected fun linkTo(target : Item, label : String, type: LinkStereotypes) {
        addLink(Link(this, target, label, type))
    }

    //возвращает исходящие ссылки из элемента
    public fun outgoingLinks() : List<Link> {
        return linksFrom.safeGetListByKey(this)
    }

    //возвращает входящие ссылки
    public fun incomingLinks() : List<Link> {
        return linksTo.safeGetListByKey(this)
    }


}

open class Package(Label: String, Description: String) : Item(Label, Description) {

    public final fun children() : List<Item> {
        val c =this.javaClass
        return c.fields
            .map { it.get(this) }
            .filterIsInstance<Item>()
    }

    public inline fun<reified T> childrenOf() : List<T> {
        return  children().filterIsInstance<T>()
    }
}


