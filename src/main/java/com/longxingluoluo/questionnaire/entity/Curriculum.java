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
 * Created on 2023/5/13 下午 09:13
 *
 * @author 龙星洛洛
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "curriculum")
public class Curriculum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;

    @OneToMany(mappedBy = "curriculum")
    @ToString.Exclude
    @JsonIgnoreProperties
    @JSONField(serialize = false)
    public List<CurriculumEvaluation> curriculumEvaluationList;

    @ManyToMany(mappedBy = "curriculumList")
    @ToString.Exclude
    @JsonIgnoreProperties
    @JSONField(serialize = false)
    public List<Questionnaire> questionnaireList;
}