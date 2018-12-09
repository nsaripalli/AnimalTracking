package view

import controller.Observer
import controller.Sighting
import controller.sightingList
import tornadofx.*

private val Sighting.commonName get() = this.species.commonName
private val Sighting.name get() = this.observer.name
private val Sighting.watchName get() = this.watch?.watchID

class Sightings(private val user: Observer) : View("Sightings") {


    override val root = hbox {
        style {
            backgroundColor = multi(javafx.scene.paint.Paint.valueOf("#c9daf8ff"))
        }
        vbox {
            hbox {
                if (user.isScientist) {
                    button("Create Sighting") {
                        addClass(MyStyle.regularFont)
                        action {
                            replaceWith<CreateSighting>()
                        }
                    }
                }
            }

            // select * from watch table query
            tableview(sightingList().observable()) {
                addClass(MyStyle.regularFont)
                readonlyColumn("ID", Sighting::sightingID)
                readonlyColumn("Date", Sighting::date)
                readonlyColumn("Latitude", Sighting::latitude)
                readonlyColumn("Longitude", Sighting::longitude)
                readonlyColumn("Species", Sighting::commonName)
                readonlyColumn("Notes", Sighting::notes)
                readonlyColumn("Observer", Sighting::name)
                readonlyColumn("Watch ID", Sighting::watchName)
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
