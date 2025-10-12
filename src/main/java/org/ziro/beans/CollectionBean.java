package org.ziro.beans;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import org.ziro.HitChecker;

import java.util.List;

@Named
@ApplicationScoped
public class CollectionBean {


    @Inject
    private DataBean dataBean;

    private HitChecker hitChecker = new HitChecker();


    public void processRequest() {
        List<Double> selectedRs = dataBean.getSelectedRs();
        for (Double selectedR : selectedRs) {
            try {
                double x = dataBean.getValue();
                double y=Double.parseDouble(dataBean.getText());
                System.out.println("R: " + selectedR + " X: " + x + " Y: " + y +" Hit: "
                        + hitChecker.isHit(selectedR,x,y));
            } catch (NumberFormatException e) {
                PrimeFaces.current().executeScript("showNotification('Не делай так больше. Введи валидные X,Y,R')");

            }


        }


    }
}
