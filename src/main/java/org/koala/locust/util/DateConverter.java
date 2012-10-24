package org.koala.locust.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
 
public class DateConverter implements Converter<String, Date> {  
    @Override  
    public Date convert(String source) {  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
        dateFormat.setLenient(false);  
        try {  
            return dateFormat.parse(source);  
        }
        catch (java.text.ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }         
        return null;  
    }
}
//public class DateConverter implements WebBindingInitializer {
//
//  private static Logger logger = LoggerFactory.getLogger(DateConverter.class);
//
//    @Override
//    public void initBinder(WebDataBinder binder, WebRequest request) {
//      logger.warn("I am initBinder");
//      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
//      binder.registerCustomEditor(Date.class, new CustomDateEditor(df,  
//                      false));  
//    }  
//
//}

//public class CustomerConverter implements Converter<String, Date> {  
//@Override  
//public Date convert(String source) {  
//    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
//    dateFormat.setLenient(false);  
//    try {  
//        return dateFormat.parse(source);  
//    } catch (ParseException e) {  
//        // TODO Auto-generated catch block  
//        e.printStackTrace();  
//    }         
//    return null;  
//} 