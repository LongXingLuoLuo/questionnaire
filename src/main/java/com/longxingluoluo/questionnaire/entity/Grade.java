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
 * Created on 2023/5/15 下午 05:18
 *
 * @author 龙星洛洛
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "grade")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;

    @ManyToMany(mappedBy = "gradeList")
    @ToString.Exclude
    @JsonIgnoreProperties
    @JSONField(serialize = false)
    public List<Questionnaire> questionnaireList;

    @OneToMany(mappedBy = "grade")
    @ToString.Exclude
    @JsonIgnoreProperties
    @JSONField(serialize = false)
    public List<QuestionnaireAnswer> questionnaireAnswerList;
}
