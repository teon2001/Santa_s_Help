package main;

import common.Constants;
import enums.Category;
import enums.CityStrategyEnum;
import fileio.AnnualChange;
import fileio.Child;
import fileio.ChildUpdate;
import fileio.ChildrenForAYear;
import fileio.FinalVersion;
import fileio.Gift;
import fileio.InitialData;
import fileio.Input;
import fileio.OutputChild;
import fileio.OutputGift;
import strategies.AssignedGiftsStrategy;
import strategies.StrategyFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static common.Constants.MAX_AGE;
import static common.Constants.MAX_SCORE;
import static common.Constants.PROCENT_JOS;
import static common.Constants.PROCENT_SUS;
import static enums.ElvesType.BLACK;
import static enums.ElvesType.PINK;
import static enums.ElvesType.YELLOW;

public final class Solution {
    private static Solution instance = null;

    private Solution() {
    }

    /**
     * @return
     */
    public static Solution getInstance() {
        if (instance == null) {
            instance = new Solution();
        }
        return instance;
    }

    /**
     * @param list
     * @return
     */
    public Gift min(final List<Gift> list) {
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
     * @param category
     * @param santaGiftsList
     * @return
     */
    public Gift giveGiftYellow(final Category category, final List<Gift> santaGiftsList) {

        List<Gift> finalGifts = new ArrayList<>();
        for (Gift gift : santaGiftsList) {
            if (gift.getCategory().equals(category)) {
                finalGifts.add(gift);
            }
        }

        Gift perPref = min(finalGifts);
        if (perPref != null) {
            int cant = perPref.getQuantity();
            cant--;
            perPref.setQuantity(cant);
            if (perPref.getQuantity() >= 0) {
                return perPref;
            }
        }
        return null;
    }


    /**
     * @param children
     * @return
     */
    public List<OutputChild> generateOutput(final List<Child> children) {
        Collections.sort(children, Child.getUpId());
        List<OutputChild> childOutputList = new ArrayList<>();
        for (Child c : children) {
            OutputChild o = new OutputChild(
                    c.getId(), c.getLastName(), c.getFirstName(),
                    c.getCity(), c.getAge(), c.getGiftsPreferences(),
                    c.getAverageScore(), c.getNiceScoreHistory(),
                    c.getBudget(), generateOutputGift(c.getReceivedGifts())
            );
            childOutputList.add(o);
        }
        return childOutputList;
    }


    /**
     * @param gift
     * @return
     */
    public List<OutputGift> generateOutputGift(final List<Gift> gift) {
        List<OutputGift> oGiftList = new ArrayList<>();
        for (Gift c : gift) {
            OutputGift o = new OutputGift(
                    c.getProductName(), c.getPrice(),
                    c.getCategory());
            oGiftList.add(o);
        }
        return oGiftList;
    }

    /**
     * @param c
     * @param list
     * @return
     */
    private boolean isNotInTheList(final Category c, final List<Category> list) {
        for (Category cc : list) {
            if (cc.equals(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param oldPref
     * @param newPref
     * @return
     */
    public List<Category> giveNewCategories(final List<Category> oldPref,
                                            final List<Category> newPref) {
        List<Category> list = new ArrayList<>(newPref);

        for (Category c : oldPref) {
            for (Category cNew : newPref) {
                if (!c.equals(cNew) && isNotInTheList(c, list)) {
                    list.add(c);
                }
            }
        }

//-----------------------ELIMINA DUPLICATE----------------------------
        List<Category> newList = new ArrayList<>();

        for (Category element : list) {
            if (!newList.contains(element)) {
                newList.add(element);
            }
        }
        return newList;
    }

    /**
     * @param list
     * @return
     */
    public double mediePonderata(final List<Double> list) {
        double sum = 0.0;
        int sumP = 0;
        for (int i = 0; i < list.size(); i++) {
            sum = sum + list.get(i) * (i + 1);
            int j = i + 1;
            sumP = sumP + j;
        }
        sum = sum / sumP;

        return sum;
    }


    /**
     * @param oneChange
     * @param input
     * @return
     */
    public ChildrenForAYear roundX(final AnnualChange oneChange,
                                   final Input input) {

        List<Child> newC = new ArrayList<>();

        for (Child c : input.getInitialData().getChildren()) {
            int age = c.getAge();
            age++;
            c.setAge(age);
            if (age <= Constants.MAX_AGE) {
                newC.add(c);
            }
        }

        if (oneChange.getNewChildren().size() != 0) {
            for (Child c : oneChange.getNewChildren()) {
                if (c.getAge() <= Constants.MAX_AGE) {
                    c.getNiceScoreHistory().add(c.getNiceScore());
                    newC.add(c);
                }
            }
        }

        for (Child c : newC) {
            if (oneChange.getChildrenUpdates().size() != 0) {
                for (ChildUpdate cU : oneChange.getChildrenUpdates()) {
                    if (Objects.equals(c.getId(), cU.getId())) {
                        if (cU.getNiceScore() != null) {
                            c.getNiceScoreHistory().add(cU.getNiceScore());
                        }
                        if (cU.getGiftsPreferences().size() != 0) {
                            List<Category> newCategories =
                                    giveNewCategories(c.getGiftsPreferences(),
                                            cU.getGiftsPreferences());
                            c.setGiftsPreferences(newCategories);
                        }
                        c.setElf(cU.getElf());
                    }
                }
            }
        }

        if (oneChange.getNewGifts().size() != 0) {
            input.getInitialData().getSantaGiftsList().addAll(oneChange.getNewGifts());
        }
        input.setSantaBudget(oneChange.getNewSantaBudget());

        newC.sort(Child.getUpId());
        for (Child c : newC) {
            if (c.getTypeOfChild().equals("Baby")) {
                c.setAverageScore(MAX_SCORE);
            } else if (c.getTypeOfChild().equals("Kid")) {
                double sum = 0.0;
                for (double nr : c.getNiceScoreHistory()) {
                    sum += nr;
                }
                sum = sum / c.getNiceScoreHistory().size();
                sum = sum + sum * c.getNiceScoreBonus() / PROCENT_JOS;
                if (sum >= MAX_SCORE) {
                    c.setAverageScore(MAX_SCORE);
                } else {
                    c.setAverageScore(sum);
                }
            } else if (c.getTypeOfChild().equals("Teen")) {
                double sum = mediePonderata(c.getNiceScoreHistory());
                sum = sum + sum * c.getNiceScoreBonus() / PROCENT_JOS;
                if (sum >= MAX_SCORE) {
                    c.setAverageScore(MAX_SCORE);
                } else {
                    c.setAverageScore(sum);
                }
            } else {
                c.setAverageScore(-1d);
            }
        }

        double sumAverageScore = 0.0;
        for (Child c : newC) {
            sumAverageScore += c.getAverageScore();
        }
        double budgetUnit = input.getSantaBudget() / sumAverageScore;

        for (Child child : newC) {
            double budgetPerChild = child.getAverageScore() * budgetUnit;

            if (child.getElf().equals(BLACK)) {
                budgetPerChild = budgetPerChild - budgetPerChild * PROCENT_SUS / PROCENT_JOS;
            }
            if (child.getElf().equals(PINK)) {
                budgetPerChild = budgetPerChild + budgetPerChild * PROCENT_SUS / PROCENT_JOS;
            }

            child.setBudget(budgetPerChild);
        }

        StrategyFactory strategyFactory = new StrategyFactory(oneChange.getStrategy());
        AssignedGiftsStrategy a = strategyFactory.createStrategy(oneChange.getStrategy());
        List<Child> newnewnew = a.assignedGifts(newC, input.getInitialData().getSantaGiftsList());

        for (Child c : newnewnew) {
            if (c.getElf().equals(YELLOW)) {
                if (c.getReceivedGifts().size() == 0) {
                    if (c.getGiftsPreferences().size() != 0) {
                        Category category = c.getGiftsPreferences().get(0);
                        Gift yey = giveGiftYellow(category, input.getInitialData()
                                .getSantaGiftsList());
                        if (yey != null) {
                            c.getReceivedGifts().add(yey);
                        }
                    }
                }
            }
        }

        newnewnew.removeIf(child -> child.getAge() > MAX_AGE);
        List<OutputChild> childOutputList = generateOutput(newnewnew);

        //deep copy, care previne modificarea rundelor anterioare
        List<OutputChild> list1 = new ArrayList<>();
        for (OutputChild o : childOutputList) {
            List<Double> l = new ArrayList<>(o.getNiceScoreHistory());

            OutputChild o1 = new OutputChild(o.getId(), o.getLastName(),
                    o.getFirstName(), o.getCity(), o.getAge(), o.getGiftsPreferences(),
                    o.getAverageScore(), l,
                    o.getAssignedBudget(), o.getReceivedGifts());
            list1.add(o1);
        }
        ChildrenForAYear list = new ChildrenForAYear();
        list.setChildren(list1);

        input.getInitialData().getChildren().addAll(oneChange.getNewChildren());
        return list;
    }

    /**
     * @param input
     * @return
     */
    public FinalVersion action(final Input input) {
        FinalVersion annualChildren = new FinalVersion();

        List<OutputChild> childOutputList;
        int numberOfYears = input.getNumberOfYears();
        double santaBudget = input.getSantaBudget();
        InitialData initialData = input.getInitialData();

        List<AnnualChange> annualChanges = input.getAnnualChanges();

        List<Child> children1 = initialData.getChildren();

        //----aici se foloseste BUILDER.ul-----------
        List<Child> children2 = new ArrayList<>();
        for (Child c : children1) {
            Child newChh = new Child.ChildBuilder(c.getId(),
                    c.getNiceScore(), c.getGiftsPreferences(),
                    c.getElf(), c.getCity(), c.getLastName(),
                    c.getFirstName(), c.getAge(), c.getNiceScoreHistory(),
                    c.getAverageScore(), c.getBudget(), c.getReceivedGifts())
                    .s(c.getNiceScoreBonus()).build();
            children2.add(newChh);
        }


        List<Gift> santaGiftsList = initialData.getSantaGiftsList();

        children2.sort(Child.getUpId());
        List<Child> children = new ArrayList<>();

        for (Child c : children2) {
            if (c.getAge() <= MAX_AGE) {
                children.add(c);
            }
        }
//----------------------------------RUNDA 0-----------------------------------
        double sumAverageScore = 0.0;
        for (Child child : children) {
            child.getNiceScoreHistory().add(child.getNiceScore());
            if (child.getTypeOfChild().equals("Baby")) {
                child.setAverageScore(MAX_SCORE);
            } else if (child.getTypeOfChild().equals("Kid")) {
                double d = child.getNiceScore();
                d = d + d * child.getNiceScoreBonus() / PROCENT_JOS;
                if (d >= MAX_SCORE) {
                    child.setAverageScore(MAX_SCORE);
                } else {
                    child.setAverageScore(d);
                }
            } else if (child.getTypeOfChild().equals("Teen")) {
                double d = child.getNiceScore();
                d = d + d * child.getNiceScoreBonus() / PROCENT_JOS;
                if (d >= MAX_SCORE) {
                    child.setAverageScore(MAX_SCORE);
                } else {
                    child.setAverageScore(d);
                }
            } else {
                child.setAverageScore(-1d);
            }
            sumAverageScore += child.getAverageScore();
        }

        double budgetUnit = santaBudget / sumAverageScore;
        List<Child> newnewnew = new ArrayList<>();
        for (Child child : children) {
            double budgetPerChild = child.getAverageScore() * budgetUnit;
            if (budgetPerChild >= 0) {
                if (child.getElf().equals(BLACK)) {
                    budgetPerChild = budgetPerChild - budgetPerChild * PROCENT_SUS / PROCENT_JOS;
                }
                if (child.getElf().equals(PINK)) {
                    budgetPerChild = budgetPerChild + budgetPerChild * PROCENT_SUS / PROCENT_JOS;
                }
            }
            child.setBudget(budgetPerChild);
        }

        StrategyFactory strategyFactory = new StrategyFactory(CityStrategyEnum.ID);
        AssignedGiftsStrategy a = strategyFactory.createStrategy(CityStrategyEnum.ID);
        newnewnew = a.assignedGifts(children, santaGiftsList);
        newnewnew.removeIf(child -> child.getAge() > MAX_AGE);

        for (Child c : newnewnew) {
            if (c.getElf().equals(YELLOW)) {
                if (c.getReceivedGifts().size() == 0) {
                    if (c.getGiftsPreferences().size() != 0) {
                        Category category = c.getGiftsPreferences().get(0);
                        Gift yey = giveGiftYellow(category, santaGiftsList);
                        if (yey != null) {
                            c.getReceivedGifts().add(yey);
                        }
                    }
                }
            }
        }

        childOutputList = generateOutput(newnewnew);

        List<OutputChild> list1 = new ArrayList<>();
        for (OutputChild o : childOutputList) {
            List<Double> l = new ArrayList<>(o.getNiceScoreHistory());

            OutputChild o1 = new OutputChild(o.getId(), o.getLastName(),
                    o.getFirstName(), o.getCity(), o.getAge(), o.getGiftsPreferences(),
                    o.getAverageScore(), l,
                    o.getAssignedBudget(), o.getReceivedGifts());
            list1.add(o1);
        }

        ChildrenForAYear list = new ChildrenForAYear();
        list.setChildren(list1);

        List<ChildrenForAYear> list2 = new ArrayList<>();
        list2.add(list);
        annualChildren.setAnnualChildren(list2);

        //acum parcurgem fiecare an si modificam listele din runda 0
        for (int i = 1; i <= numberOfYears; i++) {
            ChildrenForAYear l = roundX(annualChanges.get(i - 1), input);
            list2.add(l);
            annualChildren.setAnnualChildren(list2);
        }

        annualChildren.setAnnualChildren(list2);
        return annualChildren;

    }

}
