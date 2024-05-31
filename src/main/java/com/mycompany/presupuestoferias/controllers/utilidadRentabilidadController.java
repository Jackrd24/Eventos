package com.mycompany.presupuestoferias.controllers;

import com.mycompany.presupuestoferias.models.egreso;
import com.mycompany.presupuestoferias.models.egresoDao;
import com.mycompany.presupuestoferias.models.ingreso;
import com.mycompany.presupuestoferias.models.ingresoDao;
import com.mycompany.presupuestoferias.views.UtilidadRentabilidadView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Haisenberg
 */
public class utilidadRentabilidadController implements ActionListener {

    private UtilidadRentabilidadView utilRentaView;
    private egreso egreso_pres;
    private egresoDao egreso_presDao;
    private ingreso ingreso_pres;
    private ingresoDao ingreso_presDao;

    public utilidadRentabilidadController(UtilidadRentabilidadView utilRentaView, egreso egreso_pres, egresoDao egreso_presDao, ingreso ingreso_pres, ingresoDao ingreso_presDao) {
        this.utilRentaView = utilRentaView;
        this.egreso_pres = egreso_pres;
        this.egreso_presDao = egreso_presDao;
        this.ingreso_pres = ingreso_pres;
        this.ingreso_presDao = ingreso_presDao;
        //Boton calcular
        this.utilRentaView.btnCalcular.addActionListener(this);
        //Boton regresar
        this.utilRentaView.btnRegresar.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == utilRentaView.btnCalcular) {
            String idFeria = utilRentaView.txtIdFeriaStatus.getText();
            double egresosTotal = egreso_presDao.totalEgreso(idFeria);
            double ingresosTotal = ingreso_presDao.totalIngreso(idFeria);
            // Calcular la utilidad
            double utilidad = ingresosTotal - egresosTotal;
            double rentabilidad = (utilidad / ingresosTotal)*100;
            //Imprimir valores
            utilRentaView.txtUtilidad.setText(String.valueOf(utilidad));
            utilRentaView.txtRentabilidad.setText(String.valueOf(rentabilidad+" %"));
        }else if (e.getSource() == utilRentaView.btnRegresar) {
            utilRentaView.dispose();
        }

    }

}
