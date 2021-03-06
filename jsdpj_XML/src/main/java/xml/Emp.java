package xml;

/**
 * 员工类
 * 该类每个实力用于员工信息
 */
public class Emp {
    private int id;
    private String name;
    private int age;
    private String gender;
    private int with;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getWith() {
        return with;
    }

    public void setWith(int with) {
        this.with = with;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", with=" + with +
                '}';
    }

    public Emp(int id, String name, int age, String gender, int with) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.with = with;
    }
}
