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
@Entity(name = "professional")
public class Professional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;

    @ManyToMany(mappedBy = "professionalList", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @JsonIgnoreProperties
    @JSONField(serialize = false)
    public List<Questionnaire> questionnaireList;

    @OneToMany(mappedBy = "professional", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @JsonIgnoreProperties
    @JSONField(serialize = false)
    public List<QuestionnaireAnswer> questionnaireAnswerList;
}
