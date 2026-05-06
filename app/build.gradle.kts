import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
}

val properties =
    Properties().apply {
        load(project.rootProject.file("local.properties").inputStream())
    }

android {
    namespace = "com.example.letssopt"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.letssopt"
        minSdk = 30
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "BASE_URL", "${properties.getProperty("base.url")}")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    dependencies {
        // AndroidX Core & Lifecycle
        implementation(libs.bundles.androidx.core)

        // Compose
        implementation(platform(libs.androidx.compose.bom))
        implementation(libs.bundles.compose)
        debugImplementation(libs.bundles.compose.debug)

        // Navigation (SAA 적용)
        implementation(libs.androidx.navigation.compose)

        // Network
        implementation(libs.bundles.network)

        // Kotlinx
        implementation(libs.kotlinx.immutable)
        implementation(libs.kotlinx.serialization.json)

        // Room
        implementation(libs.bundles.room)
        ksp(libs.androidx.room.compiler)

        // Test
        testImplementation(libs.junit)
        androidTestImplementation(platform(libs.androidx.compose.bom))
        androidTestImplementation(libs.bundles.androidTest)
    }
}