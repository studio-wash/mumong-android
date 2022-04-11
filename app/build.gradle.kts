plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")

    kotlin("plugin.serialization") version "1.6.20"
}

android {
    setCompileSdkVersion(31)

    defaultConfig {
        applicationId = "com.studiowash.mumong"
        minSdk = 24
        targetSdk = 31
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        dataBinding = true
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
    implementation("androidx.room:room-ktx:2.3.0")
    testImplementation("junit:junit:4+")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    // jetpack navigation component
    val navVersion = "2.4.0-beta02"
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")

    // Feature module Support
    implementation("androidx.navigation:navigation-dynamic-features-fragment:$navVersion")

    // Glide
    val glideVersion = "4.11.0"
    implementation("com.github.bumptech.glide:glide:$glideVersion")
    annotationProcessor("com.github.bumptech.glide:compiler:$glideVersion")

    // Kakao AD SDK
    val adfitVersion = "3.8.5"
    val kotlinVersion = "1.3.72"
    val playServiceVersion = "17.0.0"
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion")
    implementation("com.google.android.gms:play-services-ads-identifier:$playServiceVersion")
    implementation("com.kakao.adfit:ads-base:$adfitVersion")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.5.0")
    implementation("com.squareup.okhttp3:logging-interceptor:3.4.1")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.31-alpha")
    kapt("com.google.dagger:hilt-android-compiler:2.28.1-alpha")
    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
}