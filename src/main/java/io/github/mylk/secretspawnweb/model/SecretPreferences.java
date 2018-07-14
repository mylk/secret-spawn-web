package io.github.mylk.secretspawnweb.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class SecretPreferences {

    @NotNull
    @Min(8)
    private Integer length;

    @NotNull
    private String source;

    @NotNull
    private String format;

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
