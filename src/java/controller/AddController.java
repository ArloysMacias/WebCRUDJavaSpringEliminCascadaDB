
package controller;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import model.Conectar;
import model.Pais;
import model.Persona;
import model.PersonaValidation;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
    private List tablaPaises;
    
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
        ModelAndView mav=crearModeloConNombre("add");
        return mav;
    }
    
    public ModelAndView crearModeloConNombre(String nombreVista){  
        ModelAndView mav=new ModelAndView();
        Persona per=new Persona();//Solo se utiliza para la fecha de hoy, lo demas esta vacio
        per.setFecha(per.getFechalocal());//Para que salga la fecha de hoy  
        mav.setViewName(nombreVista); //Nombre de la vista
        mav.addObject("persona", per);//Esta persona esta vacia pero es la que se utiliza para mostrar la fecha actual es decir no muestra los datos pero si la fecha de hoy
        mav.addObject("listaPaises",listadoDePaises());//Este es el que sale en el desplegable "Select......"     LinkedHashMap   para el select
        return mav;
    }
    
    public ModelAndView crearModeloConNombreYPersona(String nombreVista, Persona p){  
        ModelAndView mav=new ModelAndView();
        mav.setViewName(nombreVista); //Nombre de la vista
        mav.addObject("persona", p);//Esta persona esta vacia pero es la que se utiliza para mostrar la fecha actual es decir no muestra los datos pero si la fecha de hoy
        mav.addObject("listaPaises",listadoDePaises());//Este es el que sale en el desplegable "Select......"                 LinkedHashMap   para el select
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
    public ModelAndView form
        (
                @ModelAttribute("persona")Persona p, //"persona" ==commandName en add.jsp
                BindingResult result,
                SessionStatus status
        )
    {
        
        this.personaValidacion.validate(p, result);
        if(result.hasErrors()){
            ModelAndView mav = crearModeloConNombreYPersona("add",p);
            return mav;
        }else{
            String sql="insert into usuarios (nombre,email,edad,fechaNac,pais)values(?,?,?,?,?)";
            this.jdbcTemplate.update(sql, p.getNombre(),p.getCorreo(),p.getEdad(),p.getFecha(),nombrePaisPorId(Integer.parseInt(p.getPais())));
            return new ModelAndView("redirect:/home.htm");
        }
    }
        
 
//    @RequestMapping(value = "/paisSS", method = RequestMethod.POST)
//    public ModelAndView formPais
//        (
//                @ModelAttribute("pais")Pais pais,
//                BindingResult result,
//                SessionStatus status
//        )
//    {  
////        this.personaValidacion.validatePais(pais, result);
////        if(result.hasErrors()){
////            ModelAndView mav=obtenerDatos("addPais");
////            return mav;
////        }else{
//            String sql="insert into paises (nombrePais,idPais)values(?,?)";
//            this.jdbcTemplate.update(sql, pais.getNombrePais(),pais.getId());
////            String sql2="insert into paises (nombrePais)values(?)";
////            this.jdbcTemplate.update(sql2, pais.getNombrePais());     
//            return new ModelAndView("redirect:/add.htm");
////        }
//    }
     
    public String nombrePaisPorId(int a){
        //Para agregar el nombre del pais tengo que encontrar 1ro el pais en la tabla de paises
        String sqlPais = "SELECT * FROM paises WHERE idPais='" +a+"'";
        List<Map<String,Object>> datos= this.jdbcTemplate.queryForList(sqlPais);
        String paise= ""+(datos.get(0).get("nombrePais"));
        return paise;
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
    
    
}
