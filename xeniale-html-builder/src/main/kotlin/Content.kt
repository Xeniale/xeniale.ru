package com.github.kshekhovtsova.xenialehtmlbuilder

data class PageData(
    val title: String,
    val language: String,
    val theme: String,
    val customStylesheets: Set<String>
)

data class NavbarData(
    val brandImg: ImgData,
    val brandText: String,
    val brandHref: String,
    val navItems: Set<NavItemData>
)

data class NavItemData(val name: String, val isActive: Boolean, val href: String)

data class ImgData(val src: String, val alt: String, val width: String, val height: String)