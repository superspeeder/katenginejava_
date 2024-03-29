import org.gradle.internal.os.OperatingSystem

plugins {
    kotlin("jvm") version "1.9.21"
    `java-library`
}

val lwjglVersion = "3.3.4-SNAPSHOT"
val jomlVersion = "1.10.5"
val `lwjgl3-awtVersion` = "0.1.8"

val lwjglNatives = Pair(
    System.getProperty("os.name")!!,
    System.getProperty("os.arch")!!
).let { (name, arch) ->
    when {
        arrayOf("Linux", "SunOS", "Unit").any { name.startsWith(it) } ->
            if (arrayOf("arm", "aarch64").any { arch.startsWith(it) })
                "natives-linux${if (arch.contains("64") || arch.startsWith("armv8")) "-arm64" else "-arm32"}"
            else if (arch.startsWith("ppc"))
                "natives-linux-ppc64le"
            else if (arch.startsWith("riscv"))
                "natives-linux-riscv64"
            else
                "natives-linux"
        arrayOf("Windows").any { name.startsWith(it) }                ->
            "natives-windows"
        else                                                                            ->
            throw Error("Unrecognized or unsupported platform. Please set \"lwjglNatives\" manually")
    }
}

group = "dev.woc"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    api(platform("org.lwjgl:lwjgl-bom:$lwjglVersion"))

    api("org.lwjgl", "lwjgl")
    api("org.lwjgl", "lwjgl-assimp")
    api("org.lwjgl", "lwjgl-bgfx")
    api("org.lwjgl", "lwjgl-cuda")
    api("org.lwjgl", "lwjgl-egl")
    api("org.lwjgl", "lwjgl-fmod")
    api("org.lwjgl", "lwjgl-freetype")
    api("org.lwjgl", "lwjgl-glfw")
    api("org.lwjgl", "lwjgl-harfbuzz")
    api("org.lwjgl", "lwjgl-hwloc")
    api("org.lwjgl", "lwjgl-jawt")
    api("org.lwjgl", "lwjgl-jemalloc")
    api("org.lwjgl", "lwjgl-ktx")
    api("org.lwjgl", "lwjgl-libdivide")
    api("org.lwjgl", "lwjgl-llvm")
    api("org.lwjgl", "lwjgl-lmdb")
    api("org.lwjgl", "lwjgl-lz4")
    api("org.lwjgl", "lwjgl-meow")
    api("org.lwjgl", "lwjgl-meshoptimizer")
    api("org.lwjgl", "lwjgl-nanovg")
    api("org.lwjgl", "lwjgl-nfd")
    api("org.lwjgl", "lwjgl-nuklear")
    api("org.lwjgl", "lwjgl-odbc")
    api("org.lwjgl", "lwjgl-openal")
    api("org.lwjgl", "lwjgl-opencl")
    api("org.lwjgl", "lwjgl-opengl")
    api("org.lwjgl", "lwjgl-opengles")
    api("org.lwjgl", "lwjgl-openvr")
    api("org.lwjgl", "lwjgl-openxr")
    api("org.lwjgl", "lwjgl-opus")
    api("org.lwjgl", "lwjgl-ovr")
    api("org.lwjgl", "lwjgl-par")
    api("org.lwjgl", "lwjgl-remotery")
    api("org.lwjgl", "lwjgl-rpmalloc")
    api("org.lwjgl", "lwjgl-shaderc")
    api("org.lwjgl", "lwjgl-spvc")
    api("org.lwjgl", "lwjgl-sse")
    api("org.lwjgl", "lwjgl-stb")
    api("org.lwjgl", "lwjgl-tinyexr")
    api("org.lwjgl", "lwjgl-tinyfd")
    api("org.lwjgl", "lwjgl-tootle")
    api("org.lwjgl", "lwjgl-vma")
    api("org.lwjgl", "lwjgl-vulkan")
    api("org.lwjgl", "lwjgl-xxhash")
    api("org.lwjgl", "lwjgl-yoga")
    api("org.lwjgl", "lwjgl-zstd")
    runtimeOnly("org.lwjgl", "lwjgl", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-assimp", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-bgfx", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-freetype", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-glfw", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-harfbuzz", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-hwloc", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-jemalloc", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-ktx", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-libdivide", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-llvm", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-lmdb", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-lz4", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-meow", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-meshoptimizer", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-nanovg", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-nfd", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-nuklear", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-openal", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-opengl", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-opengles", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-openvr", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-openxr", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-opus", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-ovr", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-par", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-remotery", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-rpmalloc", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-shaderc", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-spvc", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-sse", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-stb", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-tinyexr", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-tinyfd", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-tootle", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-vma", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-xxhash", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-yoga", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-zstd", classifier = lwjglNatives)
    api("org.joml", "joml", jomlVersion)
    api("org.lwjglx", "lwjgl3-awt", `lwjgl3-awtVersion`)
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(18)
}