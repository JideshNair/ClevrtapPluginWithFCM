package util

import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import util.Constants.FCM_DIRECTORY

object Methods {

    fun checkPrimaryColorInColorsFile(project: Project): Boolean {
        val basePath = project.basePath
        val projectBaseDir = LocalFileSystem.getInstance().findFileByPath(basePath!!)
        val colorsVirtualFile: VirtualFile? = projectBaseDir!!
            .findChild(Constants.DEFAULT_MODULE_NAME)!!
            .findChild("src")!!
            .findChild("main")!!
            .findChild("res")!!
            .findChild("values")!!
            .findChild("colors.xml")
        return if (colorsVirtualFile != null) {
            val colorsXML = FileDocumentManager.getInstance().getDocument(colorsVirtualFile)
            val documentText = colorsXML!!.text.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val colorPrimary = documentText.indices.filter {
                (documentText[it].contains(Constants.COLOR_PRIMARY) && !documentText[it].contains(Constants.COLOR_PRIMARY_DARK))
            }
            colorPrimary.isNotEmpty()
        } else {
            false
        }
    }


    fun getAndroidManifestContent(
        packageName: String,
        serviceNameText: String,
        pendingIntentText: String,
        contentTitleText: String,
        contentTextText: String
    ): String {
        return "        <meta-data\n" +
                "            android:name=\"CLEVERTAP_ACCOUNT_ID\"\n" +
                "            android:value=\"$serviceNameText\" />\n" +
                "        \n" +
                "        <meta-data\n" +
                "            android:name=\"CLEVERTAP_TOKEN\"\n" +
                "            android:value=\"$pendingIntentText\" />\n"+
                "        <meta-data\n" +
                "            android:name=\"CLEVERTAP_USE_GOOGLE_AD_ID\"\n" +
                "            android:value=\"$contentTitleText\" />\n"

    }

    fun getFileContent(
        packageName: String,
        serviceNameText: String,
        pendingIntentText: String,
        contentTitleText: String,
        contentTextText: String,
        color: String
    ): String {
        return "package $packageName.$FCM_DIRECTORY;\n\n" +
                "\n" +
                "import android.app.NotificationManager;\n" +
                "\n" +
                "import android.os.Bundle;\n" +
                "\n" +
                "import androidx.annotation.NonNull;\n" +
                "\n" +
                "import android.util.Log;\n" +
                "\n" +
                "import com.clevertap.android.sdk.CleverTapAPI;\n" +
                "\n" +
                "\n" +
                "import com.clevertap.android.sdk.pushnotification.NotificationInfo;\n" +
                "import com.google.firebase.messaging.FirebaseMessagingService;\n" +
                "import com.google.firebase.messaging.RemoteMessage;\n" +
                "\n" +
                "import java.util.Map;\n" +
                "\n"+
                "//******************* PLEASE MAKE SURE TO ADD THE GOOGLE_SERVICES.JSON FILE IN TO THE PROJECT DIRECTORY***************//\n" +
                "public class $serviceNameText extends FirebaseMessagingService {\n" +
                "\n" +
                "\tCleverTapAPI clevertapDefaultInstance;\n" +
                "\t@Override\n" +
                "\tpublic void onMessageReceived(RemoteMessage remoteMessage) {\n" +
                "\t\tsuper.onMessageReceived(remoteMessage);\n" +
                "\n" +
                "\t\tRemoteMessage.Notification notification = remoteMessage.getNotification();\n" +
                "\t\ttry {\n" +
                "\t\t\tif (remoteMessage.getData().size() > 0) {\n" +
                "\t\t\t\tBundle extras = new Bundle();\n" +
                "\t\t\t\tfor (Map.Entry<String, String> entry : remoteMessage.getData().entrySet()) {\n" +
                "\t\t\t\t\textras.putString(entry.getKey(), entry.getValue());\n" +
                "\t\t\t\t\tLog.d(\"key,value\", entry.getKey()+\" and \"+entry.getValue());\n" +
                "\t\t\t\t}\n" +
                "\n" +
                "\t\t\t\tNotificationInfo info = CleverTapAPI.getNotificationInfo(extras);\n" +
                "\n" +
                "\n" +
                "\t\t\t\tif (info.fromCleverTap) {\n" +
                "\n" +
                "\t\t\t\t\t\tCleverTapAPI.createNotification(getApplicationContext(), extras);\n" +
                "\n" +
                "\t\t\t\t} else {\n" +
                "\t\t\t\t\tMap<String, String> data = remoteMessage.getData();\n" +
                "\t\t\t\t\tLog.d(\"FROM\", remoteMessage.getFrom());\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}\n" +
                "\t\t} catch (Throwable t) {\n" +
                "\t\t\tLog.d(\"MYFCMLIST\", \"Error parsing FCM message\", t);\n" +
                "\t\t}\n" +
                "\n" +
                "\n" +
                "\t}\n" +
                "\n" +
                "\t@Override\n" +
                "\tpublic void onNewToken(@NonNull String s) {\n" +
                "\t\tsuper.onNewToken(s);\n" +
                "\t\tclevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());\n" +
                "\t\tclevertapDefaultInstance.pushFcmRegistrationId(s,true);\n" +
                "\t\tCleverTapAPI.createNotificationChannel(this,\"$contentTextText\",\"$contentTextText\",\"Channel for Push in App\", NotificationManager.IMPORTANCE_HIGH,true);\n" +
                "\t}\n" +
                "\n" +
                "\n" +
                "}"

    }



    fun getAndroidManifestContent(     packageName: String,
                                       serviceNameText: String
    ): String {
        return "\n        <service\n" +
                "            android:name=\".fcm"+".$serviceNameText\"\n" +
                "            android:exported=\"false\">\n" +
                "            <intent-filter>\n" +
                "                <action android:name=\"com.google.firebase.MESSAGING_EVENT\" />\n" +
                "            </intent-filter>\n" +
                "        </service>\n" +
                "        \n" +
                "        <meta-data\n" +
                "            android:name=\"firebase_messaging_auto_init_enabled\"\n" +
                "            android:value=\"false\" />\n" +
                "        \n" +
                "        <meta-data\n" +
                "            android:name=\"firebase_analytics_collection_enabled\"\n" +
                "            android:value=\"false\" />\n"
    }
}