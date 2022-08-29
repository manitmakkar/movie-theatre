package com.jpmc.theater;

public class MovieDiscount implements Discount {
    private static int MOVIE_CODE_SPECIAL = 1;


    @Override
    public double getDiscount(Showing showing) {
        return MOVIE_CODE_SPECIAL == showing.getMovie().getSpecialCode() ? 0.2 * showing.getMovie().getTicketPrice() : 0;
    }

}
