package view

import javafx.stage.Stage
import tornadofx.App
import tornadofx.reloadStylesheetsOnFocus

class MainApp: App(UserLogin::class, MyStyle::class) {
    override fun start(stage: Stage) {
        super.start(stage)
        stage.isMaximized = false
        stage.isResizable = true
        stage.width = 600.0
        stage.height = 600.0

    }
    init {
        reloadStylesheetsOnFocus()
    }
}
