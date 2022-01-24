package org.model.views

import org.model.core.LinkStereotypes
import org.model.sm.Usecase
import java.io.PrintWriter

class UsecaseView(name: String, title: String, val cases: List<Usecase>) : PlantUmlView(name, title) {
    override fun renderHeader(header: PrintWriter) {
        with(header) {
            write("left to right direction\n")
        }
        super.renderHeader(header)
    }

    override fun renderElements(elements: PrintWriter) {
        var index = 1
        for (case in cases) {
            elements.write("usecase ${identityOf(case, "usecase")} as \"${case.Label}\"\n\n")
            for (l in case.outgoingLinks().filter { it.stereotype == LinkStereotypes.participant })
            {
                elements.write("actor ${identityOf(l.to, "actor")} as \"${l.to.Label}\"\n\n")
            }
        }
    }

    override fun renderRelations(relations: PrintWriter) {
        for (case in cases) {
            for (l in case.outgoingLinks().filter { it.stereotype == LinkStereotypes.participant })
            {
                relations.write("${identityOf(l.to)} -- ${identityOf(l.from)} \n")
            }
        }
    }

}