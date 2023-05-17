package com.longxingluoluo.questionnaire.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

/**
 * Project questionnaire
 * Created on 2023/5/15 下午 05:17
 *
 * @author 龙星洛洛
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "questionnaire_answer")
public class QuestionnaireAnswer {
    @Id
    public Long id;
    @OneToOne
    public Questionnaire questionnaire;
    @OneToOne
    public Professional professional;
    @OneToOne
    public Grade grade;
    @OneToMany
    public List<CurriculumEvaluation> curriculumEvaluationList;
    @OneToMany
    public List<TeacherEvaluation> teacherEvaluationList;
    public int selfEvaluation;
}
