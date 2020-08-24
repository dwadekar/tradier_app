package com.iit.spring.learning.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SymbolType {
    STOCK("stock"), OPTION("option"), ETF("etf"), INDEX("index"), MUTUAL_FUND("mutual_fund");

    private String type;

    @JsonValue
    public String getType() {
        return type;
    }

    SymbolType(String type) {
        this.type = type;
    }
}
