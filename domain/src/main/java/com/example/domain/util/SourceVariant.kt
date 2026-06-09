package com.example.domain.util

enum class SourceVariant(val source: String) {
    LOCAL("local"), //TODO -> Вынести в константы именно стринги
    REMOTE("remote")
}