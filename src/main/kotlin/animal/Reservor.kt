package main.kotlin.animal

import java.time.LocalDateTime

data class Reservor(override val name: String, override val reservedAt: LocalDateTime) : IReservor