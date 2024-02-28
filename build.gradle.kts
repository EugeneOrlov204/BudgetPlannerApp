// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version Dependencies.androidGradlePlugin apply false
    id("org.jetbrains.kotlin.android") version Dependencies.kotlin apply false
    id("com.google.gms.google-services") version Dependencies.googleServices apply false
}