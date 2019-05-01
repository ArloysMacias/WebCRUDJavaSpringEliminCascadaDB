/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.servlet.http.HttpServletRequest;
import model.Conectar;
import model.Persona;
import model.PersonaValidation;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class DeleteController {

    private PersonaValidation personaValidacion;
    private JdbcTemplate jdbcTemplate;
    
    public DeleteController() {
        this.personaValidacion=new PersonaValidation();
        Conectar con= new Conectar();
        this.jdbcTemplate=new JdbcTemplate(con.conectar());
    }
    
    @RequestMapping ("delete.htm")
    public ModelAndView home(HttpServletRequest request){
        String idParametro=request.getParameter("id");
        int idInt=Integer.parseInt(idParametro);
        String sql="delete from usuarios where id=?";
        this.jdbcTemplate.update(sql,idInt);
        return new ModelAndView("redirect:/home.htm");
    }

  
}
