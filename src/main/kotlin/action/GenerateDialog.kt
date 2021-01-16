package action

import com.intellij.psi.PsiClass
import com.intellij.openapi.ui.DialogWrapper
import javax.swing.JPanel
import com.intellij.ui.CollectionListModel
import com.intellij.psi.PsiField
import javax.swing.JComponent
import com.intellij.psi.PsiModifier
import com.intellij.ui.components.JBList
import com.intellij.ide.util.DefaultPsiElementCellRenderer
import com.intellij.openapi.ui.LabeledComponent
import com.intellij.ui.ToolbarDecorator
import java.util.*


class GenerateDialog(psiClass: PsiClass) : DialogWrapper(psiClass.project) {
    private val myComponent: LabeledComponent<JPanel>
    private val myFields: CollectionListModel<PsiField>
    override fun createCenterPanel(): JComponent? {
        return myComponent
    }

    val selectedFields: List<PsiField>
        get() = myFields.items

    init {
        title = "Sample of event with event properties"
        val allFields = psiClass.allFields

        var fields = arrayOfNulls<PsiField>(allFields.size)
        var i = 0
        for (field in allFields) {
            if (!field.hasModifierProperty(PsiModifier.STATIC)) {

                fields[i++] = field

            }
        }
        fields = Arrays.copyOfRange(fields, 0, i)
        myFields = CollectionListModel<PsiField>(*fields)
        val fieldList: JBList<*> = JBList<Any?>(myFields)
        fieldList.cellRenderer = DefaultPsiElementCellRenderer()
        val decorator = ToolbarDecorator.createDecorator(fieldList)
        decorator.disableAddAction()
        val panel = decorator.createPanel()
        myComponent = LabeledComponent.create(panel, "Example")
        init()
    }
}