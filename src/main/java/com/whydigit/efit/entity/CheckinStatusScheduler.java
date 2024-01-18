package com.whydigit.efit.entity;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Component
public class CheckinStatusScheduler {

    @PersistenceContext
    private EntityManager entityManager;

    // Scheduled task to run every day at 11 PM
    @Scheduled(cron = "0 0 15 * * ?")
    @Transactional
    public void updateCheckinStatus() {
        // Find employees with status 'In' at 11 PM
        String queryString = "SELECT empcode FROM CheckinStatus WHERE status = 'In'";

        Query query = entityManager.createQuery(queryString);
        List<String> empcodes = query.getResultList();

        // Update CheckinStatus to 'Out' and insert new records in Checkin table
        for (String empcode : empcodes) {
            updateCheckinStatus(empcode);
            insertCheckinRecord(empcode);
        }
    }

    private void updateCheckinStatus(String empcode) {
        String updateQuery = "UPDATE CheckinStatus SET status = 'Out' WHERE empcode = :empcode";
        Query query = entityManager.createQuery(updateQuery);
        query.setParameter("empcode", empcode);
        query.executeUpdate();
    }

    private void insertCheckinRecord(String empcode) {
        String insertQuery = "INSERT INTO Checkin (checkin_date, empcode, entrytime, status) "
                           + "VALUES (CURRENT_DATE(), :empcode, CURRENT_TIME(), 'Out')";

        Query query = entityManager.createNativeQuery(insertQuery);
        query.setParameter("empcode", empcode);
        query.executeUpdate();
    }
}
