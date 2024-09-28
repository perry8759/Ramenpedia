package com.ramenpedia.controller.websocket.dto;

import com.ramenpedia.enumerate.StoreBusinessStatus;
import com.ramenpedia.service.dto.StoreInfo;
import com.ramenpedia.service.dto.StoreInfoDetail;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BusinessHoursMessage {
    private StoreBusinessStatus status;
}
