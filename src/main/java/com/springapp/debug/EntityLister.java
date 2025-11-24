package com.springapp.debug;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class EntityLister {

    private final EntityManager em;

    public EntityLister(EntityManager em) {
        this.em = em;
    }

    @PostConstruct
    public void listEntities() {
        var list = em.getMetamodel()
                .getEntities()
                .stream()
                .map(e -> e.getJavaType().getName())
                .collect(Collectors.toList());

        System.out.println("=== JPA Entities Detected by Hibernate ===");
        list.forEach(System.out::println);
        System.out.println("==========================================");
    }
}
