/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.Conectar;
import model.Pais;
import model.Persona;
import model.PersonaValidation;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author pc
 */
public class AddPaisController implements Validator {
    
    @Override
    public boolean supports(Class<?> type) {
        return Persona.class.isAssignableFrom(type);
        //return TuClase.class.isAssignableFrom(type);
    }
    
    private PersonaValidation paisValidacion;
    private JdbcTemplate jdbcTemplate;
    private List tablaPaises;
    
    public AddPaisController() {
        this.paisValidacion=new PersonaValidation();
        Conectar con= new Conectar();
        this.jdbcTemplate=new JdbcTemplate(con.conectar());
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView form(){
        ModelAndView mav=obtenerDatos("addPais");
        return mav;
    }

    //recibio y valido los datos del formulario
    //actualizo la tabla
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView formPais
        (
                @ModelAttribute("paisSS")Pais pais,//tiene que coincidir con el commandName en el addPais.jsp
                BindingResult result,
                SessionStatus status,
                Error errors
        )
    {  
        validate(pais, result);
        if(result.hasErrors()){
            ModelAndView mav=crearModeloConNombreYPais("addPais", pais);
            return mav;
        }else{          
            String sql="insert into paises (nombrePais,idPais)values(?,?)";
            this.jdbcTemplate.update(sql, pais.getNombrePais(),pais.getId());
//            String sql2="insert into paises (nombrePais)values(?)";
//            this.jdbcTemplate.update(sql2, pais.getNombrePais());     
            return new ModelAndView("redirect:/addPais.htm");
        }
    }
    
    public ModelAndView obtenerDatos(String nombreVista){  
        ModelAndView mav=new ModelAndView();  
        mav.setViewName(nombreVista); //Nombre de la vistamav.addObject("listaPaises",listadoDePaises());//Este es el que sale en el desplegable "Select......"                 LinkedHashMap   para el select
        mav.addObject("paisSS",new Pais());
        mav.addObject("tablaPaises",tablaPaises); //Este es el que sale en la tabla del modal esto es una matris   ArrayList para la tabla
        return mav;
    }
    
    public ModelAndView crearModeloConNombreYPais(String nombreVista, Pais p){  
        ModelAndView mav=new ModelAndView();
        mav.setViewName(nombreVista); //Nombre de la vistamav.addObject("listaPaises",listadoDePaises());//Este es el que sale en el desplegable "Select......"                 LinkedHashMap   para el select
        mav.addObject("paisSS",p);
        mav.addObject("tablaPaises",tablaPaises); //Este es el que sale en la tabla del modal esto es una matris   ArrayList para la tabla
        return mav;
    }
    
    
    @ModelAttribute("paisLista")
    //pais.put(Valor que coje en la base Datos, Valor que muestra la pagina)
    public Map<String,String>listadoDePaises(){
        String sql="SELECT * FROM paises";
        this.tablaPaises=this.jdbcTemplate.queryForList(sql);
        List<Map<String,Object>> datos= this.jdbcTemplate.queryForList(sql);
        LinkedHashMap<String,String>paises= new LinkedHashMap<>();
        for(int i=0;i<datos.size();i++){
            paises.put(""+datos.get(i).get("idPais"),""+datos.get(i).get("nombrePais"));
        } 
        return paises;
    }
    public String nombrePaisPorId(int a){
        //Para agregar el nombre del pais tengo que encontrar 1ro el pais en la tabla de paises
        String sqlPais = "SELECT * FROM paises WHERE idPais='" +a+"'";
        List<Map<String,Object>> datos= this.jdbcTemplate.queryForList(sqlPais);
        String paise= ""+(datos.get(0).get("nombrePais"));
        return paise;
    }
    
    public boolean existePais(String a){
        //Para agregar el nombre del pais tengo que encontrar 1ro el pais en la tabla de paises
        String sqlPais = "SELECT * FROM paises WHERE nombrePais='" +a+"'";
        List<Map<String,Object>> datos= this.jdbcTemplate.queryForList(sqlPais);
        if(datos.isEmpty())
            return false;
        return true;
    }
    
    @Override
    public void validate(Object o, Errors errors) {
        Pais p=(Pais)o;
        if(p.getNombrePais().equalsIgnoreCase(""))
        {
            System.out.println("El error fue en el pais: "+p.getNombrePais());
            errors.rejectValue("nombrePais", "required.nombrePais","Write the name of a Country");
        }
        if(existePais(p.getNombrePais())){
            errors.rejectValue("nombrePais", "required.nombrePais","This country already exist");
            System.out.println("El pais ya existe  : "+errors.getAllErrors());
        }
        else{
            System.out.println("No hay error en pais: "+p.getNombrePais());
        }
        System.out.println("El error es  : "+errors.getAllErrors());
    }
    
}
