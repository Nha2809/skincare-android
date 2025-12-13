plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.myapplication"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.myapplication"
        minSdk = 24
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures{
        viewBinding = true
    }
}

dependencies {
    // 1. ADD THE FIREBASE BOM (Platform) to manage versions.
    // Use the latest stable version (34.6.0 as of the current date).
    implementation(platform("com.google.firebase:firebase-bom:34.6.0"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // 2. USE THE MAIN MODULE (firebase-database) instead of the KTX module.
    // The KTX functionality is now included in the main module.
    // If your libs.toml file still points to a KTX dependency, you should update it.
    // Assuming 'libs.firebase.database.ktx' is defined without a version in libs.toml,
    // you would change it to reference the main module:
    // implementation(libs.firebase.database) // Change reference in libs.toml/build.gradle

    // If you remove the old libs entry, use this direct line instead:
    implementation("com.google.firebase:firebase-database")

    // REMOVE THIS LINE: implementation(libs.firebase.database.ktx)
    // OR ensure 'libs.firebase.database.ktx' now points to 'com.google.firebase:firebase-database'
    // and rename the reference in your code for clarity (e.g., to 'libs.firebase.database').

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation("com.github.bumptech.glide:glide:5.0.5")
    implementation("com.google.code.gson:gson:2.13.2")
    implementation("com.tbuonomo:dotsindicator:5.1.0")
}