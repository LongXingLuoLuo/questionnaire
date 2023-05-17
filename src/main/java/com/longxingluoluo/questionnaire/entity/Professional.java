package com.longxingluoluo.questionnaire.entity;

import lombok.*;

import javax.persistence.*;

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
}
