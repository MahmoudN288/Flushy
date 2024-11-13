plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
    //Parcelize
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
    id("kotlinx-serialization")
    kotlin("plugin.serialization")
    id("com.google.dagger.hilt.android")
    //Figma
    //id("com.google.relay")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.elTohamy.flushy"
    compileSdk = 35

    /*sourceSets.getByName("main") {
        assets {
            srcDir("src/main/ui-packages")
        }
    }*/

    /*androidResources {
        @Suppress("UnstableApiUsage")
        generateLocaleConfig = true
    }*/

    defaultConfig {
        applicationId = "com.elTohamy.flushy"
        minSdk = 25
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        signingConfig = signingConfigs.getByName("debug")
        android.buildFeatures.buildConfig = true

        resourceConfigurations += listOf("ar", "de", "en", "es", "ar-rEG")
        //resourceConfigurations.plus(listOf("en", "ar"))

        //buildConfigField("String", "API_KEY", "\"" + properties["API_KEY"])

        buildConfigField("String", "API_KEY", "" + properties["API_KEY"])
        buildConfigField("String", "NEWS_API_KEY", "" + properties["NEWS_API_KEY"])

        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] =  "$projectDir/schemas"
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }

        debug {
            signingConfig = signingConfigs.getByName("debug")
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false
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
        kotlinCompilerExtensionVersion = "1.5.13"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildToolsVersion = "34.0.0"
}

kapt {
    correctErrorTypes = true
}

ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
}

dependencies {
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.5")
    implementation("androidx.activity:activity-ktx:1.9.2")
    implementation("androidx.activity:activity-compose:1.9.2")
    implementation(platform("androidx.compose:compose-bom:2024.09.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3-android:1.3.0")
    implementation("androidx.compose.material:material:1.7.1")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.material:material-icons-core:1.7.1")
    implementation("androidx.compose.material:material-icons-core-android:1.7.1")
    implementation("androidx.compose.material:material-icons-extended:1.7.1")
    implementation("androidx.wear.compose:compose-material3:1.0.0-alpha24")
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.wear.compose:compose-material:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.09.01"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation("androidx.navigation:navigation-compose:2.8.0")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    implementation("io.coil-kt:coil-compose:2.5.0")
    implementation("androidx.palette:palette-ktx:1.0.0")

    // optional - Paging 3 Integration
    implementation("javax.inject:javax.inject:1")
    implementation("androidx.hilt:hilt-navigation-fragment:1.2.0")

    //UI Controller
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")

    // KotlinX Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")

    // Paging 3.0
    implementation("androidx.paging:paging-compose:3.3.2")

    // Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.49")
    ksp("com.google.dagger:hilt-android-compiler:2.49")
    ksp("androidx.hilt:hilt-compiler:1.2.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")


    //Onboarding
    // Splash API
    implementation("androidx.core:core-splashscreen:1.0.1")

    // Pager and Indicators - Accompanist
    implementation("com.google.accompanist:accompanist-pager:0.33.2-alpha")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.33.2-alpha")

    // DataStore Preferences
    implementation("androidx.datastore:datastore-preferences:1.1.1")
    //Live data
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.5")
    implementation("androidx.compose.runtime:runtime-livedata:1.7.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.5")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.5")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.5")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.5")
    implementation("androidx.lifecycle:lifecycle-livedata-core-ktx:2.8.5")

    //Date Picker
    // Implementing the `core` module is mandatory for using other use cases.
    implementation("com.maxkeppeler.sheets-compose-dialogs:core:1.3.0")
    // CALENDAR
    implementation("com.maxkeppeler.sheets-compose-dialogs:calendar:1.3.0")
    // CLOCK
    implementation("com.maxkeppeler.sheets-compose-dialogs:clock:1.3.0")
    //Empji
    implementation("com.maxkeppeler.sheets-compose-dialogs:emoji:1.3.0" )

    //Drag and Drop Lazy Column
    implementation("org.burnoutcrew.composereorderable:reorderable:0.9.6")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.12.0"))
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    //Formatter
    implementation("com.jakewharton.threetenabp:threetenabp:1.2.1")
    implementation("com.squareup.moshi:moshi-kotlin:1.12.0")
    ksp("com.squareup.moshi:moshi-kotlin-codegen:1.12.0")
    //Room
    implementation("androidx.room:room-ktx:2.6.1")
    implementation("androidx.room:room-runtime:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")

    //Ktor
    implementation("io.ktor:ktor-client-core:2.3.8")
    implementation("io.ktor:ktor-client-android:2.3.8")
    implementation("io.ktor:ktor-client-serialization:2.3.8")
    implementation("io.ktor:ktor-client-logging:2.3.8")

    //Lottie
    implementation("com.airbnb.android:lottie-compose:6.3.0")

    //Tracing
    implementation("androidx.compose.runtime:runtime-tracing:1.0.0-beta01")

    //Date Picker
    implementation("com.marosseleng.android:compose-material3-datetime-pickers:0.7.2")
    //implementation("io.github.vanpra.compose-material-dialogs:core:0.9.0")
    //Collapsing Toolbar
    implementation("me.onebone:toolbar-compose:2.3.5")
    //Icons
    implementation("br.com.devsrsouza.compose.icons:font-awesome:1.1.0")


    //Shimmer
    implementation("com.valentinilk.shimmer:compose-shimmer:1.3.0")

    //Swipe to Refresh
    implementation("com.google.accompanist:accompanist-swiperefresh:0.35.0-alpha")
    //OneBone
    implementation("me.onebone:toolbar-compose:2.3.5")





    //Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.3.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth-ktx:23.0.0")
    implementation("com.google.android.gms:play-services-auth:21.2.0")
    // Declare the dependency for the Cloud Firestore library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-firestore")
    implementation("com.google.firebase:firebase-storage-ktx:21.0.0")
    implementation("com.google.firebase:firebase-firestore-ktx:25.1.0")
    implementation("com.google.firebase:firebase-messaging-ktx:24.0.1")
    implementation("androidx.work:work-gcm:2.9.1")
}