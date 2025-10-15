package org.ziro.beans;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.primefaces.PrimeFaces;
import org.ziro.HitChecker;
import org.ziro.Point;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class CollectionBean {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Derby");
    EntityManager em = emf.createEntityManager();
    List points = new ArrayList<Point>();




    @Inject
    private DataBean dataBean;

    private HitChecker hitChecker = new HitChecker();


    public void processRequest() {

        List<Double> selectedRs = dataBean.getSelectedRs();
        for (Double selectedR : selectedRs) {
            try {
                double start = System.nanoTime();
                double x = dataBean.getValue();
                double y=Double.parseDouble(dataBean.getText());
                boolean hit = hitChecker.isHit(selectedR,x,y);
                double executionTime = (System.nanoTime()-start)/1_000_000.0;

                String startTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

                Point point = new Point(x, y,selectedR,hit,startTime,executionTime);

                addtoDataBase(point);

            } catch (NumberFormatException e) {
                PrimeFaces.current().executeScript("showNotification('Не делай так больше. Введи валидные X,Y,R')");

            }
        }

    }

    public void addtoDataBase(Point point) {
        this.em.getTransaction().begin();
        em.persist(point);
        em.getTransaction().commit();
    }

    public void clearDataBase() {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Point").executeUpdate();
        em.getTransaction().commit();
        points.clear();
        PrimeFaces.current().executeScript("redrawCanvas()");
    }

    public List<Point> getPoints() {
        em.getTransaction().begin();
        points = em.createQuery("SELECT p FROM Point p").getResultList();
        em.getTransaction().commit();
        return points;
    }

}
