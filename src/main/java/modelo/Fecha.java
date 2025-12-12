package modelo;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author Homero Ramírez
 */
public class Fecha {

    private int dia;
    private int mes;
    private int anio;

    public Fecha() {
        this(0, 0, 0);
    }
    
    public Fecha(Date sqlDate){
        LocalDate localDate = sqlDate.toLocalDate();
        this.dia = localDate.getDayOfMonth();
        this.mes = localDate.getMonthValue();
        this.anio = localDate.getYear();
    }
    
    public Fecha(LocalDate localDate) {
        this.dia = localDate.getDayOfMonth();  
        this.mes = localDate.getMonthValue();
        this.anio = localDate.getYear();
    }
    
/**
 * 
 * @param dia
 * @param mes
 * @param anio 
 */
    public Fecha(int dia, int mes, int anio) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }
/**
 * 
 * @return 
 */
    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }
    
    public String verFecha(){
        return this.dia + "/" + this.mes + "/" + this.anio;
    }
    
    public Date toSqlDate() {
        LocalDate localDate = LocalDate.of(anio, mes, dia);
        return Date.valueOf(localDate);
    }

    @Override
    public String toString() {
        return anio + "-" + mes + "-" + dia;
    }
    
    public LocalDate toLocalDate() {
        return LocalDate.of(this.anio, this.mes, this.dia);
    }
}
