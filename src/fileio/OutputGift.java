package fileio;

import enums.Category;

public class OutputGift {

    private String productName;
    private double price;
    private Category category;

    public OutputGift(final String productName,
                      final double price,
                      final Category giftPreference) {
        this.productName = productName;
        this.price = price;
        this.category = giftPreference;
    }

    public OutputGift() {
    }

    /**
     * @return
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName
     */
    public void setProductName(final String productName) {
        this.productName = productName;
    }

    /**
     * @return
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price
     */
    public void setPrice(final double price) {
        this.price = price;
    }

    /**
     * @return
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @param category
     */
    public void setCategory(final Category category) {
        this.category = category;
    }

}
