/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.servlet.http.HttpServletRequest;
import model.Conectar;
import model.PersonaValidation;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author pc
 */
public class DeletePais {
    private PersonaValidation personaValidacion;
    private JdbcTemplate jdbcTemplate;
    
    public DeletePais() {
        this.personaValidacion=new PersonaValidation();
        Conectar con= new Conectar();
        this.jdbcTemplate=new JdbcTemplate(con.conectar());
    }
    
    @RequestMapping ("deletePais.htm")
    public ModelAndView home(HttpServletRequest request){
        String idParametro=request.getParameter("id");
        int idInt=Integer.parseInt(idParametro);
        String sql="delete from paises where idPais=?";
        this.jdbcTemplate.update(sql,idInt);
        return new ModelAndView("redirect:/addPais.htm");
    }

  
    
}
