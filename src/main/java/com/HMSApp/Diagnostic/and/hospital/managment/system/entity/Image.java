package com.HMSApp.Diagnostic.and.hospital.managment.system.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('images_image_id_seq')")
    @Column(name = "image_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "exam_id", nullable = false)
    private Examination exam;

    @Column(name = "file_path", nullable = false, length = Integer.MAX_VALUE)
    private String filePath;

    @Column(name = "resolution", length = 50)
    private String resolution;
    @ColumnDefault("false")
    @Column(name = "has_artifacts")
    private Boolean hasArtifacts;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Examination getExam() {
        return exam;
    }

    public void setExam(Examination exam) {
        this.exam = exam;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public Boolean getHasArtifacts() {
        return hasArtifacts;
    }

    public void setHasArtifacts(Boolean hasArtifacts) {
        this.hasArtifacts = hasArtifacts;
    }

/*
 TODO [Reverse Engineering] create field to map the 'image_type' column
 Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = "image_type", columnDefinition = "image_type_enum not null")
    private Object imageType;
*/
/*
 TODO [Reverse Engineering] create field to map the 'lung_side' column
 Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @Column(name = "lung_side", columnDefinition = "lung_side_enum not null")
    private Object lungSide;
*/
}