package com.example.attendanceloggingservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "attendance_logs")
public class AttendanceLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id", nullable = false)
    private Long logId;

    @Column(name = "timestamp", nullable = false)
    private Timestamp instant= Timestamp.from(Instant.now());

    @ManyToOne
    @JoinColumn(name="card_id", nullable=false)
    private Student student;

    @ManyToOne
    @JoinColumn(name="lecture_id", nullable=false)
    private Lecture lecture;   // decide which lecture the received log is for, based on classRoom + startTime + endTime

}
