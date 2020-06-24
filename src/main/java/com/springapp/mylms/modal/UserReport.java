package com.springapp.mylms.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserReport {
    private Long personId;
    private String fullname;
    private String courseName;
    private String role;
    private String endDate;
    private String enrollDate;
    private String startDate;
}
