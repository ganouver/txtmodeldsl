package org.model.core

import org.model.sm.Role

open class Model(ID : String, label : String) : Package(root, ID, label, "")
{
    protected fun story(role: Role, action: String, goal: String) {
        TODO("Not yet implemented")
    }

    fun validate() {
        assert(true)
    }
}

