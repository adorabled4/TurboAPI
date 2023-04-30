package com.dhx.apisdk.model.TO;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName t_lovelorn_sentence
 */
@Data
public class LovelornSentence implements Serializable {

    /**
     * 失恋句子
     */
    private String sentence;



    private static final long serialVersionUID = 1L;

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

}