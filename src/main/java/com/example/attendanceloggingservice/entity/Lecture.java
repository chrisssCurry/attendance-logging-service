package com.example.attendanceloggingservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lectures")
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecture_id", nullable = false)
    private Long lectureId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "classroom")
    private String classRoom;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime; // decide which class the received log is for, based on classRoom + startTime + endTime

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Column(name = "term", nullable = false)
    private Byte term;

    @OneToMany(mappedBy = "lecture")
    private List<AttendanceLog> attendanceLogs;
}
