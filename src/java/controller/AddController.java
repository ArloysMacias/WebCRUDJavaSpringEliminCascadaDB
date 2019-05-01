
package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import static javax.print.PrintServiceLookup.registerService;
import model.Conectar;
import model.DateUtils;
import model.Persona;
import model.PersonaValidation;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
//import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("add.htm")
public class AddController {
    
    /*
    @RequestMapping("/")
    public String home(Model model) {
    model.addAttribute("empleado", new Persona());
    return "home";
    }*/
    
    private PersonaValidation personaValidacion;
    private JdbcTemplate jdbcTemplate;
    
    public AddController() {
        this.personaValidacion=new PersonaValidation();
        Conectar con= new Conectar();
        this.jdbcTemplate=new JdbcTemplate(con.conectar());
    }
    
    //Para coger y poner los datos
    //Del objeto que añado como persona ( mav.addObject("persona"....) voy a coger sus parametros en el path del formulario
    //ejemplo path="nombre"  se tiene que llamar igual que el atributo de la clase Persona .. per.nombre
    //que se le paso al modelo en ...mav.addObject("persona", per);
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView form(){
        Persona per=new Persona();
        per.setFecha(per.getFechalocal());
        ModelAndView mav=new ModelAndView();
        mav.setViewName("add");
        mav.addObject("persona", per);
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
    //Del objeto que añado como persona ( mav.addObject("persona"....) voy a coger sus parametros en el path del formulario
    //ejemplo path="nombre"  se tiene que llamar igual que el atributo de la clase Persona .. per.nombre
    //que se le paso al modelo en ...mav.addObject("persona", per);
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView form(@ModelAttribute("persona")Persona p,BindingResult result,SessionStatus status){
        
        this.personaValidacion.validate(p, result);
        if(result.hasErrors()){
            ModelAndView mav = new ModelAndView();
            mav.setViewName("add");
            mav.addObject("persona", new Persona());
            return mav;
        }else{
            String sql="insert into usuarios (nombre,email,edad,fechaNac,pais)values(?,?,?,?,?)";
            this.jdbcTemplate.update(sql, p.getNombre(),p.getCorreo(),p.getEdad(),p.getFecha(),p.getPais());
            return new ModelAndView("redirect:/home.htm");
        }
    }
    
    //metodo para poblar el select
    @ModelAttribute("paisLista")
    //pais.put(Valor que coje en la base Datos, Valor que muestra la pagina)
    public Map<String,String>listadoDePaises(){
        Map<String,String>pais= new LinkedHashMap<>();
        pais.put("Cuba","Cuba");
        pais.put("México","México");
        pais.put("Colombia","Colombia");
        pais.put("España","España");
        pais.put("Suecia","Suecia");
        return pais;
    }
    
    

}
