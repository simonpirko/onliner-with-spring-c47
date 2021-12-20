package by.fakeonliner.repository.hibernate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HibernateProductDaoTest {

    @Test
    void existByModel() {
        HibernateProductDao hibernateProductDao = new HibernateProductDao();
        boolean exist = hibernateProductDao.existByModel("Apple");
        System.out.println(exist);
    }
}