plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    //id("org.openapi.generator")
}

android {
    namespace = "com.example.mapfortravelers"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mapfortravelers"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

/*val generatedSourcesPath = "$buildDir/generated"
val apiDescriptionFile = "$rootDir/app/src/main/res/openapi.json"
val apiRootName = "com.makrol.teamcity.api.client"

openApiGenerate {
    generatorName.set("kotlin")
    inputSpec.set(apiDescriptionFile)
    outputDir.set("$buildDir/generated")
    apiPackage.set("$apiRootName.api")
    invokerPackage.set("$apiRootName.invoker")
    modelPackage.set("$apiRootName.model")
}
kotlin.sourceSets["main"].kotlin.srcDir("$generatedSourcesPath/src/main/kotlin")

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    dependsOn("openApiGenerate")
}*/

dependencies {

    implementation ("com.android.volley:volley:1.2.1")
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    // OpenAPI client
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.14.0")
    // Coroutines to make the HTTP requests asynchronous(In the background thread)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    // Okhttp3 for the POST requests
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    // Gson to convert raw JSON to pretty JSON
    implementation("com.google.code.gson:gson:2.8.9")
    implementation("io.ktor:ktor-client-cio:1.0.0")
    //implementation("ktor")
    // Облегченная библиотека, содержит только карту, слой пробок,
    // LocationManager, UserLocationLayer
    // и возможность скачивать офлайн-карты (только в платной версии).
    //implementation("com.yandex.android:maps.mobile:4.4.0-lite")

    // Полная библиотека в дополнение к lite версии предоставляет автомобильную маршрутизацию,
    // веломаршрутизацию, пешеходную маршрутизацию и маршрутизацию на общественном транспорте,
    // поиск, suggest, геокодирование и отображение панорам.
     implementation("com.yandex.android:maps.mobile:4.4.0-full")
}