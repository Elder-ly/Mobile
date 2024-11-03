import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "elder.ly.mobile"
    compileSdk = 34

    defaultConfig {
        applicationId = "elder.ly.mobile"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        val localProperties = project.rootProject.file("local.properties")
        val properties = Properties().apply {
            load(FileInputStream(localProperties))
        }

        buildConfigField(
            type = "String",
            name = "GOOGLE_CLIENT_ID",
            value = properties.getProperty("GOOGLE_CLIENT_ID")
        )

        buildConfigField(
            type = "String",
            name = "API_BASE_URL",
            value = properties.getProperty("API_BASE_URL")
        )

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
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui.text.google.fonts)
    implementation(libs.core)
    implementation(libs.sheets.compose.dialogs.calendar)
    implementation(libs.clock)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.navigation.compose)
    implementation(libs.kotlinx.serialization.json)

    //DataStore
    implementation(libs.androidx.datastore.preferences)

    //RetroFit
    implementation(libs.retrofit)

    //Gson
    implementation(libs.gson)
    implementation(libs.converter.gson)
    implementation("com.google.code.gson:gson:2.8.8")

    //Google SSO
    implementation(libs.googleid)
    implementation(libs.androidx.credentials.v122)
    implementation(libs.androidx.credentials.play.services.auth.v122)

    // Koin
    implementation("io.insert-koin:koin-android:4.0.0")
    implementation("io.insert-koin:koin-compose-viewmodel:4.0.0")

    implementation(libs.coil.compose)
}
