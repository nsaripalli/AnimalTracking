package view

import tornadofx.*

class Home: View("Home") {

    val watchButton = button("View Watches") {
        action {
            replaceWith<Watches>()
        }
    }

    val sightingButton = button("View Sightings") {
        action {
            replaceWith<Sightings>()
        }
    }

    override val root = vbox {
        hbox {
            label("Welcome to Animal Tracking, "
            //TODO("insert users name")
            )
        }

        hbox {
            watchButton
            sightingButton
        }

        button("Back") {
            action {
                replaceWith<UserLogin>()
            }
        }
    }
}