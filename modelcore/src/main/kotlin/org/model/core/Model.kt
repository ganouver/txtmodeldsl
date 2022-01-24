package org.model.core

import org.model.views.View

open class Model(label : String, Description: String) : Package(label, Description)
{
    fun validate() {
        assert(true)
    }

    open fun buildViews() : List<View>
    {
        return emptyList();
    }
}

