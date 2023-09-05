package validators;

import com.github.flombois.data.HashSetDataStore;
import com.github.flombois.data.Record;
import com.github.flombois.validators.DuplicateTransactionValidator;
import com.github.flombois.validators.RecordValidator;
import com.github.flombois.validators.ValidationError;
import com.github.flombois.validators.ValidationResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DuplicateTransactionValidatorTest {


    @Test
    public void givenReferenceDoesNotExists_whenValidate_thenRecordIsValid() {
        ValidationResult<Record> result = new DuplicateTransactionValidator(new HashSetDataStore<>())
                .validate(RecordUtils.withTransactionReference(0));
        assertNotNull(result);
        assertTrue(result.isValid());
        assertEquals(ValidationError.NO_ERROR, result.getValidationError());
    }

    @Test
    public void givenReferenceAlreadyExists_whenValidate_thenRecordIsInvalid() {
        RecordValidator<Record> validator = new DuplicateTransactionValidator(new HashSetDataStore<>());
        validator.validate(RecordUtils.withTransactionReference(0));
        ValidationResult<Record> result = validator.validate(RecordUtils.withTransactionReference(0));
        assertNotNull(result);
        assertFalse(result.isValid());
        assertTrue(result.getValidationError().errorMessage().contains("has already been processed"));
    }


}
