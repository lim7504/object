package com.example.object.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PERCENT")
public class PercentDiscountPolicy extends DiscountPolicy {
    private double percent;

    //Entity 작성 규칙으로 추가한 코드
    public PercentDiscountPolicy(){}

    public PercentDiscountPolicy(double percent, DiscountCondition... conditions) {
        super(conditions);
        this.percent = percent;
    }

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return screening.getMovieFee().times(percent);
    }
}
