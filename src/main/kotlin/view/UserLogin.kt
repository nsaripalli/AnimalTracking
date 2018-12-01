package view

import tornadofx.*

class UserLogin: View() {

//    override val root = form {
//        fieldset(labelPosition = Orientation.VERTICAL) {
//            field("Username") { textfield() }
//            field("Password") { passwordfield() }
//            button("Log in") {
//                action { println("Handle button press") }
//            }
//        }
//    }

    // vbox = vertical, hbox = horizontal
    override val root = hbox {
        // new user
        vbox {
            hbox {
                label("User Login Page")
            }
            hbox {
                label("Email")
                textfield()
            }
            hbox {
                label("Full Name:")
                textfield()
            }
            hbox {
                label("Optional Phone Number")
                textfield()
            }
            hbox {
                label("Scientist?")
                checkbox {  }
            }
            button("Create user") {
                useMaxWidth = true
                action {
                    //TODO("validate user")
                    replaceWith<Home>()
                    println("handle creating a user")
                    // should validate this info with database, then store if okay to do.
                }
            }
        }
        // login existing user
        vbox {
            hbox {
                label("Email ")
                textfield()
            }
            button("Login") {
                action {
                    //TODO("handle login")
                    replaceWith<Home>()
                    println("handle logging in")
                }
            }
        }
    }
}

class Home: View() {
    override val root = vbox {
        hbox {
            label("Welcome to Animal Tracking")
        }

        button("Back") {
            action {
                replaceWith<UserLogin>()
            }
        }
    }
}