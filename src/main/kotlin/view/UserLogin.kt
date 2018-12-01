package view

import tornadofx.*


//class UserLogin : View() {
//    override val root = vbox {
//        button("Press me")
//        label("Waiting")
//    }
//}


class UserLogin: View() {
    override val root = vbox {
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
        button("Login") {
            useMaxWidth = true
            action {
                replaceWith<Home>()
            }
        }
    }
}

class Home: View() {
    override val root = vbox {
        button("Back") {
            action {
                replaceWith<UserLogin>()
            }
        }
    }
}