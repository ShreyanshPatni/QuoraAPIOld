package com.upgrad.quora.service.entity;


import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "question")
@NamedQueries({
        @NamedQuery(name = "getAllQuestions", query = "select q from QuestionEntity q"),
        @NamedQuery(name = "getAllQuestionsByUser", query = "select q from QuestionEntity q where q.user.uuid=:userid"),
        @NamedQuery(name = "getQuestionById", query = "select q from QuestionEntity q where q.uuid=:uuid")
})
public class QuestionEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "uuid")
    @Size(max = 200)
    @NotNull
    private String uuid;

    @Column(name = "content")
    @Size(max = 500)
    @NotNull
    private String content;


    @Column(name = "date")
    @NotNull
    private ZonedDateTime date;

    @ManyToOne
    @OnDelete(action=OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    @NotNull
    private UserEntity user;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }


    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

}
