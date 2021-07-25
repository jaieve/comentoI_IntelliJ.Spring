package com.example.comento.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;



@EqualsAndHashCode(callSuper = false)
@Getter @Setter
@NoArgsConstructor //Builder와 함께 사용하려면 AllArgsConstructor도 함께 사용해야 함.
@Entity //Entity 클래스에서는 절대 Setter 메소드를 만들지 않는다.
@Table(name="posts")
public class Post extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String writer;

//user 테이블과 join되는 외래키
//    @ManyToOne
//    @JoinColumn(name='user_id')
//    private User author;
//    //comment 테이블과 join괴는 외래키
//    @OneToMany(mappedBy="post", cascade=CascadeType.ALL, fetch=????)
//    private final List<Comment> comments = new ArrayList<>();

    @Column(length=128, nullable = false)
    private String title;

    //@Column에서 nullable=false를 제거해도, @NotNull이 붙어있으면 DDL 생성 시 not null로 생성된다
    @Column(length=512, nullable = false)
    private String content;
//    @Convert(converter = TypeJpaConverter.class)
//    private Type type;
    @Column(nullable = true, insertable = false)
    private String type = "community";

    @Column(insertable=false)
    @ColumnDefault("'0'")
    private Long views;

    @Column(insertable = false)
    @ColumnDefault("'0'")
    private Long likes;
    @Builder
    public Post(Long id, String writer, String title, String content, String type, Long views, Long likes) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.type = type;
        this.views = views;
        this.likes = likes;
    }
}


