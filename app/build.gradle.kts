@file:Suppress("DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.android.kotlin)

    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.kotlin.kapt)

//    alias(libs.plugins.ksp)


    alias(libs.plugins.safeargs)
    alias(libs.plugins.dagger.hilt)
}

android {
    namespace = "com.ticonsys.memoryleakdemo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ticonsys.memoryleakdemo"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.swipe.refresh.layout)

    implementation(libs.work.runtime.ktx)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.bundles.lifecycle)
    implementation(libs.bundles.navigation)
    implementation(libs.bundles.coil)

    implementation(libs.viewbindingpropertydelegate.full)
    implementation(libs.leakcanary.android)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


}