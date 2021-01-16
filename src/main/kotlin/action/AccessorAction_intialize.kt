package action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.psi.PsiClass
import action.GenerateDialog
import com.intellij.psi.PsiField
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.psi.PsiFile
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil

class AccessorAction_intialize : AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        val psiClass = getPsiClassFromEvent(event)

        generateAccessors(psiClass, event)

    }

    private fun generateAccessors(psiClass: PsiClass?, event: AnActionEvent) {
        object : WriteCommandAction.Simple<Any?>(psiClass!!.project, psiClass.containingFile) {

            override fun run() {
             //   psiClass?.let { CodeGenerator(it).generate() }
                val editor = event.getData(PlatformDataKeys.EDITOR);
                psiClass.let { CodeGenerator_initialize(it!!,editor!!).generate() }
            }
        }.execute()
    }

    private fun getPsiClassFromEvent(event: AnActionEvent): PsiClass? {
        val psiFile = event.getData(LangDataKeys.PSI_FILE)
        val editor = event.getData(PlatformDataKeys.EDITOR)
        if (psiFile == null || editor == null) {
            return null
        }
        val offset = editor.caretModel.offset
        val element = psiFile.findElementAt(offset)
        return PsiTreeUtil.getParentOfType(element, PsiClass::class.java)
    }
}