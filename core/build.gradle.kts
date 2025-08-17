plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    kotlin("kapt")
}

android {
    namespace = "com.core"
    compileSdk = 36

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"https://api.jikan.moe/v4/\"")
        }
        release {
            isMinifyEnabled = false
            buildConfigField("String", "BASE_URL", "\"https://api.jikan.moe/v4/\"")
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
    kotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
        }
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    // hilt
    implementation(libs.dagger.hilt)
    implementation(libs.androidx.hilt.legacy)
    kapt(libs.dagger.hilt.compiler)
    kapt(libs.androidx.hilt.viewmodel)
    kapt(libs.androidx.hilt.compiler)

    // Retrofit
    api(libs.retrofit.core)
    api(libs.gson)
    implementation(libs.okhttp)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp.logging)

    // Coroutine
    api(libs.coroutine.core)
    api(libs.coroutine.android)  // Adding back for Android coroutine support

    // Room Database
    implementation(libs.room.runtime)
    kapt(libs.room.compiler)
}