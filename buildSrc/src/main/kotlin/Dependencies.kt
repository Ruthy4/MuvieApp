
const val kotlinVersion = "1.7.21"
object Versions {
    const val android_core_version = "2.1.0"
    const val android_runner_version = "1.1.0"
    const val codegen_version = "1.13.0"
    const val coil_version = "1.3.2"
    const val core_version = "1.9.0"
    const val coroutines_version = "1.6.0"
    const val espresso_core_version = "3.5.1"
    const val hilt_compiler_version = "1.0.0"
    const val hiltNavigation = "1.0.0"
    const val hilt_version = "2.42"
    const val junit_version = "4.13.2"
    const val ktlint_version = "0.45.2"
    const val lifecycle_version = "2.5.1"
    const val mockito_version = "4.3.1"
    const val mockito_kotlin_version = "4.0.0"
    const val moshi_converter_version = "2.9.0"
    const val moshi_version = "1.15.0"
    const val okhttp3_version = "5.0.0-alpha.2"
    const val retrofit_version = "2.9.0"
    const val roboelectric_version = "4.10.3"
    const val room_version = "2.4.3"
    const val spotless_version = "6.19.0"
    const val test_ext_junit_version = "1.1.5"
    const val timber_version = "4.7.1"
    const val appCompact_version = "1.6.1"
    const val material_version = "1.9.0"
    const val rxjava_version = "2.1.9"
    const val rxJavaAdapter_version = "2.3.0"
    const val rxAndroid_version = "2.0.1"
    const val fragment_version = "1.5.7"
    const val nav_version = "2.4.2"
    const val junit4 = "4.12"
    const val junitExt = "1.1.5"
    const val testRunner = "1.1.0-alpha4"
    const val espresso = "3.1.0-alpha4"
    const val buildToolsVersion = "7.4.0"
    const val ktlintVersion = "0.48.1"
}

object BuildPlugins {
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildToolsVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"

}

object AndroidSdk {
    const val min = 24
    const val compile = 33
    const val target = compile
}

object Libraries {
    const val appCompat             = "androidx.appcompat:appcompat:${Versions.appCompact_version}"
    const val codegen          = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.codegen_version}"
    const val coreKtx           = "androidx.core:core-ktx:${Versions.core_version}"
    const val fragment            = "androidx.fragment:fragment-ktx:${Versions.fragment_version}"
    const val fragmentNavigation  = "androidx.navigation:navigation-fragment-ktx:${Versions.nav_version}"
    const val hilt                 = "com.google.dagger:hilt-android:${Versions.hilt_version}"
    const val hiltAndroidCompiler    = "com.google.dagger:hilt-android-compiler:${Versions.hilt_version}"
    const val kotlinStdLib     = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
    const val ktxCore          = "androidx.core:core-ktx:${Versions.core_version}"
    const val lifecycleLiveDataKtx   = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle_version}"
    const val lifecycleRuntimeKtx    = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle_version}"
    const val lifecycleViewModelKtx  = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle_version}"
    const val material       = "com.google.android.material:material:${Versions.material_version}"
    const val moshi                  = "com.squareup.moshi:moshi-kotlin:${Versions.moshi_version}"
    const val navigation          = "androidx.navigation:navigation-ui-ktx:${Versions.nav_version}"
    const val okHttp3                = "com.squareup.okhttp3:okhttp:${Versions.okhttp3_version}"
    const val okHttp3Logging         = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3_version}"
    const val retrofit               = "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"
    const val retrofitMoshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.moshi_converter_version}"
    const val roomCompiler           = "androidx.room:room-compiler:${Versions.room_version}"
    const val roomKtx                = "androidx.room:room-ktx:${Versions.room_version}"
    const val roomRuntime            = "androidx.room:room-runtime:${Versions.room_version}"
    const val rxAndroid           = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid_version}"
    const val rxjava                  = "io.reactivex.rxjava2:rxjava:${Versions.rxjava_version}"
    const val rxJavaAdapter       = "com.squareup.retrofit2:adapter-rxjava2:${Versions.rxJavaAdapter_version}"
    const val timber                 =  "com.jakewharton.timber:timber:${Versions.timber_version}"
}

object TestLibraries {
    const val androidCore          = "androidx.arch.core:core-testing:${Versions.android_core_version}"
    const val androidTestRunner    = "androidx.test:runner:${Versions.android_runner_version}"
    const val coroutines           = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines_version}"
    const val espressoCore         = "androidx.test.espresso:espresso-core:${Versions.espresso_core_version}"
    const val junit                = "junit:junit:${Versions.junit_version}"
    const val mockito              = "org.mockito:mockito-core:${Versions.mockito_version}"
    const val mockitoKotlin        = "org.mockito.kotlin:mockito-kotlin:${Versions.mockito_kotlin_version}"
    const val roboelectric         = "org.robolectric:robolectric:${Versions.roboelectric_version}"
    const val testExtJunit         = "androidx.test.ext:junit:${Versions.test_ext_junit_version}"

}