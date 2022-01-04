package org.model

import java.io.PrintWriter

open class Item (
    val targetModel : Model,
    val ID : String,
    val Label : String,
    val Description : String = "") {

    fun render(out: PrintWriter) {
        out.println("rectangle $ID [")
        if (Label.isNotBlank())
            out.println("**$Label**")
        if (Description.isNotBlank())
            out.println("//$Description//")
        out.println("]")
    }

}