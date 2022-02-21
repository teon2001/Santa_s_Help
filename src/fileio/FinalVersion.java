package fileio;

import java.util.List;

public class FinalVersion {
    private List<ChildrenForAYear> annualChildren;

    public FinalVersion() {
    }

    /**
     * @return
     */
    public List<ChildrenForAYear> getAnnualChildren() {
        return annualChildren;
    }

    /**
     * @param annualChildren seteaza lista finala
     */
    public void setAnnualChildren(final List<ChildrenForAYear> annualChildren) {
        this.annualChildren = annualChildren;
    }
}
