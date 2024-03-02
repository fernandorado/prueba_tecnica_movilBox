plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")

    id("com.google.dagger.hilt.android")
    //id("dagger.hilt.android.plugin")
}


android {
    namespace = "com.android.pruebatecnicamovilbox"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.android.pruebatecnicamovilbox"
        minSdk = 21
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    composeOptions{
        kotlinCompilerExtensionVersion = "1.5.0"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    val lifecycle_version = "2.7.0"
    val fragment_version = "1.6.2"
    val activity_version = "1.8.2"
    val room_version = "2.6.1"
    val hilt_version ="2.44"

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    // Room
    implementation("androidx.room:room-runtime:$room_version")
    ksp("androidx.room:room-compiler:$room_version") // Cambiar kapt a annotationProcessor
    implementation("androidx.room:room-ktx:$room_version")

    //ksp
    implementation("com.google.devtools.ksp:symbol-processing-api:1.9.21-1.0.15")
    // Dagger Hilt
    implementation("com.google.dagger:hilt-android:$hilt_version")
    ksp("com.google.dagger:hilt-android-compiler:$hilt_version")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata:$lifecycle_version")
    // Fragment
    implementation("androidx.fragment:fragment-ktx:$fragment_version")
    // Activity
    implementation("androidx.activity:activity-ktx:$activity_version")
    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    //Corrutinas
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    //Glide
    implementation("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")


    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

