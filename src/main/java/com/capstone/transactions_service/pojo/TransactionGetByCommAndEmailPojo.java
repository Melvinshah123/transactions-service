package com.capstone.transactions_service.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionGetByCommAndEmailPojo {
    int communityId;
    String email;
}
