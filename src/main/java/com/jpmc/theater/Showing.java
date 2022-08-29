package com.jpmc.theater;

import org.reflections.Reflections;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.reflections.scanners.Scanners.SubTypes;

public class Showing {

    private Movie movie;
    private int sequenceOfTheDay;
    private LocalDateTime showStartTime;


    public Showing(Movie movie, int sequenceOfTheDay, LocalDateTime showStartTime) {
        this.movie = movie;
        this.sequenceOfTheDay = sequenceOfTheDay;
        this.showStartTime = showStartTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getStartTime() {
        return showStartTime;
    }

    public boolean isSequence(int sequence) {
        return this.sequenceOfTheDay == sequence;
    }

    public double getMovieFee() {
        return movie.getTicketPrice();
    }

    public int getSequenceOfTheDay() {
        return sequenceOfTheDay;
    }

    public double calculateTotalFee(int audienceCount) {
        return calculateTicketPrice() * audienceCount;
    }

    public double calculateTicketPrice()  {
        return movie.getTicketPrice() - getDiscount();
    }

    private double getDiscount()  {
        try {
            Reflections reflections = new Reflections("com.jpmc.theater");
            Set<Class<?>> subTypes = reflections.get(SubTypes.of(Discount.class).asClass());
            double maxDiscount = 0;
            for(Class<?> discount : subTypes) {
                Method discountMethod = discount.getMethod("getDiscount", Showing.class);
                maxDiscount = Math.max(maxDiscount, (double) discountMethod.invoke(discount.newInstance(), this));
            }
            return maxDiscount;
        }
        catch (Exception e) {
            System.out.println("Exception occurred"+e.getMessage());
            return 0;
        }

    }



    @Override
    public String toString() {
        return this.getSequenceOfTheDay() + ": " + this.getStartTime() + " " +
                this.getMovie().getTitle() + " " +
                LocalDateProvider.humanReadableFormat(this.getMovie().getRunningTime()) + " $" + this.calculateTicketPrice();

    }
}
