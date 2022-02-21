package fileio;

import enums.Category;

public class Gift {
    private String productName;
    private double price;
    private Category category;
    private int quantity;

    public Gift(final String productName,
                final double price,
                final Category giftPreference,
                final int quantity) {
        this.productName = productName;
        this.price = price;
        this.category = giftPreference;
        this.quantity = quantity;
    }

    public Gift() {
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

    /**
     * @return
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity
     */
    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return productName +  " ";
    }
}
