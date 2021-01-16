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


class CodeGenerator_initialize(private val mClass: PsiClass, private val editor:Editor) {
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


            mClass.add(elementFactory.createFieldFromText("CleverTapAPI clevertap = CleverTapAPI.getDefaultInstance(getApplicationContext());", mClass))


    }

}