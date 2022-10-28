public class CandidateRegisteration {
    String name;
    byte age;
    String gender;
    int GraduatedYear;
    String degree;
    String role;
    String email;
    String password;
    String status;

    public CandidateRegisteration(String name, byte age, String gender, int graduatedYear, String degree, String role, String email, String password, String status) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        GraduatedYear = graduatedYear;
        this.degree = degree;
        this.role = role;
        this.email = email;
        this.password = password;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getGraduatedYear() {
        return GraduatedYear;
    }

    public void setGraduatedYear(int graduatedYear) {
        GraduatedYear = graduatedYear;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
