package view

import tornadofx.*

class Home: View("Home") {
    override val root = vbox {
        hbox {
            label("Welcome to Animal Tracking, "
            //TODO("insert users name")

            )
            useMaxWidth = true
        }

        hbox {
            button("View Watches") {
                action {
                    replaceWith<Watches>()
                }
            }

            button("View Sightings") {
                action {
                    replaceWith<Sightings>()
                }
            }
        }

        button("Back") {
            action {
                replaceWith<UserLogin>()
            }
        }
    }
}