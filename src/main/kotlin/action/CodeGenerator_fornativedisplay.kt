package action

import com.intellij.openapi.editor.Editor
import com.intellij.psi.codeStyle.JavaCodeStyleManager
import java.lang.StringBuilder
import java.util.ArrayList

import com.intellij.psi.*
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.util.PsiUtilBase
import com.sun.istack.NotNull


import com.intellij.psi.PsiMethod

import javax.annotation.Nullable


class CodeGenerator_fornativedisplay(private val mClass: PsiClass, private val editor:Editor) {
    fun generate() {
        val elementFactory = JavaPsiFacade.getElementFactory(mClass.project)
        val setContentViewStatement: PsiStatement? = null

        // TODO: remove old accessors
        val methods: MutableList<PsiMethod> = ArrayList()
        val onCreateMethods = mClass.findMethodsByName("onCreate", false)

        if (onCreateMethods.size < 1) {
            return
        }

        val onCreateMethod: PsiMethod = onCreateMethods.get(0)
        val onCreateMethodBody = onCreateMethod.body


            val callevent=elementFactory.createStatementFromText("CleverTapAPI.getDefaultInstance(this).setDisplayUnitListener(this);", mClass)
            onCreateMethodBody?.addAfter(callevent,setContentViewStatement)

        mClass.implementsList!!.add(elementFactory.createKeyword("implements"))
        mClass.implementsList!!.add(elementFactory.createStatementFromText("DisplayUnitListener",mClass))

mClass.add(elementFactory.createAnnotationFromText( "@Override",mClass).add(elementFactory.createMethodFromText(
        "@Override public void onDisplayUnitsLoaded(ArrayList<CleverTapDisplayUnit> units){}",mClass)))


        val styleManager = JavaCodeStyleManager.getInstance(mClass.project)
        for (method in methods) {

            styleManager.shortenClassReferences(mClass.add(method))
        }
    }


    fun getNearbyMethodAtCaretLocation(@NotNull editor: Editor?): PsiMethod? {
        val element = PsiUtilBase.getElementAtCaret(editor!!)
        var method = PsiTreeUtil.getParentOfType(element, PsiMethod::class.java)
        if (method == null) {
            method = PsiTreeUtil.getPrevSiblingOfType(element, PsiMethod::class.java)
            if (method == null) {
                method = PsiTreeUtil.getNextSiblingOfType(element, PsiMethod::class.java)
            }
        }
        return method
    }

    @Nullable
    fun getMethodAtCaretLocation(@NotNull editor: Editor?): PsiMethod? {
        val element = PsiUtilBase.getElementAtCaret(editor!!)
        return PsiTreeUtil.getParentOfType(element, PsiMethod::class.java)
    }
}