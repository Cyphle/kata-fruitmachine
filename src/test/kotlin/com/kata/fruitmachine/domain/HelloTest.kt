package com.kata.fruitmachine.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class HelloTest {
    private val hello = Hello()
    @Test
    internal fun `should say hello`() {
        val hello = hello.say()

        assertThat(hello).isEqualTo("Hello")
    }
}