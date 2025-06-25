package com.Spel.SpelLearning.spel;

import com.Spel.SpelLearning.service.ExpressionService;
import com.Spel.SpelLearning.service.PropertiesService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Spel")
public class SpelExp {
	
	


	@PostMapping("/expression")
	public ResponseEntity<ExpressionService> expression(@RequestBody ExpressionService service) {

	    StandardEvaluationContext context = new StandardEvaluationContext(service);
	    ExpressionParser parser = new SpelExpressionParser();

		/*
		 * Expression exp1 = parser.parseExpression("'Hello World'"); Object m1 =
		 * exp1.getValue(); System.out.println(m1.toString());
		 * 
		 * Expression exp2 = parser.parseExpression("'Hello World'.concat('!')"); Object
		 * m2 = exp2.getValue(); System.out.println(m2.toString());
		 * 
		 * Expression exp3 = parser.parseExpression("'Hello World'.bytes"); Object m3 =
		 * exp3.getValue(); System.out.println(m3.toString());
		 * 
		 * Expression exp4 = parser.parseExpression("'Hello World'.bytes.length");
		 * Object m4 = exp4.getValue(); System.out.println(m4.toString());
		 * 
		 * Expression exp5 = parser.parseExpression("'Hello World'.toUpperCase()");
		 * Object m5 = exp5.getValue(); System.out.println(m5.toString());
		 */

	    
	    for (String expString : service.getExpression()) {
	        Expression exp = parser.parseExpression(expString);
	        exp.getValue(context);
	        Object evaluatedValue = exp.getValue(context);
		    

		    
		    if (expString.contains("age")) {
		        if (evaluatedValue instanceof Integer) {
		            parser.parseExpression("age").setValue(context, ((Integer) evaluatedValue).longValue());
		        } else if (evaluatedValue instanceof Long) {
		            parser.parseExpression("age").setValue(context, evaluatedValue);
		        } else {
		            throw new IllegalArgumentException("Expression result for age is not numeric");
		        }
		    } else if (expString.contains("name")) {
		        parser.parseExpression("name").setValue(context, String.valueOf(evaluatedValue));
		    } else if (expString.contains("address")) {
		        parser.parseExpression("address").setValue(context, String.valueOf(evaluatedValue));
		    } else {
		        throw new IllegalArgumentException("Expression does not target a valid field (name, age, address)");
		    }
	    }
	    

	    return ResponseEntity.ok(service);
	}


    public static String print_name() {
        return "udhayakumar";
    }

    @PostMapping("/properties")
    public ResponseEntity<String> properties() throws NoSuchMethodException, SecurityException {

        ExpressionParser parser = new SpelExpressionParser();

        Map<String, String> maps1 = new HashMap<String, String>();
        maps1.put("name", "udhay");
        maps1.put("role", "software Developer");
        maps1.put("age", "22");

        ArrayList<String> arrays1 = new ArrayList<String>();
        arrays1.add("udhay");
        arrays1.add("software Developer");
        arrays1.add("22");

        HashMap<String, String> maps2 = new HashMap<String, String>();
        maps2.put("name", "udhay");
        maps2.put("role", "software Developer");
        maps2.put("age", "23");

        PropertiesService properties = new PropertiesService(maps1, arrays1, maps2);

        StandardEvaluationContext context = new StandardEvaluationContext(properties);

        Expression exp1 = parser.parseExpression("map1['name']");
        Object m1 = exp1.getValue(context);
        System.out.println(m1.toString());

        Expression exp2 = parser.parseExpression("array1[0]");
        Object m2 = exp2.getValue(context);
        System.out.println(m2.toString());

        Expression exp3 = parser.parseExpression("map2['name']");
        Object m3 = exp3.getValue(context);
        System.out.println(m3.toString());

        List<Integer> exp4 = (List) parser.parseExpression("{1,2,3}").getValue();
        System.out.println(exp4.toString());

        int exp5[] = (int[]) parser.parseExpression("new int[]{1,2,3}").getValue();
        for (int i = 0; i < exp5.length; i++) {
            System.out.println(exp5[i]);
        }

        StandardEvaluationContext context1 = new StandardEvaluationContext();
        context1.registerFunction("udhay", SpelExp.class.getDeclaredMethod("print_name"));

        Object result = parser.parseExpression("#udhay()").getValue(context1);

        String exp6 = (String) parser.parseExpression("'udhay'.substring(0,2)").getValue();
        System.out.println(exp6);

        return ResponseEntity.status(HttpStatus.OK).body(result.toString());
    }

    @PostMapping("/operators")
    public ResponseEntity<String> operators() {

        ExpressionParser parser = new SpelExpressionParser();

        Expression exp1 = parser.parseExpression("5==5");
        Object m1 = exp1.getValue();
        System.out.println(m1.toString());

        Expression exp2 = parser.parseExpression("5==5");
        Object m2 = exp2.getValue();
        System.out.println(m2.toString());

        Expression exp3 = parser.parseExpression("5==5 and 5==4");
        Object m3 = exp3.getValue();
        System.out.println(m3.toString());

        Expression exp4 = parser.parseExpression("5==5 or 5==4");
        Object m4 = exp4.getValue();
        System.out.println(m4.toString());

        Expression exp5 = parser.parseExpression("!(5==5)");
        Object m5 = exp5.getValue();
        System.out.println(m5.toString());

        Expression exp6 = parser.parseExpression("5+5");
        Object m6 = exp6.getValue();
        System.out.println(m6.toString());

        ExpressionService service = new ExpressionService("udhay", "chennai", 22);
        StandardEvaluationContext context = new StandardEvaluationContext(service);

        context.setVariable("dept", "ComputerScience");

        parser.parseExpression("name").setValue(context, "Kumar");

        parser.parseExpression("name = #dept").getValue(context);

        Expression exp7 = parser.parseExpression("name == 'ComputerScience' ? 'true' : 'false'");
        Object m7 = exp7.getValue(context);
        System.out.println(m7.toString());

        Expression exp8 = parser.parseExpression("name ?: 'false'");
        Object message = exp8.getValue(context);

        return ResponseEntity.status(HttpStatus.OK).body(message.toString());
    }
}
