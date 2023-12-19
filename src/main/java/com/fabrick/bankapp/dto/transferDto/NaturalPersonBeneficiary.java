package com.fabrick.bankapp.dto.transferDto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class NaturalPersonBeneficiary implements Serializable {
    //required
    private String fiscalCode1;
    private String fiscalCode2;
    private String fiscalCode3;
    private String fiscalCode4;
    private String fiscalCode5;

    @JsonCreator
    public NaturalPersonBeneficiary(@JsonProperty("fiscalCode1") String fiscalCode1,
                                    @JsonProperty("fiscalCode2") String fiscalCode2,
                                    @JsonProperty("fiscalCode3") String fiscalCode3,
                                    @JsonProperty("fiscalCode4") String fiscalCode4,
                                    @JsonProperty("fiscalCode5") String fiscalCode5) {

        this.fiscalCode1 = fiscalCode1;
        this.fiscalCode2 = fiscalCode2;
        this.fiscalCode3 = fiscalCode3;
        this.fiscalCode4 = fiscalCode4;
        this.fiscalCode5 = fiscalCode5;
    }


    public String getFiscalCode1() {
        return fiscalCode1;
    }

    public void setFiscalCode1(String fiscalCode1) {
        this.fiscalCode1 = fiscalCode1;
    }


    public String getFiscalCode2() {
        return fiscalCode2;
    }

    public void setFiscalCode2(String fiscalCode2) {
        this.fiscalCode2 = fiscalCode2;
    }

    public String getFiscalCode3() {
        return fiscalCode3;
    }

    public void setFiscalCode3(String fiscalCode3) {
        this.fiscalCode3 = fiscalCode3;
    }

    public String getFiscalCode4() {
        return fiscalCode4;
    }

    public void setFiscalCode4(String fiscalCode4) {
        this.fiscalCode4 = fiscalCode4;
    }

    public String getFiscalCode5() {
        return fiscalCode5;
    }

    public void setFiscalCode5(String fiscalCode5) {
        this.fiscalCode5 = fiscalCode5;
    }


}

