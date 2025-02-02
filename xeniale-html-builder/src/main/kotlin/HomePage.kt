package com.github.kshekhovtsova.xenialehtmlbuilder

import kotlinx.html.*

private val loremIpsum = """
    Есть много вариантов Lorem Ipsum, но большинство из них имеет не всегда приемлемые модификации,
                например,
                юмористические вставки или слова, которые даже отдалённо не напоминают латынь. Если вам нужен Lorem
                Ipsum для серьёзного проекта, вы наверняка не хотите какой-нибудь шутки, скрытой в середине абзаца.
                Также все другие известные генераторы Lorem Ipsum используют один и тот же текст, который они просто
                повторяют, пока не достигнут нужный объём. Это делает предлагаемый здесь генератор единственным
                настоящим Lorem Ipsum генератором. Он использует словарь из более чем 200 латинских слов, а также
                набор
                моделей предложений. В результате сгенерированный Lorem Ipsum выглядит правдоподобно, не имеет
                повторяющихся абзацей или "невозможных" слов.
""".trimIndent()

val pageEnglishDark = PageData(
    language = "en",
    theme = "dark",
    title = "Xeniale",
    customStylesheets = setOf("css/style.css")
)

val pageEnglishLight = pageEnglishDark.copy(theme = "light")

val homePageNavbarData = NavbarData(
    brandImg = ImgData(
        src = "logo/logo_round_xe_favicon.ico",
        alt = "Logo",
        width = "30",
        height = "30"
    ),
    brandText = "Xeniale",
    brandHref = "#",
    navItems = setOf(
        NavItemData("Home", true, "#"),
        NavItemData("CV", false, "#"),
        NavItemData("Blog", false, "#"),
        NavItemData("Contact", false, "#")
    )
)

val homePageEnglishDark by lazy {
    basePage(basePageData = pageEnglishDark, navbarData = homePageNavbarData) {
        pageContent()
    }
}

val homePageEnglishLight by lazy {
    basePage(basePageData = pageEnglishLight, navbarData = homePageNavbarData) {
        pageContent()
    }
}

private fun HtmlBlockTag.pageContent() {
    div(classes = "container") {
        div(classes = "row bg-overlay d-flex justify-content-center align-items-center") {
            div(classes = "col-12 col-sm-12 col-md-4 col-lg-8 col-xl-8") {
                img(classes = "rounded-img", src = "css/images/ksenia-pic.jpg", alt = "Photo of Xenia Shekhovtsova")
                h2 {
                    +"Xeniale (Xenia Shekhovtsova)"
                }
                repeat(4) {
                    p {
                        +loremIpsum
                    }
                }
            }
        }
    }
}

fun basePage(basePageData: PageData, navbarData: NavbarData, block: HtmlBlockTag.() -> Unit): String {
    return baseHtmlPage(basePageData) {
        nav(classes = "navbar sticky-top navbar-expand-lg bg-body-tertiary") {
            div(classes = "container") {
                a(classes = "navbar-brand", href = navbarData.brandHref) {
                    img(
                        src = navbarData.brandImg.src,
                        alt = navbarData.brandImg.alt,
                        classes = "d-inline-block align-text-top"
                    ) {
                        attributes["width"] = navbarData.brandImg.width
                        attributes["height"] = navbarData.brandImg.height
                    }
                    +navbarData.brandText
                }
                button(classes = "navbar-toggler") {
                    attributes["type"] = "button"
                    attributes["data-bs-toggle"] = "collapse"
                    attributes["data-bs-target"] = "#${Ids.NAVBAR}"
                    attributes["aria-controls"] = Ids.NAVBAR
                    attributes["aria-expanded"] = "false"
                    attributes["aria-label"] = "Toggle navigation"
                }
                div(classes = "collapse navbar-collapse") {
                    id = Ids.NAVBAR
                    ul(classes = "navbar-nav me-auto mb-2 mb-lg-0 justify-content-center") {
                        navbarData.navItems.forEach { navItem(it.name, it.isActive, it.href) }
                    }
                }
            }
        }
        block()
    }
}

private fun UL.navItem(name: String, isActive: Boolean, href: String) {
    li(classes = "nav-item") {
        a(href = href) {
            if (isActive) {
                attributes["aria-current"] = "page"
                classes = setOf("nav-link active")
            } else {
                classes = setOf("nav-link")
            }
            +name
        }
    }
}

