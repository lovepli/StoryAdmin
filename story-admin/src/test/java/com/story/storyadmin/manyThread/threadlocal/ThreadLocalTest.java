package com.story.storyadmin.manyThread.threadlocal;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * Description:
 *
 * @author yuange
 * @version 1.0
 * @date: 2019/7/2 11:38
 * @since JDK 1.8
 */
public class ThreadLocalTest {

	private static ThreadLocal<Student> threadLocal = new ThreadLocal<>();

	@Test
	public void test() {
		Student student1 = new Student(11, "lvshen");
		Student student2 = new Student(22, "niumo");

		threadLocal.set(student1);
		threadLocal.set(student2);

	}

	@Test
	public void test1() {
		printAllSlot(16);
	}

	private static void printAllSlot(int len) {
		System.out.println("************* len = " + len + " ***************");
		for (int i = 1; i <= 64; i++) {
			ThreadLocal<String> local = new ThreadLocal<>();
			int slot = getSlot(local, len);
			System.out.print(slot + " ");
			if (i % len == 0) {
				System.out.println();
			}
		}

	}

	static int getSlot(ThreadLocal<?> t, int len) {
		int hashCode = getHashCode(t);
		return hashCode & (len - 1);
	}

	static int getHashCode(ThreadLocal<?> t) {
		Field field;
		try {
			field = t.getClass().getDeclaredField("threadLocalHashCode");
			field.setAccessible(true);
			return (int) field.get(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}

@AllArgsConstructor
class Student {
    private Integer score;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date date;

    public Student() {
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Student{" +
                "score=" + score +
                ", name='" + name + '\'' +
                ", date=" + date +
                '}';
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student(Integer score, String name) {
        this.score = score;
        this.name = name;
    }

    public Student(String name) {
        this.name = name;
    }
}

