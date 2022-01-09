package org.model.core

open class Model(ID : String, label : String) : ContainerItem(root, ID, label, "")
{
    fun validate() {
        assert(true)
    }
}

