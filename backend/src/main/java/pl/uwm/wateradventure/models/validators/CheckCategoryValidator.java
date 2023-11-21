package pl.uwm.wateradventure.models.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pl.uwm.wateradventure.models.learning.category.Category;

import java.util.Arrays;

public class CheckCategoryValidator implements ConstraintValidator<pl.uwm.wateradventure.models.validators.Category, String> {

    @Override
    public boolean isValid(String givenCategory, ConstraintValidatorContext constraintValidatorContext) {
        return Arrays.stream(Category.values())
                .anyMatch(category -> category.enumValue.equals(givenCategory));
    }

}
