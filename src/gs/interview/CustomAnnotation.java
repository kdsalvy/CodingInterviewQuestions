package gs.interview;

import java.lang.annotation.*;

public class CustomAnnotation {

    @CustomAnnotationDefinition("Welcome")
    public String str;

    public static void main(String[] args){
        CustomAnnotation ca = new CustomAnnotation();
        Annotation[] annotations = CustomAnnotation.class.getFields()[0].getDeclaredAnnotations();
        for(Annotation ann : annotations){
            System.out.print(ann);
        }
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface CustomAnnotationDefinition{
    String value();
}