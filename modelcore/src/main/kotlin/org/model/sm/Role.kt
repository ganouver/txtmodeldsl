package org.model.sm

import org.model.core.ContainerItem
import org.model.core.Item

open class Role (parent: ContainerItem, ID: String, Label: String, Description: String) :
    Item(parent, ID, Label, Description) {}


class Need (parent: ContainerItem, ID: String, Label: String, Description: String) :
Item(parent, ID, Label, Description) {}

