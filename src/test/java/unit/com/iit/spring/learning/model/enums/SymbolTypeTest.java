package com.iit.spring.learning.model.enums;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class SymbolTypeTest {

    @Test
    public void testSymbolType() {
        assertEquals(SymbolType.OPTION.getType(), "option");
        assertEquals(SymbolType.STOCK.getType(), "stock");
        assertEquals(SymbolType.ETF.getType(), "etf");
        assertEquals(SymbolType.INDEX.getType(), "index");
        assertEquals(SymbolType.MUTUAL_FUND.getType(), "mutual_fund");
    }
}
