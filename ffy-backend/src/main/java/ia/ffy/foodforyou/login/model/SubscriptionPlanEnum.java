package ia.ffy.foodforyou.login.model;

public enum SubscriptionPlanEnum {
    PLAN_1("50$"),
    PLAN_2("75$"),
    PLAN_3("100$");

    private final String subscriptionPlan;

    SubscriptionPlanEnum(String subscriptionPlan) {
        this.subscriptionPlan = subscriptionPlan;
    }
}
