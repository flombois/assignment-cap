package validators;

import com.github.flombois.validators.BalanceValidator;
import com.github.flombois.validators.ValidationError;
import com.github.flombois.validators.ValidationResult;
import org.junit.jupiter.api.Test;

import com.github.flombois.data.Record;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class BalanceValidatorTest {

    @Test
    public void givenEndBalanceMatchStartBalancePlusMutation_whenValidate_thenRecordIsValid() {
        ValidationResult<Record> result = new BalanceValidator().validate(RecordUtils.withBalance(
                BigDecimal.valueOf(12.50), BigDecimal.valueOf(2.60), BigDecimal.valueOf(15.10)));
        assertNotNull(result);
        assertTrue(result.isValid());
        assertEquals(ValidationError.NO_ERROR,  result.getValidationError());
    }

    @Test
    public void givenEndBalanceMatchStartBalanceMinusMutation_whenValidate_thenRecordIsValid() {
        ValidationResult<Record> result = new BalanceValidator().validate(RecordUtils.withBalance(
                BigDecimal.valueOf(12.50), BigDecimal.valueOf(-2.60), BigDecimal.valueOf(9.90)));
        assertNotNull(result);
        assertTrue(result.isValid());
        assertEquals(ValidationError.NO_ERROR,  result.getValidationError());
    }

    @Test
    public void givenEndBalanceMatchStartBalance_whenValidate_thenRecordIsValid() {
        ValidationResult<Record> result = new BalanceValidator().validate(RecordUtils.withBalance(
                BigDecimal.valueOf(12.50), BigDecimal.ZERO, BigDecimal.valueOf(12.50)));
        assertNotNull(result);
        assertTrue(result.isValid());
        assertEquals(ValidationError.NO_ERROR,  result.getValidationError());
    }

    @Test
    public void givenEndBalanceMismatchStartBalancePlusMutation_whenValidate_thenRecordIsInvalid() {
        ValidationResult<Record> result = new BalanceValidator().validate(RecordUtils.withBalance(
                BigDecimal.valueOf(12.50), BigDecimal.valueOf(2.60), BigDecimal.valueOf(13.30)));
        assertNotNull(result);
        assertFalse(result.isValid());
        assertTrue(result.getValidationError().errorMessage().contains("balance is invalid"));
    }

    @Test
    public void givenEndBalanceMismatchStartBalanceMinusMutation_whenValidate_thenRecordIsInvalid() {
        ValidationResult<Record> result = new BalanceValidator().validate(RecordUtils.withBalance(
                BigDecimal.valueOf(12.50), BigDecimal.valueOf(-2.60), BigDecimal.valueOf(8.70)));
        assertNotNull(result);
        assertFalse(result.isValid());
        assertTrue(result.getValidationError().errorMessage().contains("balance is invalid"));
    }

    @Test
    public void givenEndBalanceMismatchStartBalance_whenValidate_thenRecordIsInvalid() {
        ValidationResult<Record> result = new BalanceValidator().validate(RecordUtils.withBalance(
                BigDecimal.valueOf(12.50), BigDecimal.ZERO, BigDecimal.valueOf(-10.40)));
        assertNotNull(result);
        assertFalse(result.isValid());
        assertTrue(result.getValidationError().errorMessage().contains("balance is invalid"));
    }
}
