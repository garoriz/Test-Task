plugins {
    id("java")
    id("org.springframework.boot") version("2.6.3")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    val springBootVersion = "2.6.7"
    implementation("org.springframework.boot:spring-boot-starter-web:${springBootVersion}")

    // https://mvnrepository.com/artifact/org.mockito/mockito-all
    testImplementation("org.mockito:mockito-all:1.8.4")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}