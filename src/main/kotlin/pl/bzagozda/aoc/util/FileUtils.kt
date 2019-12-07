package pl.bzagozda.aoc.util

import java.io.File

fun String.getResourceFile(): File =
    File(this::javaClass::class.java.getResource(this).toURI())