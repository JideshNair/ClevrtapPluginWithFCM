package data.file


import com.intellij.lang.html.HTMLLanguage
import com.intellij.lang.java.JavaLanguage
import com.intellij.openapi.fileTypes.StdFileTypes.JAVA
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiFileFactory
import model.FileType
import org.jetbrains.kotlin.idea.KotlinLanguage
import com.intellij.openapi.command.WriteCommandAction





interface Directory {
    fun findSubdirectory(name: String): Directory?
    fun createSubdirectory(name: String): Directory
    fun addFile(file: File)
}

class DirectoryImpl(private val project: Project,
                    private val psiDirectory: PsiDirectory) : Directory {

    override fun findSubdirectory(name: String) = psiDirectory.findSubdirectory(name)?.let { DirectoryImpl(project, it) }

    override fun createSubdirectory(name: String) = DirectoryImpl(project, psiDirectory.createSubdirectory(name))

    override fun addFile(file: File) {
        val language = when(file.fileType) {
            FileType.JAVA -> JavaLanguage.INSTANCE
            FileType.TEXT -> HTMLLanguage.INSTANCE
         //   FileType.KOTLIN->KotlinLanguage.INSTANCE

        }
        WriteCommandAction.runWriteCommandAction(project) {
            val psiFile = PsiFileFactory.getInstance(project).createFileFromText("${file.name}.${file.fileType.extension}", language, file.content)
            psiDirectory.add(psiFile)
        }

    }
}
