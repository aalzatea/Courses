package com.aalzatea.courses.linkedinlearning.challenges.kotlin.exercise2

private enum class SeatType(val price: Double, val description: String) {
    BACK_ROW(14.50, "Back row"),
    CHEAPER_SEAT(14.50, "Cheaper seat"),
    RESTRICTED_VIEW(16.50, "Restricted View"),
    BEST_VIEW(21.00, "Best view"),
    STANDARD_SEAT(18.00, "Standard seat");
}

class Theater {

    // SEAT PRICES:
    // Seats in rows 14 and 15 cost 14.50
    // Seats in rows 1 to 13 and numbered 1 to 3 or 34 to 36 cost 16.50
    // All other seats in row 1 cost 21.00
    // All other seats cost 18.00

    // SEAT DESCRIPTIONS:
    // Seats in row 15: "Back row"
    // Seats in row 14: "Cheaper seat"
    // All other rows, seats 1 to 3 and 34 to 36: "Restricted View"
    // All other seats in rows 1 and 2 "Best view"
    // All other seats: "Standard seat"

    companion object {
        private const val ROWS: Int = 15
        private const val COLUMNS: Int = 36
    }

    private var hiddenSeats: List<Seat>? = null

    val seats
        get() = hiddenSeats ?: listOf()//THIS MUST BE AN IMMUTABLE LIST

    init {
        hiddenSeats = fillSeats()
    }

    private fun fillSeats(): List<Seat> {
        val seats = mutableListOf<Seat>()

        for (row in 1..ROWS) {
            for (column in 1..COLUMNS) {
                val seat = createSeat(row, column)
                seats.add(seat)
            }
        }

        return seats.toList()
    }

    private fun createSeat(row: Int, column: Int): Seat {
        val seatType = when {
            row in 1..2 && column in 4..33 -> SeatType.BEST_VIEW
            row in 3..13 && column in 4..33 -> SeatType.STANDARD_SEAT
            row in 1..13 && (column in 1..3 || column in 34..36) -> SeatType.RESTRICTED_VIEW
            row == 14 -> SeatType.CHEAPER_SEAT
            else -> SeatType.BACK_ROW
        }

        return Seat(row, column, seatType.price.toBigDecimal(), seatType.description)
    }
}