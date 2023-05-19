package com.longxingluoluo.questionnaire.entity;

import lombok.*;

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
    @ManyToMany
    public List<Grade> gradeList;
    @ManyToMany
    public List<Professional> professionalList;
    @ManyToMany
    public List<Curriculum> curriculumList;
    @ManyToMany
    public List<Teacher> teacherList;
}
