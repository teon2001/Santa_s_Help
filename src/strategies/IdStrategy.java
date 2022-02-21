package strategies;

import enums.Category;
import fileio.Child;
import fileio.Gift;

import java.util.ArrayList;
import java.util.List;

public class IdStrategy implements AssignedGiftsStrategy {
    /**
     * @param children
     * @param santaGifts
     * @return
     */
    @Override
    public List<Child> assignedGifts(final List<Child> children,
                                     final List<Gift> santaGifts) {
        children.sort(Child.getUpId());
        for (Child child : children) {
            List<Gift> g = searchGift(child, santaGifts);
            child.setReceivedGifts(g);
        }
        return children;
    }

    /**
     * @param list
     * @return
     */
    public Gift giveMinPrice(final List<Gift> list) {
        Gift min = null;
        if (list.size() == 0) {
            return null;
        }
        if (list.get(0) != null) {
            min = list.get(0);
        }
        for (Gift g : list) {
            if (min != null) {
                if (g.getPrice() < min.getPrice()) {
                    min = g;
                }
            }
        }
        return min;
    }

    /**
     * @param child
     * @param santaGiftsList
     * @return
     */
    @Override
    public List<Gift> searchGift(final Child child, final List<Gift> santaGiftsList) {
        List<Gift> finalGifts = new ArrayList<>();
        double newB = child.getBudget();
        for (Category pref : child.getGiftsPreferences()) {
            List<Gift> g = new ArrayList<>();
            for (Gift gift : santaGiftsList) {
                if (gift.getCategory().equals(pref)) {
                    if (gift.getQuantity() > 0) {
                        g.add(gift);

                    }
                }
            }
            Gift perPref = giveMinPrice(g);
            if (perPref != null) {
                if (newB >= perPref.getPrice()) {
                    newB = newB - perPref.getPrice();
                    int cant = perPref.getQuantity();
                    cant--;
                    perPref.setQuantity(cant);
                    finalGifts.add(perPref);
                }
            }
        }
        return finalGifts;
    }
}
