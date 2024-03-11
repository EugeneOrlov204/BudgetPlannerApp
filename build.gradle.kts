// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version Dependencies.kotlin apply false
    id("com.google.gms.google-services") version Dependencies.googleServices apply false
    id("com.google.dagger.hilt.android") version Dependencies.hiltVersion apply false
}