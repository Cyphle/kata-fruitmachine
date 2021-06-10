package com.kata.fruitmachine.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CashMachineTest {
    @Test
    internal fun `should get total by fruit`() {
        val machine = CashMachine()

        machine.addFruit(Fruit.POMMES)
        machine.addFruit(Fruit.POMMES)
        machine.addFruit(Fruit.CERISES)

        assertThat(machine.getTotalByFruit()).containsExactlyInAnyOrder(
                FruitEvent(Fruit.POMMES, from(200.00)),
                FruitEvent(Fruit.CERISES, from(75.0))
        )
    }

    @Test
    internal fun `should add discount of 20 every 2 cerises`() {
        val machine = CashMachine()

        machine.addFruit(Fruit.CERISES)
        machine.addFruit(Fruit.CERISES)

        assertThat(machine.getTotalByFruit()).containsExactlyInAnyOrder(
                FruitEvent(Fruit.CERISES, from(120.0))
        )
        assertThat(machine.getTotal()).isEqualTo(from(120.0))
    }

    @Test
    internal fun `should find fruit by name pomme`() {
        val machine = CashMachine()

        machine.addFruit("Apples")

        assertThat(machine.getTotal()).isEqualTo(from(100.00))
    }

    @Test
    internal fun `should find fruit by name cerise`() {
        val machine = CashMachine()

        machine.addFruit("Cerises")

        assertThat(machine.getTotal()).isEqualTo(from(75.00))
    }

    @Test
    internal fun `should find fruit by name banane`() {
        val machine = CashMachine()

        machine.addFruit("Bananes")

        assertThat(machine.getTotal()).isEqualTo(from(150.00))
    }

    @Test
    internal fun `should find fruits by name banane`() {
        val machine = CashMachine()

        machine.addFruits("Bananes, Cerises")

        assertThat(machine.getTotal()).isEqualTo(from(225.00))
    }
}