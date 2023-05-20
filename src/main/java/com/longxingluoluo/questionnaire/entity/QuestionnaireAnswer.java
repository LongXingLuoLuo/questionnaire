package com.longxingluoluo.questionnaire.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    public Questionnaire questionnaire;

    @ManyToOne
    public Professional professional;

    @ManyToOne
    public Grade grade;

    @OneToMany(mappedBy = "questionnaireAnswer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    public List<CurriculumEvaluation> curriculumEvaluationList;

    @OneToMany(mappedBy = "questionnaireAnswer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    public List<TeacherEvaluation> teacherEvaluationList;

    public int selfEvaluation;
}
