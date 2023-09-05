package validators;

import com.github.flombois.data.Record;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Random;

public final class RecordUtils {

    private RecordUtils() {
        // Prevent instantiation from outside
    }

    public static Record of(int transactionReference, String accountNumber, String description,
                            BigDecimal startBalance, BigDecimal mutation, BigDecimal endBalance) {
        return new Record(transactionReference, accountNumber, description, startBalance, mutation, endBalance);
    }

    public static Record withBalance(BigDecimal startBalance, BigDecimal mutation, BigDecimal endBalance) {
        return of(0, "NL91ABNA0417164300", "", startBalance, mutation,
                endBalance);
    }

    public static Record withTransactionReference(int transactionReference) {
        return of(transactionReference, "NL91ABNA0417164300", "", BigDecimal.ZERO,
                BigDecimal.ZERO, BigDecimal.ZERO);
    }

    public static Record randomValid(int transactionReference) {
        Random random = new Random();
        BigDecimal startBalance = BigDecimal.valueOf(random.nextDouble()).setScale(Record.SCALE, Record.ROUNDING_MODE);
        BigDecimal mutation = BigDecimal.valueOf(random.nextDouble()).setScale(Record.SCALE, Record.ROUNDING_MODE);
        BigDecimal endBalance = startBalance.add(mutation);
        return of(transactionReference, "NL91ABNA0417164300", "",
                startBalance, mutation, endBalance);
    }

    public static Record randomInvalid(int transactionReference) {
        Random random = new Random();
        BigDecimal startBalance = BigDecimal.valueOf(random.nextDouble()).setScale(Record.SCALE, Record.ROUNDING_MODE);
        BigDecimal mutation = BigDecimal.valueOf(random.nextDouble()).setScale(Record.SCALE, Record.ROUNDING_MODE);
        BigDecimal endBalance = BigDecimal.valueOf(random.nextDouble()).setScale(Record.SCALE, Record.ROUNDING_MODE);
        return of(transactionReference, "NL91ABNA0417164300", "",
                startBalance, mutation, endBalance);
    }
}
