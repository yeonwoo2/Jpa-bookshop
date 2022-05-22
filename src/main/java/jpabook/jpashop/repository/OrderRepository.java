package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order){
        em.persist(order);
    }

    public Order findOne(Long id){
        return em.find(Order.class, id);
    }

    //동적쿼리 수정
    public List<Order> findAll(OrderSearch orderSearch){
        String jpql = "select o from Order o join o.member m";
        return em.createQuery(jpql, Order.class)
                        .setMaxResults(1000) //최대 1000건
                        .getResultList();
    }
}
