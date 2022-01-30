package org.model.views

import org.model.core.Item
import org.model.core.Link
import org.model.core.LinkStereotypes
import org.model.sm.Component
import org.model.sm.Container
import org.model.sm.Role
import org.model.sm.System
import java.io.PrintWriter

class ServiceStructureView(name: String, title: String,
                           items: List<Item>) : PlantUmlView(name, title)
{
    val tree = DiagramTreeBuilder()
    init {
        items.forEach { tree.put(it) }
    }

    override fun renderHeader(header: PrintWriter) {
        super.renderHeader(header)

        header.println("!include https://raw.githubusercontent.com/plantuml-stdlib/C4-PlantUML/master/C4_Component.puml")

        header.println("SHOW_PERSON_PORTRAIT()")
    }

    override fun renderElements(elements: PrintWriter) {
        tree.rootNodes.forEach { renderNodesRecursive(it, elements)}
    }

    private fun renderNodesRecursive(node: TreeNode, writer: PrintWriter) {
        val item = node.item
        if (node.children.isNotEmpty()) {
            renderContainer(writer, item)
            writer.println("{")
                node.children.forEach { renderNodesRecursive(it, writer) }
            writer.println("}")
        }
        else {
            renderItem(writer, item)
            writer.println("")
        }

    }

    private fun renderContainer(writer: PrintWriter, item: Item) {
        writer.print("System_Boundary(${identityOf(item, "node")}, \"${item.Label}\")")
    }

    private fun renderItem(writer: PrintWriter, item: Item) {
        var elementName = "System"
        var args = ""
        if (item is System)
            with(item) {
                if (extern)
                    elementName = "System_Ext"
            }
        else if (item is Container)
            with(item) {
                if (extern)
                    elementName = "Container_Ext"
                else
                    elementName = "Container"

                args += ", \$techn=\"${this.Technology}\""
            }
        else if (item is Component)
            with(item) {
                if (extern)
                    elementName = "Component_Ext"
                else
                    elementName = "Component"
            }
        else if (item is Role)
            with(item) {
                if (extern)
                    elementName = "Person_Ext"
                else
                    elementName = "Person"
            }

        writer.print("$elementName( ${identityOf(item, "node")},\"${item.Label}\" $args)")
    }

    override fun renderRelations(relations: PrintWriter) {
        val links = tree.allnodes.keys
            .flatMap { item ->
                item.incomingLinks()
                    .union(item.outgoingLinks())
                    .filter {
                        it.stereotype != LinkStereotypes.contains &&
                        tree.allnodes.containsKey(it.from) &&
                        tree.allnodes.containsKey(it.to)
                    }
            }
            .distinct()
            .toSet()

        links.forEach {
            renderLink(it, relations)
        }

    }

    private fun renderLink(link: Link, relations: PrintWriter) {
        with (relations) {
            print("${identityOf(link.from, "node")} ==> ${identityOf(link.to, "node")} ")
            var lbl = link.label
            if (!link.technology.isNullOrEmpty())
                lbl += "\\n//[${link.technology}]//"
            link.Attributes().forEach {
                lbl += "\\n| ${it.key} | ${it.value} |"
            }

            if (lbl.isNotEmpty())
                print(" : $lbl")
            println()
        }
    }
}