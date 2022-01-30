package org.model.core

import org.model.views.View

open class Model(label : String, Description: String) : Package(label, Description)
{
    fun validate() {
        assert(true)

        children().forEach() {
            addContainsLinkRecursive(this, it)
        }
    }

    private fun addContainsLinkRecursive(parent: Item, child: Item) {
        parent.contains(child)
        if (child is Package)
            child.children()
                .forEach { addContainsLinkRecursive(child, it) }
    }

    open fun buildViews() : List<View>
    {
        return emptyList()
    }
}

