import org.jetbrains.kotlin.konan.properties.Properties
import java.io.FileInputStream

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

val properties = Properties()
properties.load(project.rootProject.file("local.properties").inputStream())


android {
    namespace = "com.example.muvies"
    compileSdk = AndroidSdk.compile

    defaultConfig {
        applicationId = "com.example.muvies"
        minSdk = AndroidSdk.min
        targetSdk = AndroidSdk.target
        versionCode = 1
        versionName = "1.0"

        buildConfigField("String", "API_KEY", "\"${properties.getProperty("API_KEY")}\"")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
    buildFeatures {
        viewBinding = true
    }

}

dependencies {

    implementation(Libraries.appCompat)
    implementation(Libraries.ktxCore)
    implementation(Libraries.material)
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.coil)
    kapt(Libraries.codegen)
    implementation(Libraries.coreKtx)
    implementation(Libraries.fragment)
    implementation(Libraries.fragmentNavigation)
    implementation(Libraries.hilt)
    implementation(Libraries.moshi)
    implementation(Libraries.navigation)
    implementation(Libraries.okHttp3)
    implementation(Libraries.okHttp3Logging)
    implementation(Libraries.retrofit)
    implementation(Libraries.retrofitMoshiConverter)
    kapt(Libraries.roomCompiler)
    implementation(Libraries.roomKtx)
    implementation(Libraries.roomRuntime)
    implementation(Libraries.rxAndroid)
    implementation(Libraries.rxjava)
    implementation(Libraries.rxJavaAdapter)
    implementation(Libraries.timber)
    implementation(Libraries.lifecycleRuntimeKtx)
    implementation(Libraries.lifecycleLiveDataKtx)
    implementation(Libraries.lifecycleViewModelKtx)
    kapt(Libraries.hiltAndroidCompiler)

    androidTestImplementation(TestLibraries.espressoCore)
    androidTestImplementation(TestLibraries.junit)
    androidTestImplementation(TestLibraries.testExtJunit)

    testImplementation(TestLibraries.androidCore)
    testImplementation(TestLibraries.androidTestRunner)
    testImplementation(TestLibraries.coroutines)
    testImplementation(TestLibraries.junit)
    testImplementation(TestLibraries.mockito)
    testImplementation(TestLibraries.mockitoKotlin)
    testImplementation(TestLibraries.roboelectric)
    testImplementation(TestLibraries.testExtJunit)
}