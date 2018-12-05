package view

import javafx.scene.layout.Priority
import javafx.scene.text.FontWeight
import javafx.scene.text.TextAlignment
import tornadofx.*

class Home: View("Home") {
    override val root = vbox {
        style {
            backgroundColor = multi(javafx.scene.paint.Paint.valueOf("#c9daf8ff"))
        }
        hbox {
            vboxConstraints {
                marginTop = 40.0
                marginLeft = 40.0
                marginBottom = 60.0
            }
            hgrow = Priority.ALWAYS
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
            vboxConstraints {
                marginLeft = 130.0
                marginBottom = 40.0
            }
            button("View Watches") {
                addClass(MyStyle.regularFont)
                action {
                    replaceWith<Watches>()
                }
            }

            button("View Sightings") {
                hboxConstraints {
                    marginLeft = 140.0
                }
                addClass(MyStyle.regularFont)
                action {
                    replaceWith<Sightings>()
                }
            }
        }

        button("Logout") {
            vboxConstraints {
                marginLeft = 260.0
            }
            addClass(MyStyle.regularFont)
            action {
                replaceWith<UserLogin>()
            }
        }
    }
}