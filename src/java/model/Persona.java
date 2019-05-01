package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Persona {
    
    private int id;
    private String nombre;
    private String correo;
    private String pais;
    private int edad;
    private LocalDate Localfecha;
    private DateUtils dateUtils;
    
    
    
    private String fecha;
    
    /*   @InitBinder
    public void initBinder(final WebDataBinder binder) {
    final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
    binder.registerCustomEditor(Date.class, editor);
    }*/
    
    
    public void fechaLocal() {
        this.Localfecha = LocalDate.now();
    }
    
    public Persona() {
        DateUtils du= new DateUtils();
        this.dateUtils=du;
    }
    
    public Persona(int id, String nombre, String correo, String pais, int edad, String fecha) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.pais = pais;
        this.edad = edad;
        this.fecha = fecha;
    }
    
    /**
     *
     * @param id
     * @param nombre
     * @param correo
     * @param pais
     * @param edad
     * @param fecha
     */
    
    
    
    public int getEdad() {
        return edad;
    }
    
    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getCorreo() {
        return correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public String getPais() {
        return pais;
    }
    
    public void setPais(String pais) {
        this.pais = pais;
    }
    
    public String getFecha() {
        
        return fecha;
    }
    
    /*
    public Date getFechalocal() {
    this.Localfecha = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
    String text = Localfecha.format(formatter);
    LocalDate parsedDate = LocalDate.parse(text, formatter);
    System.out.println("Fecha:  "+parsedDate);
    return this.dateUtils.asDate(parsedDate);
    }
    */
    
    /*
    public Date getFechalocal() {
    this.Localfecha = LocalDate.now();
    return this.dateUtils.asDate(this.Localfecha);
    }
    */
    
    public LocalDate getFechalocal() {
        return this.Localfecha = LocalDate.now();
    }
    
    public String getFechalocalString() {
        this.Localfecha = LocalDate.now();
        return this.dateUtils.asDateString(this.Localfecha);
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    private String pattern = "yyyy-MM-dd";
    public void setFecha(LocalDate fecha) { 
        this.fecha=""+fecha.toString();
    }
    
    
    
    
    /*public String currentDate (){
    
    Date dNow = new Date();
    SimpleDateFormat ft =new SimpleDateFormat ("MM/dd/yyyy");
    String currentDate = ft.format(dNow);
    return currentDate;
    
    }
    
    public String hoy(){
    String txtDia="";
    Calendar miCalendario = Calendar.getInstance();
    Date eldia = miCalendario.getTime();
    txtDia=""+eldia;
    
    int diaHoy = miCalendario.get(Calendar.DAY_OF_MONTH);
    int mes=miCalendario.get(Calendar.MONTH);
    int anno =miCalendario.get(Calendar.YEAR);
    
    txtDia=""+diaHoy+"/"+mes+"/"+anno;
    return txtDia;
    }*/
    
    /*
    //@NumberFormat(pattern = "##,###.##")
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private BigDecimal salario;
    */
    
}
