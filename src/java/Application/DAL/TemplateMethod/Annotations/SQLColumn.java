package Application.DAL.TemplateMethod.Annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SQLColumn {
    String name() default "";
}
