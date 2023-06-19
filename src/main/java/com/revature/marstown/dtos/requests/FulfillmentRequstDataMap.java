package com.revature.marstown.dtos.requests;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FulfillmentRequstDataMap {
    private String id;
    private String object;
    private Map<String, String> metadata;
}
