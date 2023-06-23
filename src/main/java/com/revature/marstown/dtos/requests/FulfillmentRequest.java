package com.revature.marstown.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FulfillmentRequest {
    private String id;
    private String object;
    private String api_version;
    private Long created;
    private boolean livemode;
    private Integer pending_webhooks;
    private String type;
    private FullfillmentRequestData data;
}
