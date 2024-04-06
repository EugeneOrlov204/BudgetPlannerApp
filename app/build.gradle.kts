plugins {
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.shpp.budget.planner"
    compileSdk = Dependencies.compileSdk

    defaultConfig {
        applicationId = "com.shpp.budget.planner"
        minSdk = Dependencies.minSdk
        targetSdk = Dependencies.targetSdk
        versionCode = Dependencies.versionCode
        versionName = Dependencies.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.kotlinCompilerExtensionVersion
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:${Dependencies.coreKtx}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Dependencies.lifecycleRuntimeKtx}")

    // Jetpack Compose
    implementation("androidx.activity:activity-compose:${Dependencies.activityCompose}")
    implementation(platform("androidx.compose:compose-bom:${Dependencies.composeBom}"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    //Hilt
    implementation("com.google.dagger:hilt-android:${Dependencies.hiltVersion}")
    kapt("com.google.dagger:hilt-android-compiler:${Dependencies.hiltVersion}")

    //Navigation
    implementation("androidx.navigation:navigation-compose:${Dependencies.navVersion}")

    //Hilt+Navigation
    implementation("androidx.hilt:hilt-navigation-compose:${Dependencies.hiltNavVersion}")

    // JUnit tests
    testImplementation("junit:junit:${Dependencies.junit}")
    androidTestImplementation("androidx.test.ext:junit:${Dependencies.androidJUnit}")

    // Espresso tests
    androidTestImplementation("androidx.test.espresso:espresso-core:${Dependencies.espressoCore}")

    // Jetpack Compose tests
    androidTestImplementation(platform("androidx.compose:compose-bom:${Dependencies.composeBom}"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    // Firebase
    // Import the Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:${Dependencies.firebaseBom}"))
    // Firebase Analytics
    implementation("com.google.firebase:firebase-analytics")
    // Firebase Auth
    implementation("com.google.firebase:firebase-auth:${Dependencies.firebaseAuth}")

    // Material Icons extended
    implementation("androidx.compose.material:material-icons-extended:${Dependencies.materialIconsExtended}")

    //Polygons
    implementation("androidx.graphics:graphics-shapes:${Dependencies.shapesVersion}")

}

kapt {
    correctErrorTypes = true
}