package com.system.wood.web.professor.dto;

import com.system.wood.domain.assigment.Assignment;
import com.system.wood.domain.subject.Subject;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.Lob;

@Getter
@Setter
public class AssignmentReqDto {

    @Column(nullable = false)
    private String assignmentName;

    @Lob
    private String description;

    private String language;

    private String languageVersion;

    private MultipartFile multipartFile;

    private MultipartFile testInput;

    private MultipartFile testOutput;

    private String subjectCode;

    public Assignment toEntity(String uploadUrl, String imageName, Subject subject) {
        return Assignment.builder()
                .assignmentName(assignmentName)
                .description(description)
                .language(language)
                .languageVersion(languageVersion)
                .uploadUrl(uploadUrl)
                .imageName(imageName)
                .subject(subject)
                .build();
    }
}
