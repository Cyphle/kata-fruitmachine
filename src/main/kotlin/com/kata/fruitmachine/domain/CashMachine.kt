package com.kata.fruitmachine.domain

import java.math.BigDecimal

class CashMachine {
    private val fruits: MutableList<FruitEvent> = mutableListOf()
    private var totalByFruit: Set<FruitEvent> = setOf()

    fun addFruit(fruit: Fruit) {
        fruits.add(FruitEvent(fruit, fruit.price))
        totalByFruit = consolidateByFruit()
    }

    fun addFruit(fruit: String) {
        Fruit.from(fruit)
                .map {
                    fruits.add(FruitEvent(it, it.price))
                    totalByFruit = consolidateByFruit()
                }
    }

    fun addFruits(fruitsToAdd: String) {
        val f = fruitsToAdd
                .split(",")
                .map { it.trim() }
        f.forEach { addFruit(it) }
    }

    fun getTotalByFruit(): Set<FruitEvent> {
        return totalByFruit
    }

    private fun consolidateByFruit(): Set<FruitEvent> {
        return fruits
                .groupBy { it.fruit }
                .map { calculateTotal(it.key, it.value) }
                .toSet()
    }

    private fun calculateTotal(fruit: Fruit, events: List<FruitEvent>): FruitEvent {
        val discount = calculateDiscount(fruit, events)
        val total = events.fold(BigDecimal(0.0)) { acc, current ->
            acc.add(current.fruit.price)
        }
        return FruitEvent(fruit, total.minus(discount))
    }

    private fun calculateDiscount(fruit: Fruit, events: List<FruitEvent>): BigDecimal {
        val totalPairs = events.size / fruit.numberOfOccurencesForDiscount
        val discount = fruit.discount
        return discount.multiply(BigDecimal(totalPairs))
    }

    fun getTotal(): BigDecimal {
        return totalByFruit
                .map { it.total }
                .fold(BigDecimal(0.0)) { acc, current ->
                    acc.add(current)
                }
    }
}

data class FruitEvent(val fruit: Fruit, val total: BigDecimal = BigDecimal(0.0))