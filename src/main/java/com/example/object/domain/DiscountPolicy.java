package com.example.object.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "movieDiscountPolicy")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "policyType", length = 20)
public abstract class DiscountPolicy {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "discountPolicy", cascade = CascadeType.ALL)
    private List<DiscountCondition> conditions;

    //Entity 작성 규칙으로 추가한 코드
    public DiscountPolicy(){}

    public DiscountPolicy(DiscountCondition ... conditions){
        this.conditions = Arrays.asList(conditions);

        //Entity 작성 규칙으로 추가한 코드
        for (DiscountCondition condition : this.conditions) {
            condition.setDiscountPolicy(this);
        }
    }

    public Money calculateDiscountAmount(Screening screening){
        for (DiscountCondition each : conditions) {
            if(each.isSatisfiedBy(screening)){
                return getDiscountAmount(screening);
            }
        }
        return Money.ZERO;
    }

    protected abstract Money getDiscountAmount(Screening screening);
}
