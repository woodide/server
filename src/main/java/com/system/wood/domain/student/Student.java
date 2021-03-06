package com.system.wood.domain.student;

import com.sun.istack.NotNull;
import com.system.wood.domain.Role;
import com.system.wood.domain.User;
import com.system.wood.domain.container.Container;
import com.system.wood.domain.studtosubj.StudToSubj;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Student extends User {

    @Id @GeneratedValue
    @Column(name = "student_id")
    private Long id;

    @Column(unique = true, nullable = false)
    private String studentNumber;

    @Column
    @OneToMany(mappedBy = "student")
    private List<StudToSubj> studToSubjList = new ArrayList<>();

    @Column
    @OneToMany(mappedBy = "student")
    private List<Container> containerList = new ArrayList<>();

    @Builder
    public Student(String email, String password, String username, Role role,String studentNumber) {
        super(email, password, username, role);
        this.studentNumber = studentNumber;
    }

    public void setRole(Role role) {
        super.setRole(role);
    }

    public void setPassword(String password) {
        super.setPassword(password);
    }
}
