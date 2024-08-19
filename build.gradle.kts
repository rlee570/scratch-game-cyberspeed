plugins {
    id("java")
    id("com.gradleup.shadow") version "8.3.0"
    id("com.diffplug.spotless") version "6.25.0"
}

group = "ae.cyberspeed"
val version = "0.0.1"

repositories {
    mavenCentral()
}


dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.2")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

spotless{
    java{
        palantirJavaFormat("2.39.0").style("GOOGLE")
    }
}

tasks.withType(Jar::class) {
    manifest{
        attributes["Main-Class"] = "ae.cyberspeed.ScratchGame"
    }
}

tasks.shadowJar{
    archiveBaseName.set("scratch-game")
    archiveClassifier.set("")
    archiveVersion.set(version)
}

tasks.test {
    useJUnitPlatform()
}