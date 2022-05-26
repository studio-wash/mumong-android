plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    setCompileSdkVersion(31)

    defaultConfig {
        applicationId = "com.studiowash.mumong"
        minSdk = 24
        targetSdk = 31
        versionCode = 1
        versionName = "1.0.0"

        manifestPlaceholders["KAKAO_NATIVE_APP_KEY"] = "b494f0df7d1a69d740e57c3dfac3c091"
        buildConfigField("String", "KAKAO_NATIVE_APP_KEY", "\"b494f0df7d1a69d740e57c3dfac3c091\"")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        dataBinding = true
    }
}

dependencies {
    // kotlin
    implementation("androidx.core:core-ktx:1.7.0")

    // hilt
    val hiltVersion = "2.41"
    implementation("com.google.dagger:hilt-android:${hiltVersion}")
    kapt("com.google.dagger:hilt-android-compiler:${hiltVersion}")

    // test
    testImplementation("junit:junit:4+")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    // login
    implementation("com.kakao.sdk:v2-user:2.7.0")

    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":presentation"))
}