import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
    id("vkid.manifest.placeholders")
}

android {
    namespace = "com.example.vknewsclient"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.vknewsclient"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        fun loadProperties(): java.util.Properties {
            return Properties().apply {
                load(File("local.properties").inputStream())
            }
        }

        val vkidClientIdProperty = loadProperties().getProperty("vkid.client.id") ?: ""
        val vkidClientSecretProperty =
            loadProperties().getProperty("vkid.client.secret") ?: ""


        addManifestPlaceholders(
            mapOf(
                "VKIDClientID" to vkidClientIdProperty,
                "VKIDClientSecret" to vkidClientSecretProperty,
                "VKIDRedirectHost" to "vk.ru",
                "VKIDRedirectScheme" to "vk${vkidClientIdProperty}",
            )
        )

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp3.okhttp)
    implementation(libs.okhttp3.logging.interceptor)
    implementation(libs.retrofit)
    implementation(libs.vkid)
    implementation(libs.gson)
    implementation(libs.coil.compose)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.androidx.hilt.lifecycle.viewmodel.compose)
    implementation(libs.logging.interceptor)
    implementation(libs.converter.gson)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}