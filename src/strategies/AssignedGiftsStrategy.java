package strategies;

import fileio.Child;
import fileio.Gift;

import java.util.List;

public interface AssignedGiftsStrategy {
    /**
     * @param children
     * @param gifts
     * @return
     */
    List<Child> assignedGifts(List<Child> children, List<Gift> gifts);

    /**
     * @param child
     * @param santaGiftsList
     * @return
     */
    List<Gift> searchGift(Child child, List<Gift> santaGiftsList);
}
