package util

object Constants {

    // gradle
    const val GRADLE_FOR_CT="com.clevertap.android:clevertap-android-sdk:4.0.2"
    const val GRADLE_FOR_FCM = "com.google.firebase:firebase-messaging:"
const val PLAYSTORE="com.google.android.gms:play-services-ads:19.4.0"
    const val Intalrefrerrer="com.android.installreferrer:installreferrer:2.1"
    // Title messages for the studio notification
    const val NOTIFICATION_TITLE = "Clevertap Notification"
    const val NOTIFICATION_CONTENT = "Clevertap has been added successfully"
    const val DISPLAY_ID = "Jidesh Nair"

    // Error Titles
    const val ERROR_TITLE_SYNC_FAILED = "SYNC FAILED"

    // Error Messages
    const val ERROR_BASE_PATH_NOT_FOUND = "Project base path not found."
    const val ERROR_PROJECT_BASE_DIR_NOT_FOUND = "Project base directory not found."
    const val ERROR_BUILD_GRADLE_NOT_FOUND = "Project doesn't contain any gradle file."
    const val ERROR_SYNC_FAILED = "Project sync failed."

    // Commons
    const val DEFAULT_MODULE_NAME = "app"
    const val IMPLEMENTATION = "implementation"
    const val DEPENDENCIES = "dependencies"
    const val APPLICATION = "application"
    const val COLOR_PRIMARY = "colorPrimary"
    const val COLOR_PRIMARY_DARK = "colorPrimaryDark"
    const val FCM_INSTRUCTION = "FCM_Instruction"
    const val FCM_DIRECTORY = "fcm"
    const val FCM_NOTIFICATION = "// Clevertap SDK"
    const val FCM_PANEL = "Clevertap Panel"

    const val COLOR_PRIMARY_FROM_XML = "R.color.colorPrimary"
    const val COLOR_PRIMARY_FROM_ANDROID = "android.R.color.holo_blue_dark"

    // Panel
    const val MY_FIREBASE_MESSAGING_SERVICE_FCM = "MyFirebaseMessagingService"
    const val FCM_SERVICE_NAME_FCM = "FCM Service Name:"
    const val PENDINGINTENT_ACTIVITY_NAME_FCM = "PendingIntent Activity Name:"
    const val ChannelID_Name_FCM = "Channel ID Name(Mandatory)"
    const val CONTENT_TEXT_FCM  = "Content Text:"
    const val NEED_INSTRUCTION_FCM  = "Need Instruction:"
    const val NOTES_INSTRUCTION_FCM  = "Note: If you will check Instruction section, then you will get the instruction text file for the FCM notification."
    const val MY_FIREBASE_MESSAGING_SERVICE = ""
    const val DEPENDENCY_VERSION_VALUE = "4.0.0"
    const val DEPENDENCY_VERSION_VALUE_FCM = "20.2.4"
    const val FCM_SERVICE_NAME = "Account ID: "

    const val PENDINGINTENT_ACTIVITY_NAME = "Account Token:"
    const val CONTENT_TITLE = "Use Google AD ID (yes/no):"
    const val CONTENT_TEXT = "Add Clevertap Application Class(yes/no):"
    const val DEPENDENCY_VERSION = "Dependency Version:"
    const val NEED_INSTRUCTION = "Need Instruction:"
    const val NOTES_INSTRUCTION = "Note: If you will check Instruction section, then you will get the instruction text file for the FCM notification."
}