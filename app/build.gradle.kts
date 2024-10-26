import java.util.Properties

val projectPropertiesPath = "../project.properties"
val properties = readProperties(file(projectPropertiesPath))

val semverMajor = properties.getProperty("semverMajor").toInt()
val semverMinor = properties.getProperty("semverMinor").toInt()
val semverPatch = properties.getProperty("semverPatch").toInt()

val projectGroup = properties.getProperty("projectGroup") ?: ""
val projectArtifact = properties.getProperty("projectArtifact") ?: ""
val projectDescription = properties.getProperty("projectDescription") ?: ""
val projectProductDimension = properties.getProperty("projectProductDimension") ?: ""
val projectSoftwareComponent = properties.getProperty("projectSoftwareComponent") ?: ""

val demo = properties.getProperty("demo") ?: ""
val premium = properties.getProperty("premium") ?: ""
val projectRelease = properties.getProperty("projectRelease") ?: ""
val projectDebug = properties.getProperty("projectDebug") ?: ""

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt.android)
    id("kotlin-kapt")
}

android {
    namespace = "${projectGroup}.${projectArtifact}"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "$projectGroup.$projectArtifact"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = semverMajor * 1000000 +
                semverMinor * 10000 +
                semverPatch * 100
        versionName = "$semverMajor.$semverMinor.$semverPatch"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName(projectDebug) {
            isDefault = true
            isMinifyEnabled = false
        }
        getByName(projectRelease) {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions += projectProductDimension
    productFlavors {
        create(premium) {
            dimension = projectProductDimension
        }
        create(demo) {
            dimension = projectProductDimension
            isDefault = true
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
    }
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}

dependencies {

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.ktx)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(project(":data"))

    // HILT
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

fun readProperties(propertiesFile: File) = Properties().apply {
    propertiesFile.inputStream().use { fis ->
        load(fis)
    }
}