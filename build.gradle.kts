import java.util.Properties

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    id("vkid.manifest.placeholders") version "1.1.0" apply true
}

fun loadProperties(): Properties {
    return Properties().apply {
        load(File("local.properties").inputStream())
    }
}

val vkidClientIdProperty = loadProperties().getProperty("vkid.client.id") ?: ""
val vkidClientSecretProperty = loadProperties().getProperty("vkid.client.secret") ?: ""

vkidManifestPlaceholders {

    init(
        clientId = vkidClientIdProperty,
        clientSecret = vkidClientSecretProperty,
    )
    vkidRedirectHost = "vk.ru"
    vkidRedirectScheme = "vk${vkidClientIdProperty}}"
    vkidClientId = vkidClientIdProperty
    vkidClientSecret = vkidClientSecretProperty
}