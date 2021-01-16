package action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import ui.NewScreenDialog_Clevertap

class SearchAction : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        NewScreenDialog_Clevertap(event).show()
    }
}
