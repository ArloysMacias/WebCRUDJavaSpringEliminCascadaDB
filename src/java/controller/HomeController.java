
package controller;

import java.util.List;
import model.Conectar;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


public class HomeController {
    
    private JdbcTemplate jdbcTemplate;
    
    public HomeController() {
        Conectar con= new Conectar();
        this.jdbcTemplate=new JdbcTemplate(con.conectar());
    }
    
    
    @RequestMapping("home.htm")
    public ModelAndView home(){
        String sql= "SELECT \n" +
                "  `usuarios`.id,\n" +
                "  `usuarios`.nombre,\n" +
                "  `usuarios`.email,\n" +
                "  `usuarios`.edad,\n" +
                "  `usuarios`.fechaNac,\n" +
                "  `usuarios`.pais\n" +
                "FROM\n" +
                "  `usuarios`\n" +
                "ORDER BY\n" +
                "  `usuarios`.id";
        List datos=this.jdbcTemplate.queryForList(sql);
        ModelAndView mav = new ModelAndView();
        mav.addObject("datos", datos);
        mav.setViewName("home");
        return mav;
        
    }
    
}
