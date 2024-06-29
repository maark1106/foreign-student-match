package com.example.foreignstudentmatch.domain;

import com.example.foreignstudentmatch.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "board")
public class Board extends BaseTimeEntity {

    @Id @GeneratedValue
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    public Student student;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BatchSize(size = 10)
    private List<Comment> comments; // 댓글

    @Column(name = "comment_count")
    private Integer commentCount;     // 댓글 수

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BatchSize(size = 10)
    private List<Like> likes; // 좋아요

    @Column(name = "like_count")
    private Integer likeCount;     // 좋아요 수

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BatchSize(size = 10)
    private List<UploadImage> uploadImages = new ArrayList<>();

    public void commentChange(Integer commentCnt) {
        this.commentCount = commentCnt;
    }

    public void likeChange(Integer likeCount) {
        this.likeCount = likeCount;
    }

    //== 수정 Dirty Checking ==//
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    //== Student & Board 연관관계 편의 메소드 ==//
    private void setMappingStudent(Student student) {
        this.student = student;
        student.getBoards().add(this);
    }

    @Builder
    public Board(Long id, String title, String content, Student student, List<UploadImage> uploadImages) {
        this.id = id;
        this.title = title;
        this.content = content;
        setMappingStudent(student);
        this.uploadImages = uploadImages;
    }
}
