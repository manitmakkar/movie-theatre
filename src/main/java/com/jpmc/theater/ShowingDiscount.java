package com.jpmc.theater;

public class ShowingDiscount implements Discount {


    @Override
    public double getDiscount(Showing showing) {

        double showingStartTimeDiscount = 0;
        double showingSequenceDiscount = 0;

        if (showing.getStartTime().getHour() >= 11 && showing.getStartTime().getHour() <= 16)
            showingStartTimeDiscount = 0.25 * showing.getMovie().getTicketPrice();

        if (showing.getSequenceOfTheDay() == 1) {
            showingSequenceDiscount = 3; // $3 discount for 1st show
        } else if (showing.getSequenceOfTheDay() == 2) {
            showingSequenceDiscount = 2; // $2 discount for 2nd show
        }
        else if (showing.getSequenceOfTheDay() == 7) {
            showingSequenceDiscount = 1; // $2 discount for 2nd show
        }

        // biggest discount wins
        return Math.max(showingStartTimeDiscount, showingSequenceDiscount);

    }
}
