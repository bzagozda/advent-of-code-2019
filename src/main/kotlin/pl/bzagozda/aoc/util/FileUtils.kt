package pl.bzagozda.aoc.util

import java.io.File

fun String.getResource(): File =
    File(this::javaClass::class.java.getResource(this).toURI())