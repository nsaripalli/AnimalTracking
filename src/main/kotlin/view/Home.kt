package view

import tornadofx.*

class Home: View("Home") {
    override val root = vbox {
        hbox {
            label("Welcome to Animal Tracking"
            //TODO("insert users name")

            ) {
                addClass(MyStyle.regularFont)
            }
            useMaxWidth = true
        }

        hbox {
            button("View Watches") {
                addClass(MyStyle.regularFont)
                action {
                    replaceWith<Watches>()
                }
            }

            button("View Sightings") {
                addClass(MyStyle.regularFont)
                action {
                    replaceWith<Sightings>()
                }
            }
        }

        button("Logout") {
            addClass(MyStyle.regularFont)
            action {
                replaceWith<UserLogin>()
            }
        }
    }
}