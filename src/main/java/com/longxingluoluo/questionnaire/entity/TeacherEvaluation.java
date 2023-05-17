package com.longxingluoluo.questionnaire.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Project questionnaire
 * Created on 2023/5/15 下午 09:20
 *
 * @author 龙星洛洛
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "teacher_evaluation")
public class TeacherEvaluation {
    @Id
    public Long id;
    @OneToOne
    public Teacher teacher;
    public int evaluation;
}
