package fileio;

import java.util.List;

public class Input {
    private Integer numberOfYears;
    private Double santaBudget;
    private InitialData initialData;
    private List<AnnualChange> annualChanges;

    public Input() {
    }

    public Input(final Integer numberOfYears, final Double santaBudget,
                 final InitialData initialData, final List<AnnualChange> annualChanges) {
        this.numberOfYears = numberOfYears;
        this.santaBudget = santaBudget;
        this.initialData = initialData;
        this.annualChanges = annualChanges;
    }

    /**
     * @return
     */
    public int getNumberOfYears() {
        return numberOfYears;
    }

    /**
     * @param numberOfYears
     */
    public void setNumberOfYears(final Integer numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    /**
     * @return
     */
    public double getSantaBudget() {
        return santaBudget;
    }

    /**
     * @param santaBudget
     */
    public void setSantaBudget(final Double santaBudget) {
        this.santaBudget = santaBudget;
    }

    /**
     * @return
     */
    public InitialData getInitialData() {
        return initialData;
    }

    /**
     * @param initialData
     */
    public void setInitialData(final InitialData initialData) {
        this.initialData = initialData;
    }

    /**
     * @return
     */
    public List<AnnualChange> getAnnualChanges() {
        return annualChanges;
    }

    /**
     * @param annualChanges
     */
    public void setAnnualChanges(final List<AnnualChange> annualChanges) {
        this.annualChanges = annualChanges;
    }

}
