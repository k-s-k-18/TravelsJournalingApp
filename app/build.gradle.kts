plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.devtools.ksp") version "1.9.22-1.0.16"
    //kotlin("kapt") version "1.9.22" // Version originally 1.6.10
    id("kotlin-kapt")
}

android {
    namespace = "com.example.traveladventures"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.traveladventures"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName  = "1.0"

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

    buildFeatures{
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11 // Originally VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_11 // Originally VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "11" // Originally 1.8
    }

    // Enable Data Binding
    buildFeatures {
        //noinspection DataBindingWithoutKapt
        dataBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.fragment.testing)
    implementation(libs.play.services.location)
    implementation(libs.locationdelegation)
    implementation(libs.junit)
    testImplementation(libs.junit)
    testImplementation(libs.junit.jupiter)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
    implementation ("androidx.fragment:fragment-ktx:1.8.5") // Version originally 1.4.1
    implementation ("androidx.recyclerview:recyclerview:1.3.2") // Version originally 1.2.1
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
    //implementation ("androidx.room:room-runtime:2.4.2")
    //implementation ("androidx.room:room-ktx:2.4.2")
    //kapt ("androidx.room:room-compiler:2.4.2")
    implementation("com.mapbox.maps:android:11.8.0")
    //implementation ("com.mapbox.maps:android:10.9.1")
    //implementation ("androidx.fragment:fragment-ktx:1.4.1")

    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    //annotationProcessor("androidx.room:room-compiler:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    //ksp("androidx.room:room-compiler:$room_version")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$rootProject.coroutines_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$rootProject.coroutines_version")

    implementation("androidx.lifecycle:lifecycle-common-java8:$rootProject.lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$rootProject.lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$rootProject.lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$rootProject.lifecycle_version")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")

    implementation("androidx.activity:activity:1.6.0-alpha05")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.okhttp3:okhttp:4.9.3")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0")
    implementation ("com.squareup.moshi:moshi:1.13.0")
    ksp ("com.squareup.moshi:moshi-kotlin-codegen:1.13.0")
    implementation ("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.google.code.gson:gson:2.8.9")
    implementation ("androidx.navigation:navigation-fragment-ktx:2.4.1")
    implementation ("androidx.navigation:navigation-ui-ktx:2.4.1")
    debugImplementation("androidx.fragment:fragment-testing:1.6.1")

    testImplementation("org.mockito:mockito-core:5.14.2")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.4.0")
    testImplementation("junit:junit:4.13.2")
    testImplementation("androidx.room:room-testing:2.6.1")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.9.0")

    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test.espresso:espresso-contrib:3.5.1")
    androidTestImplementation("androidx.test:core:1.5.0")
    androidTestImplementation("androidx.test:runner:1.6.2")
    androidTestImplementation("androidx.test:rules:1.6.1")
    androidTestImplementation("org.hamcrest:hamcrest:2.2")
    //androidTestImplementation("androidx.test:platform:1.1.0")
}