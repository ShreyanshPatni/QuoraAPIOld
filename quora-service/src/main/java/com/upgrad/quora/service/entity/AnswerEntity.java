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
@Table(name = "answer")
@NamedQueries({
        @NamedQuery(name = "getAnswerById", query = "select a from AnswerEntity a where a.uuid = :uuid"),
        @NamedQuery(name = "getAnswerByQuestionId", query = "select a from AnswerEntity a where a.question.uuid = :questionId")
})
public class AnswerEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "uuid")
    @Size(max = 200)
    @NotNull
    private String uuid;

    @Column(name = "ans")
    @Size(max = 255)
    @NotNull
    private String ans;

    @Column(name = "date")
    @NotNull
    private ZonedDateTime date;

    @ManyToOne
    @OnDelete(action=OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    @NotNull
    private UserEntity user;

    @ManyToOne
    @OnDelete(action=OnDeleteAction.CASCADE)
    @JoinColumn(name = "question_id")
    @NotNull
    private QuestionEntity question;


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


    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
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


    public QuestionEntity getQuestion() {
        return question;
    }

    public void setQuestion(QuestionEntity question) {
        this.question = question;
    }

}
