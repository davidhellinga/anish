package main.kotlin.animal

import main.kotlin.webshop.ISellable
import java.time.LocalDateTime

abstract class Animal(override val name: String, val gender: Gender, @Transient override val price: Double) :
    ISellable {
    var reservedBy: Reservor? = null
        private set

    fun reserve(reservedBy: String, reservedAt: LocalDateTime = LocalDateTime.now()): Boolean {
        if (this.reservedBy != null) return false
        this.reservedBy = Reservor(reservedBy, reservedAt)
        return true
    }

    override fun toString(): String {
        val reserved: String = reservedBy?.let { "reserved by ${reservedBy!!.name}" } ?: "not reserved"
        return "$name, $gender, $reserved, $price"
    }

    fun setName() {

    }

    fun unreserve(): Boolean {
        if (this.reservedBy == null) return false
        this.reservedBy = null
        return true
    }
}