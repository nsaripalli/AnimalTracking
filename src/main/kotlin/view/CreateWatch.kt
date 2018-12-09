package view

import controller.Observer
import controller.Watch
import controller.insertWatch
import controller.speciesFromSciName
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleStringProperty
import org.joda.time.DateTime
import tornadofx.*
import java.time.LocalDate

class CreateWatch(private val user: Observer) : View("Create Watch Form") {
    private val sciName = SimpleStringProperty()
    private val latitude = SimpleDoubleProperty()
    private val longitude = SimpleDoubleProperty()
    private val radius = SimpleDoubleProperty()
    private val sDate = observable<LocalDate>(propName = "sDate")
    private val eDate = observable<LocalDate>(propName = "eDate")

    override val root = form {
        style {
            backgroundColor = multi(javafx.scene.paint.Paint.valueOf("#c9daf8ff"))
        }
        fieldset("Log Watch") {
            addClass(MyStyle.regularFont)
            field("Species Scientific Name") {
                addClass(MyStyle.regularFont)
                textfield {
                    bind(sciName)
                    addClass(MyStyle.regularFont)
                }
            }
            field("Latitude") {
                addClass(MyStyle.regularFont)
                textfield {
                    bind(latitude)
                    addClass(MyStyle.regularFont)
                }
            }
            field("Longitude") {
                addClass(MyStyle.regularFont)
                textfield {
                    bind(longitude)
                    addClass(MyStyle.regularFont)
                }
            }
            field("Radius") {
                addClass(MyStyle.regularFont)
                textfield {
                    bind(radius)
                    addClass(MyStyle.regularFont)
                }
            }
            field("Start Date") {
                addClass(MyStyle.regularFont)
                datepicker {
                    bind(sDate)
                    addClass(MyStyle.regularFont)
                }
            }
            field("End Date") {
                addClass(MyStyle.regularFont)
                datepicker {
                    bind(eDate)
                    addClass(MyStyle.regularFont)
                }
            }
        }
        button("Submit") {
            addClass(MyStyle.regularFont)
            action {
                insertWatch(
                        Watch(
                                watchID = null,
                                latitude = latitude.value,
                                longitude = longitude.value,
                                radius = radius.value,
                                startDate = DateTime(sDate.value),
                                endDate = DateTime(eDate.value),
                                species = speciesFromSciName(sciName.value),
                                scientist = user
                        )
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
