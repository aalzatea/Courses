package com.aalzatea.courses.linkedinlearning.challenges.kotlin.exercise3.controllers

import com.aalzatea.courses.linkedinlearning.challenges.kotlin.exercise3.controllers.beans.CheckAvailabilityBackingBean
import com.aalzatea.courses.linkedinlearning.challenges.kotlin.exercise3.data.PerformanceRepository
import com.aalzatea.courses.linkedinlearning.challenges.kotlin.exercise3.data.SeatRepository
import com.aalzatea.courses.linkedinlearning.challenges.kotlin.exercise3.domain.Seat
import com.aalzatea.courses.linkedinlearning.challenges.kotlin.exercise3.services.BookingService
import com.aalzatea.courses.linkedinlearning.challenges.kotlin.exercise3.services.TheaterService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView


@Controller
class MainController constructor(
    val theaterService: TheaterService,
    val bookingService: BookingService,
    val seatRepository: SeatRepository,
    val performanceRepository: PerformanceRepository) {

    @GetMapping
    fun homePage() : ModelAndView {
        val model = mapOf ("bean" to CheckAvailabilityBackingBean(),
                            "performances" to performanceRepository.findAll(),
                            "seatNums" to 1..36,
                            "seatRows" to 'A'..'O')

        return ModelAndView("seatBooking", model)
    }

    @RequestMapping(value = ["checkAvailability"], method= [RequestMethod.POST])
    fun checkAvailability(bean : CheckAvailabilityBackingBean) : ModelAndView {
        val selectedSeat : Seat = bookingService.findSeat(bean.selectedSeatNum, bean.selectedSeatRow)!!
        val selectedPerformance = performanceRepository.findById(bean.selectedPerformance!!).get()

        bean.seat = selectedSeat
        bean.performance = selectedPerformance

        val available = bookingService.isSeatFree(selectedSeat, selectedPerformance)
        bean.available = available

        if (!available) {
            bean.booking = bookingService.findBooking(selectedSeat, selectedPerformance)
        }

        val model = mapOf ("bean" to bean,
                            "performances" to performanceRepository.findAll(),
                            "seatNums" to 1..36,
                            "seatRows" to 'A'..'O')

        return ModelAndView("seatBooking", model)
    }

    @PostMapping(value = ["booking"])
    fun bookASeat(bean: CheckAvailabilityBackingBean) : ModelAndView {
        val booking = bookingService.reserveSeat(bean.seat!!, bean.performance!!, bean.customerName)
        return ModelAndView("bookingConfirmed", "booking", booking)
    }

    @RequestMapping("bootstrap")
    fun createInitialData() : ModelAndView {
        //create the data and save it to the database
        val seats = theaterService.seats
        seatRepository.saveAll(seats)
        return homePage()
    }
}