package com.aalzatea.courses.linkedinlearning.challenges.kotlin.exercise3.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "PERFORMANCES")
data class Performance constructor(@Id
                                   @GeneratedValue(strategy = GenerationType.AUTO)
                                   val id: Long,
                                   val title: String) {

    @OneToMany(mappedBy = "performance")
    lateinit var bookings: List<Booking>
}