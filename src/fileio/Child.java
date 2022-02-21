package fileio;

import enums.Category;
import enums.Cities;
import enums.ElvesType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Child extends ChildUpdate implements ChildrenTypes {
    private Cities city;             //mandatory
    private String lastName;         //mandatory
    private String firstName;        //mandatory
    private int age;                 //mandatory
    private Double niceScoreBonus;   //optional

    private List<Double> niceScoreHistory = new ArrayList<>();
    private double averageScore;
    private double budget;
    private List<Gift> receivedGifts = new ArrayList<>();

    private final int nr1 = 5;
    private final int nr2 = 12;
    private final int nr3 = 18;

    public Child() {
    }

    public Child(final int id, final String lastName,
                 final String firstName, final int age,
                 final Cities city, final Double niceScore,
                 final List<Category> categories, final Double niceScoreBonus,
                 final ElvesType elf) {
        super(id, niceScore, categories, elf);
        this.city = city;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.niceScoreBonus = niceScoreBonus;
    }

    private Child(final ChildBuilder childBuilder, final int id,
                  final Double niceScore, final List<Category> giftsPreferences,
                  final ElvesType elf) {
        super(id, niceScore, giftsPreferences, elf);
        this.lastName = childBuilder.lastName;
        this.firstName = childBuilder.firstName;
        this.age = childBuilder.age;
        this.city = childBuilder.city;
        this.averageScore = childBuilder.averageScore;
        this.niceScoreHistory = childBuilder.niceScoreHistory;
        this.budget = childBuilder.budget;
        this.receivedGifts = childBuilder.receivedGifts;
        this.niceScoreBonus = childBuilder.niceScoreBonus;
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
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age
     */
    public void setAge(final int age) {
        this.age = age;
    }

    /**
     * @return
     */
    public List<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    /**
     * @param niceScoreHistory
     */
    public void setNiceScoreHistory(final List<Double> niceScoreHistory) {
        this.niceScoreHistory = niceScoreHistory;
    }

    /**
     * @return
     */
    public double getAverageScore() {
        return averageScore;
    }

    /**
     * @param averageScore
     */
    public void setAverageScore(final double averageScore) {
        this.averageScore = averageScore;
    }

    /**
     * @return
     */
    public double getBudget() {
        return budget;
    }

    /**
     * @param budget
     */
    public void setBudget(final double budget) {
        this.budget = budget;
    }

    /**
     * @return
     */
    public List<Gift> getReceivedGifts() {
        return receivedGifts;
    }

    /**
     * @param receivedGifts
     */
    public void setReceivedGifts(final List<Gift> receivedGifts) {
        this.receivedGifts = receivedGifts;
    }

    /**
     * @return
     */
    public Double getNiceScoreBonus() {
        return niceScoreBonus;
    }

    /**
     * @param niceScoreBonus
     */
    public void setNiceScoreBonus(final Double niceScoreBonus) {
        this.niceScoreBonus = niceScoreBonus;
    }

    /**
     * @return
     */
    @Override
    public String getTypeOfChild() {
        if (this.age < nr1) {
            return ChildrenFactory.createChild(ChildrenFactory.ChildType.BABY);
        } else if (this.age < nr2) {
            return ChildrenFactory.createChild(ChildrenFactory.ChildType.KID);
        } else if (this.age <= nr3) {
            return ChildrenFactory.createChild(ChildrenFactory.ChildType.TEEN);
        } else {
            return ChildrenFactory.createChild(ChildrenFactory.ChildType.YOUNG_ADULT);
        }
    }

    private static final Comparator<Child> UP_ID = Comparator.comparingInt(ChildUpdate::getId);

    public static Comparator<Child> getUpId() {
        return UP_ID;
    }

    private static final Comparator<Child> UP_AVERAGE_SCORE = new Comparator<Child>() {
        @Override
        public int compare(final Child o1, final Child o2) {
            return Double.compare(o1.averageScore, o2.averageScore);
        }
    };

    public static Comparator<Child> getUpAverageScore() {
        return UP_AVERAGE_SCORE;
    }

    private static final Comparator<Child> DOWN_AVERAGE_SCORE = new Comparator<Child>() {
        @Override
        public int compare(final Child o1, final Child o2) {
            return Double.compare(o2.averageScore, o1.averageScore);
        }
    };

    public static Comparator<Child> getDownAverageScore() {
        return DOWN_AVERAGE_SCORE;
    }

    /**
     *
     */
        public static class ChildBuilder {
        private Integer id;
        private Double niceScore;
        private List<Category> giftsPreferences;
        private ElvesType elf;
        private Cities city;                   //mandatory
        private String lastName;               //mandatory
        private String firstName;              //mandatory
        private int age;                       //mandatory
        private List<Double> niceScoreHistory; //mandatory
        private double averageScore;           //mandatory
        private double budget;                 //mandatory
        private List<Gift> receivedGifts;      //mandatory
        private Double niceScoreBonus = 0.0;   //optional

        public ChildBuilder(final Integer id,
                final Double niceScore,
                final List<Category> giftsPreferences,
                final ElvesType elf,
                            final Cities city, final String lastName,
                            final String firstName, final int age,
                            final List<Double> niceScoreHistory,
                            final double averageScore,
                            final double budget, final List<Gift> receivedGifts) {
            this.id = id;
            this.niceScore = niceScore;
            this.giftsPreferences = giftsPreferences;
            this.elf = elf;
            this.city = city;
            this.lastName = lastName;
            this.firstName = firstName;
            this.age = age;
            this.niceScoreHistory = niceScoreHistory;
            this.averageScore = averageScore;
            this.budget = budget;
            this.receivedGifts = receivedGifts;
        }

        /**
         * @param niceScoreBonus
         * @return
         */
        public ChildBuilder s(final Double niceScoreBonus) {
            this.niceScoreBonus = niceScoreBonus;
            return this;
        }

        /**
         * @return
         */
        public Child build() {
            return new Child(this, id, niceScore,
                    giftsPreferences, elf);
        }
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return lastName + " " + firstName + ", ";
    }
}
