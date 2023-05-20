package com.longxingluoluo.questionnaire.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * Project questionnaire
 * Created on 2023/5/15 下午 09:17
 *
 * @author 龙星洛洛
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "curriculum_evaluation")
public class CurriculumEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    public Curriculum curriculum;

    public int evaluation;

    @ManyToOne
    @ToString.Exclude
    @JsonIgnoreProperties
    @JSONField(serialize = false)
    public QuestionnaireAnswer questionnaireAnswer;
}
