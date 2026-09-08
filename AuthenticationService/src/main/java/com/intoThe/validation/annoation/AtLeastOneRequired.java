package com.intoThe.validation.annoation;

import com.intoThe.validation.validators.AtLeastOneRequiredValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target({ElementType.TYPE})
/*----> Purpose: Specifies where your custom annotation can be applied.
@Target defines the program elements on which the custom annotation can be
used.If we use ElementType.FIELD, it means the annotation can only be applied to fields inside a class.*/
@Retention(RetentionPolicy.RUNTIME)
/*-----> Purpose: Defines how long the annotation is retained in the lifecycle.
@Retention(RetentionPolicy.RUNTIME) means the annotation will be available at runtime.
Spring’s validation engine (Hibernate Validator) needs to access the annotation during runtime to
execute the validator logic.*/
@Constraint(validatedBy = AtLeastOneRequiredValidator.class)

/*---->Purpose: Connects the annotation to a validator class.

@Constraint tells Spring that this annotation is a validation constraint.
The validatedBy attribute specifies the class (or classes) that will implement the validation logic.
This class must implement ConstraintValidator<AnnotationType, FieldType>.*/
public @interface AtLeastOneRequired {
    String message() default "Email or password must be there.Both can't be empty!...";
    //This is the default validation error message that will be returned when validation fails.
    //message() provides the default error message for the constraint. It is mandatory as per the
    // Bean Validation API. When the validator returns false, this message is shown to the user unless
    // overridden.

    Class<?>[] groups() default {};
    //groups() is used when you want to apply validation conditionally, typically in multi-step forms.
    /*User creation validation
    User update validation (less strict)*/
    /*groups() allows grouping multiple constraints so they can be applied conditionally. It’s rarely
    used in simple applications, but it must be included because the Bean Validation spec requires it.*/
    Class<? extends Payload>[] payload() default {};
    //payload() is used by advanced systems to carry metadata with the validation error.
    /*payload() is used to attach metadata to a constraint violation, such as severity levels.
    It’s rarely used in everyday applications, but must be present because the Bean Validation API expects it.*/

    /*In a custom validation annotation, three attributes are required by the Bean Validation
    specification:
    ---> message() defines the default error message when validation fails. It is always required.
    ---> groups() allows constraints to be validated in specific groups, enabling conditional validation.
    It's rarely used but must be present.
    ---> payload() lets developers attach metadata to constraints, for example, severity levels.
    Also rarely used but required by the spec.
    Even if we don’t use groups() or payload() in normal projects, they must be included to comply
    with the Bean Validation API.*/
}
