package com.kata.fruitmachine

import com.kata.fruitmachine.domain.CashMachine
import java.io.IOException

import java.io.BufferedReader
import java.io.InputStreamReader


class FruitmachineApplication

fun main(args: Array<String>) {
	val cashMachine = CashMachine()

	val br = BufferedReader(InputStreamReader(System.`in`))
	try {
		var exit = false
		while (!exit) {
			println("Enter a fruit:")
			val fruit = br.readLine().toString()

			if (fruit.toUpperCase() == "EXIT") {
				exit = true
			}
		}
	} catch (ioe: IOException) {
		println(ioe)
	}
}
