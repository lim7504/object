package com.example.object.repository;

import com.example.object.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class MovieRepositoryTest {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    EntityManager em;

    @Test
    @Rollback(false)
    public void testAddMovie() {

        Movie movie = new Movie();
        movie.setTitle("아바타");
        movie.setFee(Money.wons(800));
        movie.setRunningTime(Duration.ofMinutes(120));
        movie.setDiscountPolicy(new AmountDiscountPolicy(Money.wons(800),
                new SequenceCondition(1),
                new SequenceCondition(10),
                new PeriodCondition(DayOfWeek.MONDAY, LocalTime.of(10,0), LocalTime.of(11,59)),
                new PeriodCondition(DayOfWeek.THURSDAY, LocalTime.of(10,0), LocalTime.of(20,59))));

        this.movieRepository.save(movie);

        em.flush();
        em.clear();

        Movie findMovie = this.movieRepository.findById(1L).get();



    }
}