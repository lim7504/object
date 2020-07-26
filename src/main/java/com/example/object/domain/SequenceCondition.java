package com.example.object.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SEQUENCE")
public class SequenceCondition extends DiscountCondition {
    private int sequence;

    //Entity 작성 규칙으로 추가한 코드
    public SequenceCondition(){}

    public SequenceCondition(int sequence){
        this.sequence = sequence;
    }

    public boolean isSatisfiedBy(Screening screening){
        return screening.isSequence(sequence);
    }
}
