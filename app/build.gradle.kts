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
        compileSdk = 36
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
     * Constraintlayout
     *
     * https://developer.android.com/jetpack/androidx/releases/constraintlayout
     * https://developer.android.com/reference/androidx/constraintlayout/widget/ConstraintLayout
     *
     * implementation("androidx.constraintlayout:constraintlayout:2.2.1")
     */
    implementation(libs.androidx.constraintlayout)

    /**
     * Serialization
     *
     * https://github.com/Kotlin/kotlinx.serialization
     * https://kotlinlang.org/docs/serialization.html
     *
     * implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0")
     */
    implementation(libs.kotlinx.serialization.json)

    /**
     * SplashScreen
     *
     * https://developer.android.com/reference/android/window/SplashScreen
     * https://developer.android.com/reference/androidx/core/splashscreen/SplashScreen
     * https://developer.android.com/develop/ui/views/launch/splash-screen
     *
     * https://github.com/patildnyaneshwar/SplashScreen
     *
     * implementation("androidx.core:core-splashscreen:1.0.1")
     */
    implementation(libs.androidx.core.splashscreen)

    /**
     * Android Jetpack's Navigation component
     *
     * https://developer.android.com/guide/navigation
     *
     */
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui)

    /**
     * ViewModel Lifecycle
     *
     * https://developer.android.com/jetpack/androidx/releases/lifecycle
     */
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    /**
     * SkeletonLayout
     *
     * https://github.com/Faltenreich/SkeletonLayout
     */
    implementation(libs.skeletonlayout)

    /**
     * Glide
     * BlurTransformation glide-transformations
     *
     * https://github.com/bumptech/glide
     * https://github.com/wasabeef/glide-transformations
     */
    implementation(libs.glide)

    /**
     * Retrofit
     *
     * https://github.com/square/retrofit
     * https://github.com/square/retrofit/tree/trunk/retrofit-converters/kotlinx-serialization
     */
    implementation(libs.retrofit)
    implementation(libs.converter.kotlinx.serialization)

    /**
     * OkHttp
     *
     * https://github.com/square/okhttp
     */
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

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
     * SwipeRefreshLayout
     *
     * https://developer.android.com/develop/ui/views/touch-and-input/swipe/add-swipe-interface
     */
    implementation(libs.androidx.swiperefreshlayout)
}
