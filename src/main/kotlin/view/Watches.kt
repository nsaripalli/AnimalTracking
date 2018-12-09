package view

import controller.Observer
import controller.Watch
import controller.watchList
import tornadofx.*

class Watches(private val user: Observer) : View("My View") {

    override val root = hbox {
        style {
            backgroundColor = multi(javafx.scene.paint.Paint.valueOf("#c9daf8ff"))
        }
        vbox {
            hbox {
                if (user.isScientist) {
                    button("Create Watch") {
                        addClass(MyStyle.regularFont)
                        action {
                            replaceWith<CreateWatch>()
                        }
                    }
                }
            }

            tableview(watchList().observable()) {
                addClass(MyStyle.regularFont)
                readonlyColumn("ID", Watch::watchID)
                readonlyColumn("Latitude", Watch::latitude)
                readonlyColumn("Longitude", Watch::longitude)
                readonlyColumn("Radius", Watch::radius)
                readonlyColumn("Start", Watch::startDate)
                readonlyColumn("End", Watch::endDate)
                readonlyColumn("Species", Watch::species)
                readonlyColumn("Scientist", Watch::scientist)
            }
            button("Home") {
                addClass(MyStyle.regularFont)
                action {
                    replaceWith(Home(user))
                }
            }
        }

    }
}
