package com.story.storyadmin.service.oasys;

import com.story.storyadmin.domain.entity.oasys.Attendance;

import java.util.List;
import java.util.Map;

public interface AttendanceService {
    void setAttendanceTime(String begin, String end);

    Map<String, String> getAttendanceTime();

    List<String> getAttendances(Long userId);

    Attendance getAttendance(Long userId);

    void signIn(Long userId);

    void signOut(Long userId);
}
