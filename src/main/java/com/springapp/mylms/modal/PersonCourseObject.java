package com.springapp.mylms.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by bezdj on 25/12/2017.
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonCourseObject {
    private Long id;
    private Long categoryId;
    private String courseImage;
    private String courseName;
    private String description;
    private String idNumber;
    private String enrollDate;
    private String startDate;
    private String endDate;
}
