package com.longxingluoluo.questionnaire.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;

    @OneToMany(mappedBy = "teacher")
    @ToString.Exclude
    @JsonIgnoreProperties
    @JSONField(serialize = false)
    public List<TeacherEvaluation> teacherEvaluationList;

    @ManyToMany(mappedBy = "teacherList")
    @ToString.Exclude
    @JsonIgnoreProperties
    @JSONField(serialize = false)
    public List<Questionnaire> questionnaireList;
}
