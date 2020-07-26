package com.example.object.domain;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public class Money {

    public static final Money ZERO = Money.wons(0);

    private final BigDecimal amount;

    public static Money wons(long amount){
        return new Money(BigDecimal.valueOf(amount));
    }

    public static Money wons(double amount){
        return new Money(BigDecimal.valueOf(amount));
    }

    // 오브젝트 책에 없는 내용
    // amount를 final로 유지하기 위해선 생성자에서 amount값을 초기화 해야한다.
    // embeded 값을 사용하기 위해서는 default 생성자가 필요하다.
    // 위의 두 조건이 충돌한다.
    // 하여 기존의 없던 default생성자를 추가하고 amount를 0으로 초기화 하였다.
    // default생성자가 추가 되었지만 사용하지는 않을 것이다.
    public Money(){
        this.amount = BigDecimal.valueOf(0);
    }

    public Money(BigDecimal amount){
        this.amount = amount;
    }

    public Money plus(Money amount){
        return new Money(this.amount.add(amount.amount));
    }

    public Money minus(Money amount){
        return new Money(this.amount.subtract(amount.amount));
    }

    public Money times(double percent) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(percent)));
    }

    public boolean isLessThan(Money other) {
        return amount.compareTo(other.amount) < 0;
    }

    public boolean isGreaterThanOrEqual(Money other){
        return amount.compareTo(other.amount) >= 0;
    }
}
