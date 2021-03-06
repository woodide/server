package com.system.wood.web.professor.service;

import com.system.wood.domain.professor.Professor;
import com.system.wood.domain.professor.ProfessorRepository;
import com.system.wood.domain.student.Student;
import com.system.wood.domain.student.StudentRepository;
import com.system.wood.domain.studtosubj.StudToSubj;
import com.system.wood.domain.studtosubj.StudToSubjRepository;
import com.system.wood.domain.subject.Subject;
import com.system.wood.domain.subject.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProfessorService {

    private final SubjectRepository subjectRepository;
    private final ProfessorRepository professorRepository;
    private final StudentRepository studentRepository;
    private final StudToSubjRepository studToSubjRepository;

    public Subject findById(Long id) {
        return subjectRepository.findById(id).orElseThrow(
                () -> {
                    throw new EntityNotFoundException(String.format("id가 %d인 Subject가 존재하지 않습니다.", id));
                }
        );
    }

    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    @Transactional
    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Transactional
    public void saveStudentList(List<String> studentNumberList, Subject subject) {
        List<Student> studentList = studentRepository.findByStudentNumberIn(studentNumberList);
        studToSubjRepository.deleteBySubject(subject); // 기존에 있던 멤버들 다 삭제 후 재갱신
        studentList.stream()
                .map(student -> new StudToSubj(student, subject))
                .forEach(studToSubjRepository::save);
    }

}
