package com.longxingluoluo.questionnaire.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Entity(name = "questionnaire")
public class Questionnaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    public List<Grade> gradeList;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    public List<Professional> professionalList;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    public List<Curriculum> curriculumList;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    public List<Teacher> teacherList;

    @OneToMany(mappedBy = "questionnaire", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnoreProperties
    @JSONField(serialize = false)
    public List<QuestionnaireAnswer> questionnaireAnswerList;
}
