package br.com.b2w.bit.planets.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

public class ErrorMessage implements Serializable {

    private static final long serialVersionUID = -8345269920851498768L;

    private Integer status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String title;

    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String stackTracer;

    public ErrorMessage(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public ErrorMessage(Integer status, String title, String message) {
        this.status = status;
        this.title = title;
        this.message = message;
    }

    public ErrorMessage(Integer status, String title, String message, String stackTracer) {
        this.status = status;
        this.title = title;
        this.message = message;
        this.stackTracer = stackTracer;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStackTracer() {
        return stackTracer;
    }

    public void setStackTracer(String stackTracer) {
        this.stackTracer = stackTracer;
    }

    @Override
    public String toString() {
        return "ErrorMessage [" + status + "] [" + title + "] ['" + message + "]";
    }
}
