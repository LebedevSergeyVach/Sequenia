import java.io.BufferedReader
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    /**
     * Serialization
     *
     * https://github.com/Kotlin/kotlinx.serialization
     */
    alias(libs.plugins.serialization)

    /**
     * KSP - Kotlin Symbol Processing API
     *
     * https://github.com/google/ksp
     */
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.sequenia"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.sequenia"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "v1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // file("secrets.properties")
        val secretsProperties = rootDir.resolve("secrets.properties")
            .bufferedReader()
            .use { buffer: BufferedReader ->
                Properties().apply {
                    load(buffer)
                }
            }

        buildConfigField(
            "String",
            "URL_SERVER_FILM",
            secretsProperties.getProperty("URL_SERVER_FILM")
        )
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
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
        // Для ViewBinding вместо findViewById
        viewBinding = true
        // Для secrets.properties
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    /**
     * OkHttp
     *
     * https://github.com/square/okhttp
     */
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    /**
     * Retrofit
     *
     * https://github.com/square/retrofit
     * https://github.com/square/retrofit/tree/trunk/retrofit-converters/kotlinx-serialization
     */
    implementation(libs.retrofit)
    implementation(libs.converter.kotlinx.serialization)

    /**
     * Serialization
     *
     * https://github.com/Kotlin/kotlinx.serialization
     */
    implementation(libs.kotlinx.serialization.json)

    /**
     * Android Jetpack's Navigation component
     *
     * https://developer.android.com/guide/navigation
     */
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui)

    /**
     * ViewModel Lifecycle
     *
     * https://developer.android.com/topic/libraries/architecture/viewmodel
     */
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    /**
     * Glide
     * BlurTransformation glide-transformations
     *
     * https://github.com/bumptech/glide
     */
    implementation(libs.glide)

    /**
     * DI Koin
     *
     * https://github.com/InsertKoinIO/koin
     */
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.annotations)
    ksp(libs.koin.ksp.compiler)

    /**
     * SplashScreen
     *
     * https://developer.android.com/reference/androidx/core/splashscreen/SplashScreen
     */
    implementation(libs.androidx.core.splashscreen)

    /**
     * Constraintlayout
     *
     * https://developer.android.com/jetpack/androidx/releases/constraintlayout
     * https://developer.android.com/reference/androidx/constraintlayout/widget/ConstraintLayout
     */
    implementation(libs.androidx.constraintlayout)

    /**
     * SkeletonLayout
     *
     * https://github.com/Faltenreich/SkeletonLayout
     */
    implementation(libs.skeletonlayout)
}
