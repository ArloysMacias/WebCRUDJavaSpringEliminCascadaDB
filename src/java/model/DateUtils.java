
package model;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.format.annotation.DateTimeFormat;






public class DateUtils {
    
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    
    private Date date;
    
    
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    
    public DateUtils(Date date) {
        this.date = date;
    }
    
    public DateUtils() {
    }
    
    
    /*
    public Date asDate(LocalDate localDate) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
    String text = localDate.format(formatter);
    LocalDate parsedDate = LocalDate.parse(text, formatter);
    this.date=Date.from(parsedDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    try {
    formatearFecha(this.date);
    } catch (ParseException ex) {
    Logger.getLogger(DateUtils.class.getName()).log(Level.SEVERE, null, ex);
    }
    System.out.println("La fecha del DateUtils es:  "+this.date);
    return this.date;
    }*/
    
    private String pattern = "dd/LL/yyyy";
    public Date asDate(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String text = localDate.format(formatter);
        this.date=convierteStringToDate(text);
        System.out.println("La fecha del DateUtils es:  "+this.date);
        return this.date;
    }
    
    public String asDateString(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String text = localDate.format(formatter);
        LocalDate parsedDate = LocalDate.parse(text, formatter);
        this.date=Date.from(parsedDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        String dateString=convierteDateToString(this.date);
        System.out.println("La fecha del DateUtils es:  "+dateString);
        return convierteDateToString(this.date);
    }
    
    public Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
    
    public LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }
    
    public static LocalDateTime asLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
    
    /*public void formatearFecha(Date fechaEntrada) throws ParseException {
    SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss");
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Date date = new Date();
    date=parser.parse(convierteFechaString(fechaEntrada));
    System.out.println(formatter.format(date));
    formatter.format(date);
    }*/
    
    public Date convierteStringToDate(String fechaString){
        DateFormat format = new SimpleDateFormat(pattern, Locale.ENGLISH);
        Date date1=new Date();
        try {
            date1=format.parse(fechaString);
        } catch (ParseException ex) {
            Logger.getLogger(DateUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date1;
    }
    
    public String convierteDateToString(Date fecha){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String fechaComoCadena = sdf.format(fecha);
        return fechaComoCadena;
    }
    
    
}
