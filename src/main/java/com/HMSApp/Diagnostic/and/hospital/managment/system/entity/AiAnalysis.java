package com.HMSApp.Diagnostic.and.hospital.managment.system.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Entity
@Table(name = "ai_analysis")
public class AiAnalysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('ai_analysis_analysis_id_seq')")
    @Column(name = "analysis_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "image_id", nullable = false)
    private Image image;

    @Column(name = "analysis_date", nullable = false)
    private LocalDate analysisDate;

    @Column(name = "confidence_level", precision = 5, scale = 2)
    private BigDecimal confidenceLevel;

    @Column(name = "pneumonia_detected", nullable = false)
    private Boolean pneumoniaDetected = false;

    @Column(name = "pneumonia_type", length = 50)
    private String pneumoniaType;

    @Column(name = "lesion_coordinates")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> lesionCoordinates;

    @Column(name = "segmentation_mask_url", length = Integer.MAX_VALUE)
    private String segmentationMaskUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public LocalDate getAnalysisDate() {
        return analysisDate;
    }

    public void setAnalysisDate(LocalDate analysisDate) {
        this.analysisDate = analysisDate;
    }

    public BigDecimal getConfidenceLevel() {
        return confidenceLevel;
    }

    public void setConfidenceLevel(BigDecimal confidenceLevel) {
        this.confidenceLevel = confidenceLevel;
    }

    public Boolean getPneumoniaDetected() {
        return pneumoniaDetected;
    }

    public void setPneumoniaDetected(Boolean pneumoniaDetected) {
        this.pneumoniaDetected = pneumoniaDetected;
    }

    public String getPneumoniaType() {
        return pneumoniaType;
    }

    public void setPneumoniaType(String pneumoniaType) {
        this.pneumoniaType = pneumoniaType;
    }

    public Map<String, Object> getLesionCoordinates() {
        return lesionCoordinates;
    }

    public void setLesionCoordinates(Map<String, Object> lesionCoordinates) {
        this.lesionCoordinates = lesionCoordinates;
    }

    public String getSegmentationMaskUrl() {
        return segmentationMaskUrl;
    }

    public void setSegmentationMaskUrl(String segmentationMaskUrl) {
        this.segmentationMaskUrl = segmentationMaskUrl;
    }

}