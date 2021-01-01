package machine

import java.util.*

val scanner = Scanner(System.`in`)

fun main() {

    val coffeeMachine = CoffeeMachine()

    while (coffeeMachine.state != CoffeeMachine.STATE.EXIT) {
        when (CoffeeMachine.STATE.getStateByName(askAction())) {
            CoffeeMachine.STATE.BUY -> {
                coffeeMachine.state = CoffeeMachine.STATE.BUY
                buy(coffeeMachine)
            }

            CoffeeMachine.STATE.FILL -> {
                coffeeMachine.state = CoffeeMachine.STATE.FILL
                fill(coffeeMachine)
            }

            CoffeeMachine.STATE.TAKE -> {
                coffeeMachine.state = CoffeeMachine.STATE.TAKE
                coffeeMachine.takeMoney()
            }

            CoffeeMachine.STATE.REMAINING -> {
                coffeeMachine.state = CoffeeMachine.STATE.REMAINING
                println(coffeeMachine)
            }

            CoffeeMachine.STATE.EXIT -> {
                coffeeMachine.state = CoffeeMachine.STATE.EXIT
            }

            CoffeeMachine.STATE.NULL -> {
                coffeeMachine.state = CoffeeMachine.STATE.NULL
            }
        }
    }
}

class CoffeeMachine(
    private var water: Int = 400,
    private var milk: Int = 540,
    private var beans: Int = 120,
    private var cup: Int = 9,
    private var money: Int = 550
) {
    enum class STATE(val names: String) {
        BUY("buy"),
        FILL("fill"),
        TAKE("take"),
        REMAINING("remaining"),
        EXIT("exit"),
        NULL("");

        companion object {
            fun getStateByName(named: String): STATE {
                for (enum in values()) {
                    if (enum.names == named) return enum
                }

                return NULL
            }
        }
    }

    var state: STATE = STATE.NULL

    fun fill(
        filledWater: Int = 0,
        filledMilk: Int = 0,
        filledBeans: Int = 0,
        filledCup: Int = 0,
    ) {
        this.water += filledWater
        this.milk += filledMilk
        this.beans += filledBeans
        this.cup += filledCup
    }

    fun takeMoney(): Int {
        val takenMoney = this.money
        this.money = 0

        return takenMoney
    }

    fun minusQuantity(
        takenWater: Int = 0,
        takenMilk: Int = 0,
        takenBeans: Int = 0,
        addedMoney: Int = 0,
    ) {
        when {
            takenWater > this.water -> println("Sorry not enough water!")
            takenMilk > this.milk -> println("Sorry not enough milk!")
            takenBeans > this.beans -> println("Sorry not enough beans!")
            this.cup == 0 -> println("Sorry not enough cup!")
            else -> {
                this.water -= takenWater
                this.milk -= takenMilk
                this.beans -= takenBeans
                this.cup--
                this.money += addedMoney
                println("I have enough resources, making you a coffee!")
            }
        }
    }

    override fun toString(): String {
        return """
            
            The coffee machine has:
            ${this.water} of water
            ${this.milk} of milk
            ${this.beans} of coffee beans
            ${this.cup} of disposable cups
            ${this.money} of money
            
        """.trimIndent()
    }
}

enum class COFFEE(
    val water: Int = 0,
    val milk: Int = 0,
    val beans: Int = 0,
    val money: Int = 0
) {
    ESPRESSO(
        water = 250,
        beans = 16,
        money = 4
    ),

    LATTE(
        water = 350,
        milk = 75,
        beans = 20,
        money = 7
    ),

    CAPPUCCINO(
        water = 200,
        milk = 100,
        beans = 12,
        money = 6
    )
}

fun askAction(): String {
    println("Write action (buy, fill, take, remaining, exit) : > ")

    return scanner.nextLine()
}

fun buy(coffeeMachine: CoffeeMachine) {
    println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
    val coffee: COFFEE

    when (scanner.nextLine()) {
        "1" -> {
            coffee = COFFEE.ESPRESSO
            coffeeMachine.minusQuantity(
                takenWater = coffee.water,
                takenBeans = coffee.beans,
                addedMoney = coffee.money,
            )
        }

        "2" -> {
            coffee = COFFEE.LATTE
            coffeeMachine.minusQuantity(
                takenWater = coffee.water,
                takenMilk = coffee.milk,
                takenBeans = coffee.beans,
                addedMoney = coffee.money,
            )
        }

        "3" -> {
            coffee = COFFEE.CAPPUCCINO
            coffeeMachine.minusQuantity(
                takenWater = coffee.water,
                takenMilk = coffee.milk,
                takenBeans = coffee.beans,
                addedMoney = coffee.money,
            )
        }

        else -> return
    }
}

fun fill(coffeeMachine: CoffeeMachine) {
    println("Write how many ml of water do you want to add: > ")
    val water = scanner.nextInt()
    println("Write how many ml of milk do you want to add: > ")
    val milk = scanner.nextInt()
    println("Write how many grams of coffee beans do you want to add: > ")
    val beans = scanner.nextInt()
    println("Write how many disposable cups of coffee do you want to add: > ")
    val cup = scanner.nextInt()

    coffeeMachine.fill(water, milk, beans, cup)
}


// alternate solution

//package machine
//
//import kotlin.system.exitProcess
//
//class CoffeeMachine {
//    object Supplies {
//        var water = 400
//        var milk = 540
//        var beans = 120
//        var cups = 9
//        var dollars = 550
//    }
//
//    enum class States(val prompt: String) {
//        CHOOSE_ACTION("Write action (buy, fill, take, remaining, exit): "),
//        CHOOSE_COFFEE_VARIANT("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: "),
//        ENTER_FILL_WATER_AMOUNT("Write how many ml of water do you want to add: "),
//        ENTER_FILL_MILK_AMOUNT("Write how many ml of milk do you want to add: "),
//        ENTER_FILL_BEANS_AMOUNT("Write how many grams of coffee beans do you want to add: "),
//        ENTER_FILL_CUPS_AMOUNT("Write how many disposable cups of coffee do you want to add "),
//    }
//
//    private var currentState: States = States.CHOOSE_ACTION
//
//    fun printPrompt() {
//        print(currentState.prompt)
//    }
//
//    fun handleInput(input: String) {
//        if (input == "exit") {
//            exitProcess(1)
//        }
//
//        when (currentState) {
//            States.CHOOSE_ACTION -> handleChooseAction(input)
//            States.CHOOSE_COFFEE_VARIANT -> handleChooseCoffeeVariant(input)
//            States.ENTER_FILL_WATER_AMOUNT -> handleFill("water", input)
//            States.ENTER_FILL_MILK_AMOUNT -> handleFill("milk", input)
//            States.ENTER_FILL_BEANS_AMOUNT -> handleFill("beans", input)
//            States.ENTER_FILL_CUPS_AMOUNT -> handleFill("cups", input)
//        }
//    }
//
//    private fun handleChooseAction(input: String) {
//        when (input) {
//            "buy" -> currentState = States.CHOOSE_COFFEE_VARIANT
//            "fill" -> currentState = States.ENTER_FILL_WATER_AMOUNT
//            "take" -> handleTakeCommand()
//            "remaining" -> printMachineState()
//        }
//    }
//
//    private fun printMachineState() {
//        println(
//            """
//        The coffee machine has:
//        ${Supplies.water} of water
//        ${Supplies.milk} of milk
//        ${Supplies.beans} of coffee beans
//        ${Supplies.cups} of disposable cups
//        $${Supplies.dollars} of money
//        """.trimIndent()
//        )
//
//        currentState = States.CHOOSE_ACTION
//    }
//
//    private fun buyCoffee(price: Int, neededWater: Int, neededMilk: Int, neededCoffeeBeans: Int) {
//        when {
//            Supplies.cups < 1 -> println("Sorry, not enough cups!")
//            neededWater > Supplies.water -> println("Sorry, not enough water!")
//            neededMilk > Supplies.milk -> println("Sorry, not enough milk!")
//            neededCoffeeBeans > Supplies.beans -> println("Sorry, not enough coffee beans!")
//            else -> {
//                println("I have enough resources, making you a coffee!")
//
//                Supplies.dollars += price
//                Supplies.cups -= 1
//                Supplies.water -= neededWater
//                Supplies.milk -= neededMilk
//                Supplies.beans -= neededCoffeeBeans
//            }
//        }
//
//        currentState = States.CHOOSE_ACTION
//    }
//
//    private fun handleChooseCoffeeVariant(input: String) {
//        when (input) {
//            "1" -> buyCoffee(4, 250, 0, 16)
//            "2" -> buyCoffee(7, 350, 75, 20)
//            "3" -> buyCoffee(6, 200, 100, 12)
//            "back" -> {
//                currentState = States.CHOOSE_ACTION
//            }
//        }
//    }
//
//    private fun handleFill(property: String, input: String) {
//        when (property) {
//            "water" -> {
//                Supplies.water += input.toInt()
//                currentState = States.ENTER_FILL_MILK_AMOUNT
//            }
//            "milk" -> {
//                Supplies.milk += input.toInt()
//                currentState = States.ENTER_FILL_BEANS_AMOUNT
//            }
//            "beans" -> {
//                Supplies.beans += input.toInt()
//                currentState = States.ENTER_FILL_CUPS_AMOUNT
//            }
//            "cups" -> {
//                Supplies.cups += input.toInt()
//                currentState = States.CHOOSE_ACTION
//            }
//        }
//    }
//
//    private fun handleTakeCommand() {
//        println("I gave you $${Supplies.dollars}")
//
//        Supplies.dollars = 0
//
//        currentState = States.CHOOSE_ACTION
//    }
//}
//
//fun main() {
//    val coffeeMachine = CoffeeMachine()
//
//    while (true) {
//        coffeeMachine.printPrompt()
//        coffeeMachine.handleInput(readLine()!!)
//    }
//}
