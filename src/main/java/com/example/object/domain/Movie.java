package com.example.object.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Duration;

@Entity
@Data
public class Movie {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Duration runningTime;

    @Embedded
    private Money fee;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "policy_id")
    private DiscountPolicy discountPolicy;

    //Entity 작성 규칙으로 추가한 코드
    public Movie(){ }

    public Movie(String title, Duration runningTime, Money fee, DiscountPolicy discountPolicy){
        this.title = title;
        this.runningTime = runningTime;
        this.fee = fee;
        this.discountPolicy = discountPolicy;
    }

    public Money getFee(){
        return this.fee;
    }

    public Money calculateMovieFee(Screening screening){
        return fee.minus(discountPolicy.calculateDiscountAmount(screening));
    }

}
