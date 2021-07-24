package com.example.comento.domain;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;


@Builder
@Data  // getter + setter + toString + RequiredArgsConstructor
@Entity
@Table(name="posts")
public class Post extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String writer;

//user 테이블과 join되는 외래키
//    @ManyToOne
//    @JoinColumn(name='user_id')
//    private User author;
//    //comment 테이블과 join괴는 외래키
//    @OneToMany(mappedBy="post", cascade=CascadeType.ALL, fetch=????)
//    private final List<Comment> comments = new ArrayList<>();

    @Column(length=128, nullable = true)
    @NotNull
    private String title;

    @Column(length=512)
    @NotNull
    private String content;

//    @Column(columnDefinition = "varchar(225) default 'community'")

//    @Convert(converter = TypeJpaConverter.class)
//    private Type type;
    @Column(nullable = false)
    @NotNull
    private String type;

    @Column
    private Long views;

    @Column
    private Long likes;

}
