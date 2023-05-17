package com.longxingluoluo.questionnaire.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    public Long id;
    public String name;
    @OneToMany
    public List<Grade> gradeList;
    @OneToMany
    public List<Professional> professionalList;
    @OneToMany
    public List<Curriculum> curriculumList;
    @OneToMany
    public List<Teacher> teacherList;
}
