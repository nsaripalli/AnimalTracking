package view

import javafx.scene.paint.Color
import tornadofx.*

class MyStyle: Stylesheet() {

    companion object {
        val regularFont by cssclass()

    }

    init {
        regularFont {
            fontFamily = "Avenir"
        }
    }
}
