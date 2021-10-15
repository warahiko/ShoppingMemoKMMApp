plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {
    implementation(project(":shared"))

    implementation(Dependencies.androidMaterial)
    implementation(Dependencies.swipeRefresh)
    implementation(Dependencies.viewPager)
    implementation(Dependencies.viewPagerIndicators)

    implementation(Dependencies.AndroidX.coreKtx)
    implementation(Dependencies.AndroidX.appCompat)
    implementation(Dependencies.AndroidX.lifecycleRuntimeKtx)
    implementation(Dependencies.AndroidX.lifecycleViewModelCompose)
    implementation(Dependencies.AndroidX.activityCompose)
    implementation(Dependencies.AndroidX.navigationFragmentKtx)
    implementation(Dependencies.AndroidX.navigationUiKtx)
    implementation(Dependencies.AndroidX.navigationCompose)
    implementation(Dependencies.AndroidX.hiltNavigationCompose)
    implementation(Dependencies.AndroidX.Compose.compiler)
    implementation(Dependencies.AndroidX.Compose.ui)
    implementation(Dependencies.AndroidX.Compose.material)
    implementation(Dependencies.AndroidX.Compose.materialIconsExtended)
    implementation(Dependencies.AndroidX.Compose.uiTooling)

    // DI
    implementation(Dependencies.koinAndroid)

    // test
    testImplementation(Dependencies.jUnit4)
    androidTestImplementation(Dependencies.AndroidX.Test.extJUnit)
    androidTestImplementation(Dependencies.AndroidX.Test.espressoCore)
    androidTestImplementation(Dependencies.AndroidX.Compose.uiTestJUnit4)

    // debug
    implementation(Dependencies.timber)
}

android {
    compileSdkVersion(30)
    defaultConfig {
        applicationId = "io.github.warahiko.shoppingmemokmmapplication.android"
        minSdkVersion(28)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}
