package org.model.views

import org.model.core.Item
import org.model.core.LinkStereotypes

//дерево узлов диаграммы
class DiagramTreeBuilder {
    //первый уровень дерева
    val rootNodes = mutableListOf<TreeNode>()

    //индекс по всем вершинам дерева
    val allnodes = mutableMapOf<Item, TreeNode>()

    ///////////////
    //добавление узла в иерархию диаграммы
    fun put(item : Item) {
        assert(!allnodes.containsKey(item))

        //инвариант - выстроена какая-то иерархия узлов в соответствии с отношением contains
        //нужно добавить узел в дерево

        val newNode = TreeNode(item)
        //1. Ищем родителя добавляемого узла,
        val parents = item.incomingLinks().filter { it.stereotype == LinkStereotypes.contains }.map {it.from}
        val parentKey = allnodes.keys.intersect(parents.toSet()).firstOrNull()
        //если он есть - то добавляем новый узел к нему в подчинение.
        //если нет - добавляем в корень.

        if (parentKey == null)
            newNode.putUnder(null)
        else
            newNode.putUnder(allnodes[parentKey])
        //после добавления любого узла проверяем
        //если в корне дерева есть его подчиненные,
            // то перевешиваем их под вновь добавленный узел.
        val rootItems = rootNodes.map { it.item }.toSet()
        item.outgoingLinks()
            .filter { it.stereotype == LinkStereotypes.contains } .map {it.to}
            .intersect(rootItems)
            .toList()
            .forEach { allnodes[it]?.moveUnder(newNode) }


    }
    //положить узел под родителя
    private fun TreeNode.putUnder(parent: TreeNode?) {
        if (parent == null)
            rootNodes.add(this)
        else
        {
            parent.children.add(this)
        }
        this.parent = parent
        allnodes[this.item] = this
    }

    //перевесить ветку дерева под нового родителя
    private fun  TreeNode.moveUnder(newParent: TreeNode) {
        if (this.parent != null)
            this.parent!!.children.remove(this)
        else
            rootNodes.remove(this)
        this.parent = null
        this.putUnder(newParent)
    }
}

//вершина дерева
class TreeNode (
    //узел модели, связанный узлом диаграммы
    val item : Item ) {
    //подчиненные узлы
    val children = mutableListOf<TreeNode>()

    //родительская вершина (null - для вершин первого уровня)
    var parent : TreeNode? = null
}
