package com.kata.fruitmachine.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class IntegrationTest {
    @Test
    internal fun `should add multiple fruits`() {
        val machine = CashMachine()

        machine.addFruit(Fruit.CERISES)
        machine.addFruit(Fruit.POMMES)
        machine.addFruit(Fruit.CERISES)
        machine.addFruit(Fruit.BANANES)
        machine.addFruit(Fruit.POMMES)

        assertThat(machine.getTotalByFruit()).containsExactlyInAnyOrder(
                FruitEvent(Fruit.CERISES, from(150.0)),
                FruitEvent(Fruit.POMMES, from(200.0)),
                FruitEvent(Fruit.BANANES, from(150.0))
        )
        assertThat(machine.getTotal()).isEqualTo(from(500.0))
    }

    @Test
    internal fun `should add discount multiple fruits`() {
        val machine = CashMachine()

        machine.addFruit(Fruit.CERISES)
        machine.addFruit(Fruit.POMMES)
        machine.addFruit(Fruit.CERISES)
        machine.addFruit(Fruit.BANANES)
        machine.addFruit(Fruit.CERISES)
        machine.addFruit(Fruit.CERISES)
        machine.addFruit(Fruit.POMMES)

        assertThat(machine.getTotal()).isEqualTo(from(610.0))
    }

    @Test
    internal fun `iteration 3`() {
        val machine = CashMachine()

        machine.addFruit(Fruit.CERISES)
        machine.addFruit(Fruit.POMMES)
        machine.addFruit(Fruit.CERISES)
        machine.addFruit(Fruit.BANANES)
        machine.addFruit(Fruit.POMMES)
        machine.addFruit(Fruit.BANANES)
        machine.addFruit(Fruit.CERISES)

        assertThat(machine.getTotal()).isEqualTo(from(545.00))
    }

    @Test
    internal fun `iteration 4`() {
        val machine = CashMachine()

        machine.addFruit("Cerises")
        machine.addFruit("Apples")
        machine.addFruit("Cerises")
        machine.addFruit("Bananes")
        machine.addFruit("Pommes")
        machine.addFruit("Mele")

        assertThat(machine.getTotal()).isEqualTo(from(580.00))
    }

    @Test
    internal fun `iteration 5`() {
        val machine = CashMachine()

        machine.addFruits("Cerises, Apples")
        machine.addFruits("Cerises")
        machine.addFruits("Apples, Pommes, Bananes")
        machine.addFruits("Apples, Pommes")
        machine.addFruits("Mele")
        machine.addFruits("Pommes")

        val totalByFruit = machine.getTotalByFruit()

        assertThat(machine.getTotal()).isEqualTo(from(880.00))
    }
}