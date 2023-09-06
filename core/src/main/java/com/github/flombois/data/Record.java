package com.github.flombois.data;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.MappingStrategy;
import jakarta.validation.constraints.NotNull;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Record {

    public final static int SCALE = 2;
    public final static RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    @NotNull
    @CsvBindByPosition(position = 0, required = true)
    @XmlAttribute
    private int reference;

    @NotNull
    @CsvBindByPosition(position = 1, required = true)
    @XmlElement
    private String accountNumber;

    @NotNull
    @CsvBindByPosition(position = 2, required = true)
    private String description;

    @NotNull
    @CsvBindByPosition(position = 3, required = true)
    @XmlElement
    private BigDecimal startBalance;
    @NotNull
    @CsvBindByPosition(position = 4, required = true)
    @XmlElement
    private BigDecimal mutation;
    @NotNull
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
