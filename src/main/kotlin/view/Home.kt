package view

import javafx.scene.text.FontWeight
import tornadofx.*

class Home: View("Home") {
    override val root = vbox {
        style {
            backgroundColor = multi(javafx.scene.paint.Paint.valueOf("#c9daf8ff"))
        }
        hbox {
            label("Welcome to Animal Tracking"
            //TODO("insert users name")

            ) {
                style {
                fontWeight = FontWeight.EXTRA_BOLD
                fontSize = 38.px
                //TODO("change font to avenir")
                addClass(MyStyle.regularFont)
                // somehow increase font?
                }
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