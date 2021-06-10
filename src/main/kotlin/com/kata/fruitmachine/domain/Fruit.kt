package com.kata.fruitmachine.domain

import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

enum class Fruit(val price: BigDecimal, val discount: BigDecimal, val numberOfOccurencesForDiscount: Int, val translations: Set<String>) {
    POMMES(
            from(100.00),
            from(0.0),
            1,
            setOf("POMMES")
    ),
    APPLES(
            from(100.00),
            from(100.0),
            3,
            setOf("APPLES")
    ),
    MELE(
            from(100.00),
            from(100.0),
            2,
            setOf("MELE")
    ),
    CERISES(
            from(75.0),
            from(20.00),
            2,
            setOf("CERISES")
    ),
    BANANES(
            from(150.0),
            from(150.00),
            2,
            setOf("BANANES")
    );

    companion object {
        fun from(fruit: String): Optional<Fruit> {
            return values()
                    .find { it.translations.contains(fruit.toUpperCase()) }
                    ?.let { Optional.of(it) }
                    ?: Optional.empty()
        }
    }
}

fun from(amount: Double): BigDecimal {
  return BigDecimal(amount).setScale(2, RoundingMode.HALF_UP)
}