package me.kunzou.mockServer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import me.kunzou.mockServer.domain.RateCard;

@Repository
public interface RateCardRepository extends JpaRepository<RateCard, Long> {
}
