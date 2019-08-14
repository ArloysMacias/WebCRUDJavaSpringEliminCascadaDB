package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.ParseConversionEvent;
import model.Conectar;
import model.Persona;
import model.PersonaValidation;
import org.springframework.dao.DataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("edit.htm")
public class EditController 
{
    PersonaValidation usuariosValidar;
    private JdbcTemplate jdbcTemplate;
     
    
    public EditController() 
    {
        this.usuariosValidar=new PersonaValidation();
        Conectar con=new Conectar();
        this.jdbcTemplate=new JdbcTemplate(con.conectar() );
    }
    
    //Lo que sale al principio.....lo que crea la vista
    
    //Para coger y poner los datos
    //Del objeto que añado como persona ( mav.addObject("persona"....) voy a coger sus parametros en el path del formulario
    //ejemplo path="nombre"  se tiene que llamar igual que el atributo de la clase Persona .. per.nombre
    //que se le paso al modelo en ...mav.addObject("persona", per);
    @RequestMapping(method=RequestMethod.GET) 
    public ModelAndView form(HttpServletRequest request)
    {
        ModelAndView mav=new ModelAndView();
        int id=Integer.parseInt(request.getParameter("id"));
        Persona datos=this.selectUsuario(id);
        mav.setViewName("edit");
        mav.addObject("persona",new Persona(id,datos.getNombre(),datos.getCorreo(),datos.getPais(),datos.getEdad(),datos.getFecha()));
        this.probar(request);
        return mav;
    }
    
    
    //lo que hace cuando doy clic en el boton editar
    
    //Lo que hace cuando sale del formulario    de la pagina edit.htm
    //recibio y valido los datos del formulario
    //actualizo la tabla
    //Para coger y poner los datos
    //Del objeto que añado como persona ( mav.addObject("persona"....) voy a coger sus parametros en el path del formulario
    //ejemplo path="nombre"  se tiene que llamar igual que el atributo de la clase Persona .. per.nombre
    //que se le paso al modelo en ...mav.addObject("persona", per);
    @RequestMapping(method=RequestMethod.POST)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    public ModelAndView form
        (
                @ModelAttribute("persona") Persona u,//Modelo que se valida (Con los datos del formulario cuando se presiona el boton editar) mas adelante
                BindingResult result,
                SessionStatus status,
                HttpServletRequest request
        )
    {
        this.usuariosValidar.validate(u, result);//Aqui se valida ese modelo "Persona u"
        if(result.hasErrors())
        {
            //Si hay errores se siene que quedar en esa vista es decir mostrar la misma pagina
            //y no salir de esa pagina hasta que se corrigan los errores
            ModelAndView mav=new ModelAndView();
            int id=Integer.parseInt(request.getParameter("id"));
            Persona datos=this.selectUsuario(id);
            mav.setViewName("edit");
            //Aqui si el modelo esta ya bueno y con todos los datos valiod CREO EL NUEVO OBJETO PERSONA 
            //Y CREO EL NUEVO MODELO CON ESA PERSONA NUEVA  ("persona")
            //Tengo que poner "persona" como mismo esta en el modelo de arriva porque una vez que se corrigan
            //los datos y se presione de nuevo el boton ya no volvera a pasar por aqui y se ira nuevamente a la linea 73
            // @ModelAttribute("persona") Persona u    se llamara de nuevo a la funcion form con ese parametro y tiene que llamarse  "persona"
            mav.addObject("persona",new Persona(id,datos.getNombre(),datos.getCorreo(),datos.getPais(),datos.getEdad(),datos.getFecha()));
            return mav;//mostrar la misma pagina quedarse en esa pagina
        }else
        {
            int id=Integer.parseInt(request.getParameter("id"));
            String sql="update usuarios set nombre=?,email=?,edad=?,fechaNac=?,pais=? where id=?";
        this.jdbcTemplate.update(sql,u.getNombre(),u.getCorreo(),u.getEdad(),u.getFecha(),nombrePaisPorId(Integer.parseInt(u.getPais())),id);
         return new ModelAndView("redirect:/home.htm");
        }
       
    }
    public Persona selectUsuario(int id) 
    {
        final Persona user = new Persona();
        String quer = "SELECT * FROM usuarios WHERE id='" + id+"'";
        //Extrae una persona específica de la base de datos y
        //La convierte en persona
        return (Persona) jdbcTemplate.query(quer, new ResultSetExtractor<Persona>() 
            {
                public Persona extractData(ResultSet rs) throws SQLException, DataAccessException {
                    if (rs.next()) {
                        //Coge el valor de la BD de cada columnda (nombre..email..etc) de la fila 
                        //del usuario q encontró de la consulta que hace (quer)
                        user.setNombre(rs.getString("nombre"));
                        user.setCorreo(rs.getString("email"));
                        user.setEdad(rs.getInt("edad"));
                        user.setFecha(rs.getString("fechaNac"));
                        user.setPais(rs.getString("pais"));
                        
                    }
                    return user;
                }


            }
        );
        
    }

//    //metodo para poblar el select
//    @ModelAttribute("paisListaPrueba")
//    //pais.put(Valor que coje en la base Datos, Valor que muestra la pagina)
//    public Map<String,String>listadoDePaisesPrueba(){
//        Map<String,String>pais= new LinkedHashMap<>();
//        pais.put("Cuba","Cuba");
//        pais.put("México","México");
//        pais.put("Colombia","Colombia");
//        pais.put("España","España");
//        pais.put("Suecia","Suecia");
//        return pais;
//    }
//    
        @ModelAttribute("paisLista")
    //pais.put(Valor que coje en la base Datos, Valor que muestra la pagina)
    public Map<String,String>listadoDePaises(){
        String sql="SELECT * FROM paises";
        List<Map<String,Object>> datos= this.jdbcTemplate.queryForList(sql);
        LinkedHashMap<String,String>paises= new LinkedHashMap<>();
        for(int i=0;i<datos.size();i++){
            paises.put(""+datos.get(i).get("idPais"),""+datos.get(i).get("nombrePais"));
        } 
        return paises;
    }
    
    @ModelAttribute("prueba")
    private Persona probar(HttpServletRequest request) {
        int id=Integer.parseInt(request.getParameter("id"));
        Persona esaPersona=this.selectUsuario(id);   
    return esaPersona;
    }
    
    public String nombrePaisPorId(int a){
        //Para agregar el nombre del pais tengo que encontrar 1ro el pais en la tabla de paises
        String sqlPais = "SELECT * FROM paises WHERE idPais='" +a+"'";
        List<Map<String,Object>> datos= this.jdbcTemplate.queryForList(sqlPais);
        String paise= ""+(datos.get(0).get("nombrePais"));
        return paise;
    }
    

}



