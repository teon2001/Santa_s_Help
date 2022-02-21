package fileio;

import enums.CityStrategyEnum;

import java.util.List;

public class AnnualChange {
    private Double newSantaBudget;
    private List<Gift> newGifts;
    private List<Child> newChildren;
    private List<ChildUpdate> childrenUpdates;
    private CityStrategyEnum strategy;

    public AnnualChange() {
    }

    /**
     * @param newSantaBudget
     * @param newGifts
     * @param newChildren
     * @param childrenUpdates
     */
    public AnnualChange(final Double newSantaBudget, final List<Gift> newGifts,
                        final List<Child> newChildren, final List<ChildUpdate> childrenUpdates,
                        final CityStrategyEnum strategy) {
        this.newSantaBudget = newSantaBudget;
        this.newGifts = newGifts;
        this.newChildren = newChildren;
        this.childrenUpdates = childrenUpdates;
        this.strategy = strategy;
    }

    /**
     * @return
     */
    public Double getNewSantaBudget() {
        return newSantaBudget;
    }

    /**
     * @param newSantaBudget
     */
    public void setNewSantaBudget(final Double newSantaBudget) {
        this.newSantaBudget = newSantaBudget;
    }

    /**
     * @return
     */
    public List<Gift> getNewGifts() {
        return newGifts;
    }

    /**
     * @param newGifts
     */
    public void setNewGifts(final List<Gift> newGifts) {
        this.newGifts = newGifts;
    }

    /**
     * @return
     */
    public List<Child> getNewChildren() {
        return newChildren;
    }

    /**
     * @param newChildren
     */
    public void setNewChildren(final List<Child> newChildren) {
        this.newChildren = newChildren;
    }

    /**
     * @return
     */
    public List<ChildUpdate> getChildrenUpdates() {
        return childrenUpdates;
    }

    /**
     * @param childrenUpdates
     */
    public void setChildrenUpdates(final List<ChildUpdate> childrenUpdates) {
        this.childrenUpdates = childrenUpdates;
    }

    /**
     * @return
     */
    public CityStrategyEnum getStrategy() {
        return strategy;
    }

    /**
     * @param strategy
     */
    public void setStrategy(final CityStrategyEnum strategy) {
        this.strategy = strategy;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "AnnualChange{"
                + "newSantaBudget=" + newSantaBudget
                + ", newGifts=" + newGifts
                + ", newChildren=" + newChildren
                + ", childrenUpdates=" + childrenUpdates
                + '}';
    }
}
