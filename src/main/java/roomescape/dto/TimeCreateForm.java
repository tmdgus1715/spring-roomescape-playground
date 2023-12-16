package roomescape.dto;

import roomescape.domain.Time;

import java.time.LocalTime;

public class TimeCreateForm {

    private String time;

    public Time toEntity() {
        return new Time(null, this.time);
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
