package com.example.object.domain;

import javax.persistence.*;

@Entity
@Table(name = "movieDiscountCondition")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "conditionType", length = 20)
public abstract class DiscountCondition {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "policy_id")
    private DiscountPolicy discountPolicy;

    abstract boolean isSatisfiedBy(Screening screening);

    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }
}
