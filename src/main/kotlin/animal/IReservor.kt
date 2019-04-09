package main.kotlin.animal

import java.time.LocalDateTime

interface IReservor {
    val name: String
    val reservedAt: LocalDateTime
}