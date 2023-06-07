package net.benfro.lab.fpbook.ch1

data class Coffee(val brand: String)

data class CreditCard(val number: String) {
    fun charge(amount: Float) = "Charged $amount on credit card #$number"

}

data class Charge(val amount: Float)
data class Charge2(val cc: CreditCard, val amount: Float)

val menu = mapOf("Latte" to 2.75f, "Espresso" to 3.05f, "American" to 2.35f)

class Cafe {

    fun buyCoffee(cc: CreditCard): Coffee {
        val cup = Coffee("Latte")
        cc.charge(2.55f)
        return cup
    }

    fun buyCoffee2(cc: CreditCard): Coffee {
        val cup = Coffee("Latte")
        val cc = Charge2(cc, 2.75f)
        return cup
    }

    fun buyCoffee3(coffeeKind: String, cc: CreditCard): Pair<Coffee, Charge2> {
        val cup = Coffee(coffeeKind)
        val charge2 = Charge2(cc, menu[coffeeKind] ?: 1.00f)
        return Pair(cup, charge2)
    }

}






