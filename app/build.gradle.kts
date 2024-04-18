import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {

    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")

}

android {

    namespace = "com.example.ar_task"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.ar_task"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17

    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}

dependencies {

implementation(libs.androidx.room.common)
    //    implementation(libs.androidx.navigation.runtime.ktx)
//    implementation(libs.androidx.ui.tooling.preview.android)
    // Define versions
    val agp_version = "8.1.2"
    val java_version = "17"
    val minSDK_version = "24"
    val targetSDK_version = "34"
    val compileSDK_version = "34"
    val kotlin_version = "1.9.10"
    val core_ktx_version = "1.12.0"
    val junit_version = "4.13.2"
    val androidx_test_ext_junit_version = "1.1.5"
    val espresso_core_version = "3.5.1"
    val lifecycle_runtime_ktx_version = "2.6.2"
    val activity_compose_version = "1.8.0"
    val compose_bom_version = "2023.10.00"
    val compose_compiler_version = "1.5.3"
    val hilt_version = "2.47"
    val dagger_hilt_compiler_version = "2.47"
    val hilt_compiler_version = "1.0.0"
    val hilt_navigation_version = "1.0.0"
    val navigation_safe_args_version = "2.7.4"
    val ksp_version = "1.9.10-1.0.13"
    val window_manager_version = "1.1.0"
    val splash_version = "1.0.1"
    val compose_activity_version = "1.8.0"
    val coil_version = "2.4.0"
    val sdp_version = "1.1.0"
    val ssp_version = "1.1.0"
    val retrofit_version = "2.9.0"
    val okhttp_version = "4.11.0"
    val lottie_version = "5.2.0"
    val koin_version = "3.0.1"
//
//    //Koin
//    implementation("org.koin:koin-core:$koin_version")
//    implementation( "org.koin:koin-android:$koin_version")
//    implementation( "org.koin:koin-androidx-viewmodel:$koin_version")
//    implementation( "org.koin:koin-test:$koin_version")
    implementation("com.google.dagger:hilt-android:2.44")
//    annotationProcessor("com.google.dagger:hilt-android-compiler:2.44")

    kapt("com.google.dagger:hilt-android-compiler:2.44")
    kapt("androidx.hilt:hilt-compiler:1.0.0-alpha03")

    // Dependencies
//    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
    implementation("androidx.core:core-ktx:$core_ktx_version")
    implementation("junit:junit:$junit_version")
    implementation("androidx.test.ext:junit:$androidx_test_ext_junit_version")
    implementation("androidx.test.espresso:espresso-core:$espresso_core_version")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_runtime_ktx_version")
    implementation("androidx.activity:activity-compose:$activity_compose_version")
    implementation("androidx.compose:compose-bom:$compose_bom_version")
//    implementation(libs.androidx.hilt.compiler)
    implementation("com.google.dagger:hilt-android:$dagger_hilt_compiler_version")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.material:material")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.activity:activity-compose:$compose_activity_version")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:$lifecycle_runtime_ktx_version")
    implementation("androidx.window:window:$window_manager_version")
    implementation("androidx.core:core-splashscreen:$splash_version")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_runtime_ktx_version")
    implementation("io.coil-kt:coil-compose:$coil_version")
    implementation("com.airbnb.android:lottie:$lottie_version")
    implementation("com.airbnb.android:lottie-compose:$lottie_version")
    implementation("com.intuit.ssp:ssp-android:$ssp_version")
    implementation("com.intuit.sdp:sdp-android:$sdp_version")
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation("com.squareup.retrofit2:converter-gson:$retrofit_version")
    implementation("com.squareup.okhttp3:logging-interceptor:$okhttp_version")
    implementation("com.squareup.okhttp3:okhttp:$okhttp_version")
    implementation ("com.google.android.material:material:1.8.0-alpha01")
    implementation ("androidx.compose.material3:material3:1.0.0-beta03")
    // Kotlin and AGP plugins
//    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version")

//    implementation(libs.android.gradlePlugin)
//    implementation(libs.kotlin.gradlePlugin)
//    implementation(libs.ksp.gradlePlugin)
//    implementation(libs.args.gradlePlugin)
//    implementation(libs.androidx.junit.ktx)
    implementation("androidx.navigation:navigation-compose:2.5.1")
    implementation ("androidx.compose.material:material:1.1.0")
//    implementation("com.google.dagger:hilt-android:2.44")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
//    implementation("androidx.room:room-compose:2.4.0")
    val dagger_version = "2.41"


    annotationProcessor(libs.androidx.hilt.compiler)

    // optional - Kotlin Extensions and Coroutines support for Room
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    kapt("android.arch.persistence.room:compiler:1.1.1")
    implementation ("androidx.room:room-ktx:$room_version")


}
