package org.model.sm

import org.model.core.ContainerItem

open class Component constructor (parent : ContainerItem, ID: String, Label: String, val external : Boolean, Description : String) :
        ContainerItem(parent, ID, Label, Description)
{
}


open class Container constructor (parent : ContainerItem, ID: String, Label: String, val technology: String, val external : Boolean, Description : String) :
        ContainerItem(parent, ID, Label, Description)
{
}

open class System constructor (parent : ContainerItem, ID: String, Label: String, val external : Boolean, Description : String) :
        ContainerItem(parent, ID, Label, Description)
{
}