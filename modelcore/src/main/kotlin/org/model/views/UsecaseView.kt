package org.model.views

import org.model.sm.Usecase
import java.io.PrintWriter

class UsecaseView(name: String, val cases: List<Usecase>) : PlantUmlView(name) {
    override fun renderHeader(header: PrintWriter) {
        header.write("header \n")
    }

    override fun renderElements(elements: PrintWriter) {
        elements.write("elements \n")
    }

    override fun renderRelations(relations: PrintWriter) {
        relations.write("relations")
    }

}