package com.aalzatea.courses.linkedinlearning.challenges.kotlin.exercise3.controllers.beans

import com.aalzatea.courses.linkedinlearning.challenges.kotlin.exercise3.domain.Booking
import com.aalzatea.courses.linkedinlearning.challenges.kotlin.exercise3.domain.Performance
import com.aalzatea.courses.linkedinlearning.challenges.kotlin.exercise3.domain.Seat

class CheckAvailabilityBackingBean() {
    var selectedSeatNum: Int = 1
    var selectedSeatRow: Char = 'A'
    var selectedPerformance: Long? = null
    var customerName: String = ""

    var available : Boolean? = null
    var seat : Seat? = null
    var performance: Performance? = null
    var booking : Booking? = null
}