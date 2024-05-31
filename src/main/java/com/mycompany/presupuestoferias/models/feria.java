package com.mycompany.presupuestoferias.models;

import java.sql.Date;

public class feria {

    //Atributos
    private String id;
    private String name;
    private String district;
    private int aforo;
    private double monto;
    private String status;
    private String statusApro;
    private String category;
    private Date dateInicio;
    private Date dateFin;
    private double rentabilidad;
    private double utilidad;
    private double egresos;
    private double ingresos;
    private String comentarios;

    //Constructor vacio de la clase Feria
    public feria() {
    }

    /**
     * Constructor de la clase feria.
     *
     * @param id ID de la feria.
     * @param name Nombre de la feria.
     * @param district Distrito de la feria.
     * @param aforo Aforo de la feria.
     * @param monto Monto de la feria.
     * @param status Estado de la feria.
     * @param statusApro Estado de aprobacion de la feria
     * @param category Categoría de la feria.
     * @param dateInicio Fecha de inicio de la feria.
     * @param dateFin Fecha de fin de la feria.
     * @param rentabilidad Rentabilidad de la feria
     * @param utilidad Utilidad de la feria
     * @param egresos Egresos total de la feria
     * @param ingresos Ingresos total de la feria
     * @param comentarios Comentarios de la feria
     */
    public feria(String id, String name, String district, int aforo, double monto, String status, String statusApro, String category, Date dateInicio, Date dateFin, double rentabilidad, double utilidad, double egresos, double ingresos, String comentarios) {
        this.id = id;
        this.name = name;
        this.district = district;
        this.aforo = aforo;
        this.monto = monto;
        this.status = status;
        this.statusApro = statusApro;
        this.category = category;
        this.dateInicio = dateInicio;
        this.dateFin = dateFin;
        this.rentabilidad = rentabilidad;
        this.utilidad = utilidad;
        this.egresos = egresos;
        this.ingresos = ingresos;
        this.comentarios = comentarios;
    }

    //Metodos get-set
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getAforo() {
        return aforo;
    }

    public void setAforo(int aforo) {
        this.aforo = aforo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusApro() {
        return statusApro;
    }

    public void setStatusApro(String statusApro) {
        this.statusApro = statusApro;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDateInicio() {
        return dateInicio;
    }

    public void setDateInicio(Date dateInicio) {
        this.dateInicio = dateInicio;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public double getRentabilidad() {
        return rentabilidad;
    }

    public void setRentabilidad(double rentabilidad) {
        this.rentabilidad = rentabilidad;
    }

    public double getUtilidad() {
        return utilidad;
    }

    public void setUtilidad(double utilidad) {
        this.utilidad = utilidad;
    }

    public double getEgresos() {
        return egresos;
    }

    public void setEgresos(double egresos) {
        this.egresos = egresos;
    }

    public double getIngresos() {
        return ingresos;
    }

    public void setIngresos(double ingresos) {
        this.ingresos = ingresos;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
}
