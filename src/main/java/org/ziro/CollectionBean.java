package org.ziro;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@Named
@ApplicationScoped
public class CollectionBean {


    @Inject
    private DataBean dataBean;


    public void processRequest() {
        List<Double> selectedRs = dataBean.getSelectedRs();
        for (Double selectedR : selectedRs) {
            System.out.println("R: " + selectedR + " X: " + dataBean.getValue() + " Y: " + dataBean.getText());

        }


    }
}
