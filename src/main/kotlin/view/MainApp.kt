package view

import javafx.stage.Stage
import tornadofx.*

class MainApp: App(UserLogin::class, MyStyle::class) {
    override fun start(stage: Stage) {
        super.start(stage)
        stage.isMaximized = false
        stage.isResizable = false
        stage.width = 600.0
        stage.height = 600.0
    }
    init {
        reloadStylesheetsOnFocus()
    }
}