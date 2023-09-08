package com.github.flombois.data;

import com.opencsv.bean.CsvBindByPosition;
import jakarta.xml.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Java bean that holds record's properties
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Record {

    /**
     * Number of decimal digits used for BigDecimal computing
     * @see BigDecimal
     */
    public static final int SCALE = 2;

    public static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    @CsvBindByPosition(position = 0, required = true)
    @XmlAttribute
    private int reference;

    @CsvBindByPosition(position = 1, required = true)
    @XmlElement
    private String accountNumber;

    @CsvBindByPosition(position = 2, required = true)
    private String description;

    @CsvBindByPosition(position = 3, required = true)
    @XmlElement
    private BigDecimal startBalance;
    @CsvBindByPosition(position = 4, required = true)
    @XmlElement
    private BigDecimal mutation;

    @CsvBindByPosition(position = 5, required = true)
    @XmlElement
    private BigDecimal endBalance;

    public Record() {}

    public Record(int reference, String accountNumber, String description,
                  BigDecimal startBalance, BigDecimal mutation, BigDecimal endBalance) {
        this.reference = reference;
        this.accountNumber = accountNumber;
        this.description = description;
        this.startBalance = startBalance.setScale(SCALE, ROUNDING_MODE);
        this.mutation = mutation.setScale(SCALE, ROUNDING_MODE);
        this.endBalance = endBalance.setScale(SCALE, ROUNDING_MODE);
    }

    public int getReference() {
        return reference;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getStartBalance() {
        return startBalance;
    }

    public void setStartBalance(BigDecimal startBalance) {
        this.startBalance = startBalance;
    }

    public BigDecimal getMutation() {
        return mutation;
    }

    public void setMutation(BigDecimal mutation) {
        this.mutation = mutation;
    }

    public BigDecimal getEndBalance() {
        return endBalance;
    }

    public void setEndBalance(BigDecimal endBalance) {
        this.endBalance = endBalance;
    }
}
