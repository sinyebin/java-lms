package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Students {
    private final List<NsUser> students;

    public Students() {
        this(new ArrayList<>());
    }

    public Students(List<NsUser> students) {
        this.students = students;
    }

    public void addStudent(NsUser student) {
        students.add(student);
    }
}
