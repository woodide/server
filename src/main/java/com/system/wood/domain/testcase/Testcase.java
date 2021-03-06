package com.system.wood.domain.testcase;

import com.sun.istack.NotNull;
import com.system.wood.domain.BaseTimeEnity;
import com.system.wood.domain.assigment.Assignment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Testcase extends BaseTimeEnity {

    @Id
    @Column(name = "testcase_id")
    @GeneratedValue
    private Long id;

    @NotNull
    private String inputUrl;

    @NotNull
    private String outputUrl;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;

    public Testcase(String inputUrl, String outputUrl, Assignment assignment) {
        this.inputUrl = inputUrl;
        this.outputUrl = outputUrl;
        this.assignment = assignment;
    }
}
