package com.longxingluoluo.questionnaire.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Project questionnaire
 * Created on 2023/5/13 下午 09:14
 *
 * @author 龙星洛洛
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "teacher")
public class Teacher {
    @Id
    public Long id;
    public String name;

    public Teacher(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
