package com.story.storyadmin.mapper.oasys;

import com.story.storyadmin.domain.entity.oasys.Attendance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface AttendanceMapper {
    void updateAttendanceTime(@Param("begin") String begin, @Param("end") String end);

    Map<String, String> selectAttendanceTime();

    List<String> selectAttendances(@Param("userId") Long userId);

    Attendance selectAttendance(@Param("userId") Long userId, @Param("date") String date);

    void signIn(@Param("userId") Long userId, @Param("date") LocalDate date, @Param("time") LocalTime time);

    void signOut(@Param("userId") Long userId, @Param("date") LocalDate date, @Param("time") LocalTime time);
}
