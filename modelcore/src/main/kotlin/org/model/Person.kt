package org.model

class Person(targetModel: Model, ID: String, Label: String, Description: String = "") :
    Item(targetModel, ID, Label, Description) {


}