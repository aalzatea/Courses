package com.aalzatea.courses.linkedinlearning.challenges.kotlin.exercise3.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "SEATS")
data class Seat(@Id @GeneratedValue(strategy = GenerationType.AUTO)
                val id: Long,
                @Column(name = "`ROW`")
                val row: Char,
                val num: Int,
                val price: BigDecimal,
                val description: String) {

    override fun toString(): String = "Seat $row-$num $$price ($description)"
}