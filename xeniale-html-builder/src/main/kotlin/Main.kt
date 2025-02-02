package com.github.kshekhovtsova.xenialehtmlbuilder

import java.nio.file.Files
import java.nio.file.Paths

fun main() {
    rewriteHtmlFile("index.html", homePageEnglishDark)
    rewriteHtmlFile("home-light.html", homePageEnglishLight)
}

fun rewriteHtmlFile(fileName: String, htmlText: String) {
    Files.write(Paths.get(fileName), htmlText.toByteArray(Charsets.UTF_8))
}
