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


class CodeGenerator_forOnuserlogin(private val mClass: PsiClass, private val editor:Editor) {
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

            val fieldTextBuilder = StringBuilder()
            fieldTextBuilder.append("HashMap<String, Object> UserProperties = new HashMap<String, Object>();")
            val callevent=elementFactory.createStatementFromText("UserProperties.put(\"USer Property_name \", \"value\");", mClass)
        
            mClass.add(elementFactory.createFieldFromText(fieldTextBuilder.toString(), mClass))
         //   mClass.add(elementFactory.createFieldFromText("CleverTapAPI clevertap = CleverTapAPI.getDefaultInstance(getApplicationContext());", mClass))
            onCreateMethodBody?.addAfter(callevent,setContentViewStatement)
            onCreateMethodBody?.add(elementFactory.createStatementFromText("callOnuserLoginMethod(UserProperties);",mClass))
            //cleverTap.pushEvent("Product viewed", prodViewedAction);
           // elementFactory.createMethodFromText("")
         //   onCreateMethodBody?.addAfter(elementFactory.createCommentFromText("Add event properties to hashmap, call the even with desried name",mClass),callevent)

            mClass.add(elementFactory.createMethodFromText("void callOnuserLoginMethod(HashMap<String, Object> userProperties){clevertap.onUserLogin(userProperties);}", mClass))


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