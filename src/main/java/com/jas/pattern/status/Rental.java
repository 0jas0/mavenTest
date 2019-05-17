package com.jas.pattern.status;

/**
 * <一句话描述>,
 * <详细介绍>,
 *
 * @author 琚安生
 * @since 设计wiki | 需求wiki
 */
public class Rental {
    private int days;
    private Movie movie;

    public double getCharge(){
        return movie.getCharge(days);
    }
    public double getFrequentRenterPoints(){
        return movie.getFrequentRenterPoints(days);
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
