package com.example.object.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("AMOUNT")
public class AmountDiscountPolicy extends DiscountPolicy {
    private Money discountAmount;

    //Entity 작성 규칙으로 추가한 코드
    public AmountDiscountPolicy(){}

    public AmountDiscountPolicy(Money discountAmount, DiscountCondition ... conditions){
        super(conditions);
        this.discountAmount = discountAmount;
    }

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return discountAmount;
    }
}
