package com.iit.spring.learning.model.response;

import com.iit.spring.learning.model.enums.SymbolType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Security {
    private String symbol;
    private String exchange;
    private SymbolType type;
    private String description;
}
