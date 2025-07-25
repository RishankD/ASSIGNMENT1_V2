plugins {
   alias(libs.plugins.android.application)
   alias(libs.plugins.kotlin.android)
}

android {
   namespace = "ca.georgiancollege.assignment_1_v2"
   compileSdk = 35

   defaultConfig {
      applicationId = "com.example.omdbapp"
      minSdk = 24
      targetSdk = 35
      versionCode = 1
      versionName = "1.0"

      testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
   }

   buildFeatures {
      viewBinding = true
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
}

dependencies {
   implementation(libs.androidx.core.ktx)
   implementation(libs.androidx.appcompat)
   implementation(libs.material)
   implementation(libs.androidx.activity)
   implementation(libs.androidx.constraintlayout)
   implementation("com.squareup.okhttp3:okhttp:4.12.0")


   dependencies {
      implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
      implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")

      implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
      implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
   }


   testImplementation(libs.junit)
   androidTestImplementation(libs.androidx.junit)
   androidTestImplementation(libs.androidx.espresso.core)
}
