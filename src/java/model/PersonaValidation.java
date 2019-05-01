package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class PersonaValidation implements Validator {
    
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    private Pattern pattern;
    private Matcher matcher;
    
    
    @Override
    public boolean supports(Class<?> type) {
        return Persona.class.isAssignableFrom(type);
        //return TuClase.class.isAssignableFrom(type);
    }
    
    @Override
    public void validate(Object o, Errors errors) {
        Persona p = (Persona) o;
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre",
                "required.nombre", "The name must be entered");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "correo",
                "required.correo", "The e-mail must be entered");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fecha",
                "required.nombre", "The date of birth must be entered");
        
        String correo =""+p.getCorreo();
        if (!(p.getCorreo() != null && correo.isEmpty()))
        {
            this.pattern = Pattern.compile(EMAIL_PATTERN);
            this.matcher = pattern.matcher(""+p.getCorreo());
            if (!matcher.matches()) {
                errors.rejectValue("correo", "correo.incorrect",
                        "The e-mail:  "+p.getCorreo()+" is not valid");
                System.out.println("El error fe en el correo :NO ES VALIDO: "+p.getCorreo());
            }else{
                System.out.println("El correo matcha bien y no esta vacio: "+p.getCorreo());
            }
            
        }else{
            System.out.println("El correo esta vacio: "+p.getCorreo());
        }
        
        
        if(p.getPais().equalsIgnoreCase("ninguno"))
        {
            System.out.println("El error fe en el pais: "+p.getPais());
            errors.rejectValue("pais", "required.pais","Select a country");
        }else{
            System.out.println("No hay error en pais: "+p.getPais());
        }
        System.out.println("El error es  : "+errors.getAllErrors());
    }
    
}
