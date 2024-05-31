package com.mycompany.presupuestoferias.controllers;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mycompany.presupuestoferias.models.feria;
import com.mycompany.presupuestoferias.models.feriaDao;
import com.mycompany.presupuestoferias.views.ReportePresupuestal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;

public class ReportePresController implements ActionListener {

    private ReportePresupuestal reportView;
    private feria feria_ropa;
    private feriaDao feriaRopaDao;
    private int reportNumber = 1;

    public ReportePresController(ReportePresupuestal reportView, feria feria_ropa, feriaDao feriaRopaDao) {
        this.reportView = reportView;
        this.feria_ropa = feria_ropa;
        this.feriaRopaDao = feriaRopaDao;
        //Boton de Guardar Estado
        this.reportView.btnGuardar.addActionListener(this);
        //Boton Generar pdf
        this.reportView.btnGenerarPDF.addActionListener(this);
        //Boton Regresar 
        this.reportView.btnRegresar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == reportView.btnGuardar) {
            String estadoAprobacion = reportView.cmbEstadoAprobacion.getSelectedItem().toString();
            String comentarios = reportView.textAreaComentarios.getText().trim(); // Trim para eliminar espacios en blanco
            String id_feria = reportView.txtIdFeriaStatus.getText().trim(); // Trim para eliminar espacios en blanco

            if (comentarios.isEmpty() || id_feria.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos antes de guardar.");
                return; // Sale del método sin continuar si algún campo está vacío.
            }

            // Modificar estado de aprobación
            if (feriaRopaDao.updateStatusApro(id_feria, comentarios, estadoAprobacion)) {
                cleanTable();
                listAllFerias();
                listAllFeriasCalc(id_feria);
                // Mostrar mensaje de éxito al guardar el estado
                JOptionPane.showMessageDialog(null, "Estado de la Feria guardado exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Ha ocurrido un error al registrar el estado de la Feria");
            }
        } else if (e.getSource() == reportView.btnGenerarPDF) {
            List<feria> listFerias = listAllFeriasData();
            List<feria> listFeriasCalc = listAllFeriasCalcData(reportView.txtIdFeriaStatus.getText());
            generatePDF(listFerias, listFeriasCalc, reportNumber);

            // Incrementar el número del informe para el siguiente informe
            reportNumber++;
        }else if (e.getSource() == reportView.btnRegresar) {
            reportView.dispose();
        }
    }

    //Metodo para listar todas las ferias
    public void listAllFerias() {
        List<feria> list = feriaRopaDao.listFeriaQuery();
        DefaultTableModel model1 = new DefaultTableModel();
        model1 = (DefaultTableModel) reportView.tableFeriaDatos.getModel();
        Object[] row = new Object[9];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getName();
            row[2] = list.get(i).getDistrict();
            row[3] = list.get(i).getCategory();
            row[4] = list.get(i).getAforo();
            row[5] = list.get(i).getDateInicio();
            row[6] = list.get(i).getDateFin();
            row[7] = list.get(i).getStatus();
            row[8] = list.get(i).getMonto();
            model1.addRow(row);
        }
    }

    //Metodo para listar todas las ferias
    public void listAllFeriasCalc(String id_feria) {
        List<feria> list = feriaRopaDao.listFeriaCalculoQuery(id_feria);
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) reportView.tablaFeriaCalculos.getModel();
        Object[] row = new Object[8];
        for (int i = 0; i < list.size(); i++) {
            row[0] = id_feria;
            row[1] = list.get(i).getEgresos();
            row[2] = list.get(i).getIngresos();
            row[3] = list.get(i).getEgresos();
            row[4] = list.get(i).getUtilidad();
            row[5] = list.get(i).getRentabilidad();
            row[6] = list.get(i).getStatusApro();
            row[7] = list.get(i).getComentarios();
            model.addRow(row);
        }
    }

    // Método para obtener todos los datos de la tabla 1
    public List<feria> listAllFeriasData() {
        return feriaRopaDao.listFeriaQuery();
    }

    // Método para obtener todos los datos de la tabla 2
    public List<feria> listAllFeriasCalcData(String id_feria) {
        return feriaRopaDao.listFeriaCalculoQuery(id_feria);
    }

    //Metodo para limpiar tabla
    public void cleanTable() {
        DefaultTableModel model1 = (DefaultTableModel) reportView.tableFeriaDatos.getModel();
        DefaultTableModel model2 = (DefaultTableModel) reportView.tablaFeriaCalculos.getModel();
        int rowCount1 = model1.getRowCount();
        for (int i = rowCount1 - 1; i >= 0; i--) {
            model1.removeRow(i);
        }
        int rowCount2 = model2.getRowCount();
        for (int i = rowCount2 - 1; i >= 0; i--) {
            model2.removeRow(i);
        }
    }

    public void generatePDF(List<feria> feriaList, List<feria> feriaCalcList, int reportNumber) {
        // Obtener el directorio del escritorio del usuario
        String desktopPath = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath();

        // Nombre del archivo de salida
        String outputFile = desktopPath + File.separator + "ferias_report" + reportNumber + ".pdf";

        try {
            Document document = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(document, new FileOutputStream(outputFile));
            document.open();

            // Add title
            document.add(new Paragraph("Reporte de Ferias"));

            // Add the table for the first list
            PdfPTable table1 = new PdfPTable(9);
            table1.setWidthPercentage(100);
            table1.setSpacingBefore(10f);
            table1.setSpacingAfter(10f);

            // Add table headers
            table1.addCell("Código de Feria");
            table1.addCell("Nombre de la Feria");
            table1.addCell("Distrito");
            table1.addCell("Categoria");
            table1.addCell("Aforo");
            table1.addCell("Fecha de Inicio");
            table1.addCell("Fecha de Cierre");
            table1.addCell("Estado de la Feria");
            table1.addCell("Monto de inversión inicial");

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            // Add table rows for the first list
            for (feria f : feriaList) {
                table1.addCell(String.valueOf(f.getId()));
                table1.addCell(f.getName());
                table1.addCell(f.getDistrict());
                table1.addCell(f.getCategory());
                table1.addCell(String.valueOf(f.getAforo()));

                // Formatear las fechas y agregarlas a la tabla
                String formattedDateInicio = dateFormat.format(f.getDateInicio());
                String formattedDateFin = dateFormat.format(f.getDateFin());
                table1.addCell(formattedDateInicio);
                table1.addCell(formattedDateFin);

                table1.addCell(f.getStatus());
                table1.addCell(String.valueOf(f.getMonto()));
            }

            // Add the table for the second list
            PdfPTable table2 = new PdfPTable(8);
            table2.setWidthPercentage(100);
            table2.setSpacingBefore(10f);
            table2.setSpacingAfter(10f);

            // Add table headers
            table2.addCell("Código de Feria");
            table2.addCell("Total de Egresos (S/.)");
            table2.addCell("Total de Ingresos (S/.)");
            table2.addCell("Presupuesto calculado (S/.)");
            table2.addCell("Utilidad (S/.)");
            table2.addCell("Rentabilidad (%)");
            table2.addCell("Estado de Aprobación");
            table2.addCell("Comentarios");

            // Add table rows for the second list
            for (feria f : feriaCalcList) {
                table2.addCell(String.valueOf(f.getId()));
                table2.addCell(String.valueOf(f.getEgresos()));
                table2.addCell(String.valueOf(f.getIngresos()));
                table2.addCell(String.valueOf(f.getEgresos()));
                table2.addCell(String.valueOf(f.getUtilidad()));
                table2.addCell(String.valueOf(f.getRentabilidad()));
                table2.addCell(f.getStatusApro());
                table2.addCell(f.getComentarios());
            }

            // Add tables to the document
            document.add(table1);
            document.add(table2);

            document.close();

            JOptionPane.showMessageDialog(null, "PDF GENERADO CON EXITO");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
