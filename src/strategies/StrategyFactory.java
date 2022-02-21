package strategies;

import enums.CityStrategyEnum;

public class StrategyFactory {
    private CityStrategyEnum strategyEnum;

    public StrategyFactory(final CityStrategyEnum strategyEnum) {
        this.strategyEnum = strategyEnum;
    }

    /**
     * @return
     */
    public CityStrategyEnum getStrategyEnum() {
        return strategyEnum;
    }

    /**
     * @param strategyEnum
     */
    public void setStrategyEnum(final CityStrategyEnum strategyEnum) {
        this.strategyEnum = strategyEnum;
    }

    /**
     * @param strategyEnum
     * @return
     */
    public AssignedGiftsStrategy createStrategy(final CityStrategyEnum strategyEnum) {
        return switch (strategyEnum) {
            case ID -> new IdStrategy();
            case NICE_SCORE_CITY -> new NiceScoreCityStrategy();
            case NICE_SCORE -> new NiceScoreStrategy();
        };
    }
}
