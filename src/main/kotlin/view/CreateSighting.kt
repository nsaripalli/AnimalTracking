package view

import controller.Observer
import controller.Sighting
import controller.insertSighting
import controller.speciesFromSciName
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class CreateSighting(private val user: Observer) : View("My View") {
    private val sciName = SimpleStringProperty()
    private val quantity = SimpleIntegerProperty()
    private val latitude = SimpleDoubleProperty()
    private val longitude = SimpleDoubleProperty()
    private val notes = SimpleStringProperty()

    override val root = form {
        style {
            backgroundColor = multi(javafx.scene.paint.Paint.valueOf("#c9daf8ff"))
        }
        fieldset("Log sighting") {
            addClass(MyStyle.regularFont)
            field("Species Scientific name") {
                addClass(MyStyle.regularFont)
                textfield {
                    bind(sciName)
                    addClass(MyStyle.regularFont)
                }
            }
            field("quantity") {
                addClass(MyStyle.regularFont)
                textfield {
                    bind(quantity)
                    addClass(MyStyle.regularFont)
                }
            }
            field("latitude") {
                addClass(MyStyle.regularFont)
                textfield {
                    bind(latitude)
                    addClass(MyStyle.regularFont)
                }
            }
            field("longitude") {
                addClass(MyStyle.regularFont)
                textfield {
                    bind(longitude)
                    addClass(MyStyle.regularFont)
                }
            }
            field("notes") {
                addClass(MyStyle.regularFont)
                textfield {
                    bind(notes)
                    addClass(MyStyle.regularFont)
                }
            }
            field("photo") {
                addClass(MyStyle.regularFont)
                textfield {
                    addClass(MyStyle.regularFont)
                }
            }

        }
        button("Submit") {
            addClass(MyStyle.regularFont)
            action {
                insertSighting(
                        Sighting(
                                sightingID = null,
                                quantity = quantity.value,
                                latitude = latitude.value,
                                longitude = longitude.value,
                                notes = notes.value,
                                species = speciesFromSciName(sciName.value),
                                observer = user,
                                watch = null,
                                photo = null)
                )

                replaceWith(Home(user))
            }
        }
        button("Home") {
            addClass(MyStyle.regularFont)
            action {
                replaceWith(Home(user))
            }
        }
    }
}
