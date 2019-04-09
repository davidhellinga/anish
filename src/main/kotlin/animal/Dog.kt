package main.kotlin.animal

import main.kotlin.AnimalShelter
import main.kotlin.webshop.ISellable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.temporal.ChronoUnit
import kotlin.math.max
import kotlin.reflect.KClass

class Dog(
    name: String,
    gender: Gender,
    override val price: Double = max(
        50.0,
        (500.0 - (AnimalShelter.webshop.getProductL().groupBy<ISellable, KClass<*>> { it.javaClass.kotlin }[Dog::class]?.count()?.toDouble()?.times(
            50.0
        ) ?: 0.0))
    )
) : Animal(name, gender, price) {

    private val lastWalk: LocalDateTime = LocalDateTime.now()
    val needsWalk: Boolean
        get() = ChronoUnit.DAYS.between(lastWalk, LocalDateTime.now()) >= 1

    override fun toString(): String {
        return super.toString() + ", last walk: ${lastWalk.format(
            DateTimeFormatter.ofLocalizedDateTime(
                FormatStyle.LONG,
                FormatStyle.MEDIUM
            )
        )}"
    }
}