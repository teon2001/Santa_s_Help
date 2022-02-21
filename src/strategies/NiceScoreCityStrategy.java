package strategies;

import enums.Category;
import enums.Cities;
import fileio.Child;
import main.CityWrapper;
import fileio.Gift;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static enums.Cities.BRAILA;
import static enums.Cities.BRASOV;
import static enums.Cities.BUCURESTI;
import static enums.Cities.BUZAU;
import static enums.Cities.CLUJ;
import static enums.Cities.CONSTANTA;
import static enums.Cities.CRAIOVA;
import static enums.Cities.IASI;
import static enums.Cities.ORADEA;
import static enums.Cities.TIMISOARA;

public class NiceScoreCityStrategy implements AssignedGiftsStrategy {
    /**
     * @param children
     * @param santaGifts
     * @return
     */
    @Override
    public List<Child> assignedGifts(final List<Child> children, final List<Gift> santaGifts) {
        //calculez media pe oras si pun copiii intr-o lista in ordine
        List<CityWrapper> orasee = orderCities(children);

        List<Child> orderChildren = new ArrayList<>();
        for (CityWrapper unOras : orasee) {
            orderChildren.addAll(giveChildren(children, unOras.getCity()));
        }

        //----ELIMINA DUPLICATE---
        List<Child> newList = new ArrayList<>();
        for (Child element : orderChildren) {
            if (!newList.contains(element)) {
                newList.add(element);
            }
        }

        for (Child c : newList) {
            c.setReceivedGifts(searchGift(c, santaGifts));
        }

        return newList;
    }

    /**
     * @param list
     * @param c
     * @return
     */
    public List<Child> giveChildren(final List<Child> list, final Cities c) {
        List<Child> newCh = new ArrayList<>();
        Collections.sort(list, Child.getUpId());
        for (Child o : list) {
            if (o.getCity().equals(c)) {
                newCh.add(o);
            }
        }
        Collections.sort(newCh, Child.getUpId());
        return newCh;
    }

    /**
     * @param children
     * @param oras
     * @return
     */
    public Double medieOras(final List<Child> children, final Cities oras) {
        Double sum = 0.0;
        int i = 0;
        for (Child c : children) {
            if (c.getCity().equals(oras)) {
                i++;
                sum += c.getAverageScore();
            }
        }
        if (i != 0) {
            return sum / i;
        } else {
            return 0d;
        }
    }

    /**
     * @param children
     * @return
     */
    public List<CityWrapper> orderCities(final List<Child> children) {
        List<CityWrapper> cityWrapperList = new ArrayList<>();
        for (Child c : children) {
            if (c.getCity().equals(BUCURESTI)) {
                CityWrapper w = new CityWrapper(BUCURESTI, medieOras(children, BUCURESTI));
                cityWrapperList.add(w);
            }
            if (c.getCity().equals(CONSTANTA)) {
                CityWrapper w = new CityWrapper(CONSTANTA, medieOras(children, CONSTANTA));
                cityWrapperList.add(w);
            }
            if (c.getCity().equals(BUZAU)) {
                CityWrapper w = new CityWrapper(BUZAU, medieOras(children, BUZAU));
                cityWrapperList.add(w);
            }
            if (c.getCity().equals(TIMISOARA)) {
                CityWrapper w = new CityWrapper(TIMISOARA, medieOras(children, TIMISOARA));
                cityWrapperList.add(w);
            }
            if (c.getCity().equals(CLUJ)) {
                CityWrapper w = new CityWrapper(CLUJ, medieOras(children, CLUJ));
                cityWrapperList.add(w);
            }
            if (c.getCity().equals(IASI)) {
                CityWrapper w = new CityWrapper(IASI, medieOras(children, IASI));
                cityWrapperList.add(w);
            }
            if (c.getCity().equals(CRAIOVA)) {
                CityWrapper w = new CityWrapper(CRAIOVA, medieOras(children, CRAIOVA));
                cityWrapperList.add(w);

            }
            if (c.getCity().equals(BRASOV)) {
                CityWrapper w = new CityWrapper(BRASOV, medieOras(children, BRASOV));
                cityWrapperList.add(w);

            }
            if (c.getCity().equals(BRAILA)) {
                CityWrapper w = new CityWrapper(BRAILA, medieOras(children, BRAILA));
                cityWrapperList.add(w);
            }
            if (c.getCity().equals(ORADEA)) {
                CityWrapper w = new CityWrapper(ORADEA, medieOras(children, ORADEA));
                cityWrapperList.add(w);
            }
        }

        cityWrapperList.sort(CityWrapper.getDownScore().thenComparing(CityWrapper.getUpName()));
        return cityWrapperList;
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
