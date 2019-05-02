/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Conectar;
import model.Persona;
import model.PersonaValidation;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author pc
 */
public class AddPaisController {
    
    
    private PersonaValidation paisValidacion;
    private JdbcTemplate jdbcTemplate;
    
    public AddPaisController() {
        this.paisValidacion=new PersonaValidation();
        Conectar con= new Conectar();
        this.jdbcTemplate=new JdbcTemplate(con.conectar());
    }
    
    //Para coger y poner los datos
    //Del objeto que añado como pais ( mav.addObject("pais"....) voy a coger sus parametros en el path del formulario
    //ejemplo path="nombre"  se tiene que llamar igual que el atributo de la clase Persona .. per.nombre
    //que se le paso al modelo en ...mav.addObject("pais", per);
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView form(){
        Persona per=new Persona();
        per.setFecha(per.getFechalocal());
        ModelAndView mav=new ModelAndView();
        mav.setViewName("add");
        mav.addObject("pais", per);
        return mav;
    }
    
    /*
    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    */
    
    //recibio y valido los datos del formulario
    //actualizo la tabla
    
    //Para coger y poner los datos
    //Del objeto que añado como pais ( mav.addObject("pais"....) voy a coger sus parametros en el path del formulario
    //ejemplo path="nombre"  se tiene que llamar igual que el atributo de la clase Persona .. per.nombre
    //que se le paso al modelo en ...mav.addObject("pais", per);
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView form(@ModelAttribute("pais")Persona p,BindingResult result,SessionStatus status){
        
        this.paisValidacion.validate(p, result);
        if(result.hasErrors()){
            ModelAndView mav = new ModelAndView();
            mav.setViewName("add");
            mav.addObject("pais", new Persona());
            return mav;
        }else{
            String sql="insert into usuarios (nombre,email,edad,fechaNac,pais)values(?,?,?,?,?)";
            this.jdbcTemplate.update(sql, p.getNombre(),p.getCorreo(),p.getEdad(),p.getFecha(),p.getPais());
            return new ModelAndView("redirect:/home.htm");
        }
    }
    
    
}
