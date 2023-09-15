package bitcamp.myapp.vo;

import java.io.Serializable;
import java.util.Objects;

public class NotiType implements Serializable {

    private static final long serialVersionUID = 1L;

    private int no;
    private String name;

    @Override
    public String toString() {
        return "NotiType{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NotiType notiType)) return false;
        return no == notiType.no && Objects.equals(name, notiType.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(no, name);
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
