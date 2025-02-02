package com.github.kshekhovtsova.xenialehtmlbuilder

import kotlinx.html.HtmlBlockTag
import kotlinx.html.body
import kotlinx.html.dom.createHTMLDocument
import kotlinx.html.dom.serialize
import kotlinx.html.head
import kotlinx.html.html
import kotlinx.html.link
import kotlinx.html.meta
import kotlinx.html.script
import kotlinx.html.title

fun baseHtmlPage(
    pageData: PageData,
    block: HtmlBlockTag.() -> Unit
): String {
    val html = createHTMLDocument().html {
        attributes["lang"] = pageData.language
        attributes["data-bs-theme"] = pageData.theme
        head {
            title { +pageData.title }
            meta { charset = "utf-8" }
            meta {
                attributes["http-equiv"] = "X-UA-Compatible"
                content = "IE=edge"
            }
            meta { name = "viewport"; content = "width=device-width, initial-scale=1" }
            link(href = "https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css", "stylesheet") {
                attributes["integrity"] = "sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
                attributes["crossorigin"] = "anonymous"
            }
            pageData.customStylesheets.forEach {
                link(href = it, rel = "stylesheet")
            }
        }
        body {
            block()
            script("", "https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js") {
                attributes["integrity"] = "sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
                attributes["crossorigin"] = "anonymous"
            }
        }
    }
    return html.serialize(true)
}