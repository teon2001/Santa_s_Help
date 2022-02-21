package main;

import enums.Cities;

import java.util.Comparator;

public class CityWrapper {
    private Cities city;
    private Double averateScoreCity;

    public CityWrapper(final Cities city, final Double averateScoreCity) {
        this.city = city;
        this.averateScoreCity = averateScoreCity;
    }

    /**
     * @return
     */
    public Cities getCity() {
        return city;
    }

    /**
     * @param city
     */
    public void setCity(final Cities city) {
        this.city = city;
    }

    /**
     * @return
     */
    public Double getAverateScoreCity() {
        return averateScoreCity;
    }

    /**
     * @param averateScoreCity
     */
    public void setAverateScoreCity(final Double averateScoreCity) {
        this.averateScoreCity = averateScoreCity;
    }

    private static final Comparator<CityWrapper> UP_NAME =
            Comparator.comparing(o -> o.city.toString());

    /**
     * @return
     */
    public static Comparator<CityWrapper> getUpName() {
        return UP_NAME;
    }

    private static final Comparator<CityWrapper> DOWN_SCORE = new Comparator<CityWrapper>() {
        @Override
        public int compare(final CityWrapper o1, final CityWrapper o2) {
            return Double.compare(o2.averateScoreCity, o1.averateScoreCity);
        }
    };

    /**
     * @return
     */
    public static Comparator<CityWrapper> getDownScore() {
        return DOWN_SCORE;
    }
}
