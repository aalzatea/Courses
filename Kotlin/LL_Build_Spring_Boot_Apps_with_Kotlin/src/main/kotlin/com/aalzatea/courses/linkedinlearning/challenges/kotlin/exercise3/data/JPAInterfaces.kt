package com.aalzatea.courses.linkedinlearning.challenges.kotlin.exercise3.data

import com.aalzatea.courses.linkedinlearning.challenges.kotlin.exercise3.domain.Booking
import com.aalzatea.courses.linkedinlearning.challenges.kotlin.exercise3.domain.Performance
import com.aalzatea.courses.linkedinlearning.challenges.kotlin.exercise3.domain.Seat
import org.springframework.data.jpa.repository.JpaRepository

interface SeatRepository : JpaRepository<Seat, Long>

interface BookingRepository: JpaRepository<Booking, Long>

interface PerformanceRepository : JpaRepository<Performance, Long>