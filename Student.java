package admissionform;
    import java.io.Serializable;

public class Student implements Serializable {
    private String studentName;
    private String fatherName;
    private String motherName;
    private String email;
    private String contactNumber;
    private String dob;
    private int age;
    private String gender;
    private String religion;
    private int sscYear;
    private double sscGpa;
    private int hscYear;
    private double hscGpa;
    private String department;
    private String presentAddress;
    private String permanentAddress;

    // Constructor
    public Student(String studentName, String fatherName, String motherName, String email, String contactNumber, 
                   String dob, int age, String gender, String religion, int sscYear, double sscGpa,
                   int hscYear, double hscGpa, String department, String presentAddress, String permanentAddress) {
        this.studentName = studentName;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.email = email;
        this.contactNumber = contactNumber;
        this.dob = dob;
        this.age = age;
        this.gender = gender;
        this.religion = religion;
        this.sscYear = sscYear;
        this.sscGpa = sscGpa;
        this.hscYear = hscYear;
        this.hscGpa = hscGpa;
        this.department = department;
        this.presentAddress = presentAddress;
        this.permanentAddress = permanentAddress;
    }

    // Getters for each field
    public String getStudentName() { return studentName; }
    public String getFatherName() { return fatherName; }
    public String getMotherName() { return motherName; }
    public String getEmail() { return email; }
    public String getContactNumber() { return contactNumber; }
    public String getDob() { return dob; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getReligion() { return religion; }
    public int getSscYear() { return sscYear; }
    public double getSscGpa() { return sscGpa; }
    public int getHscYear() { return hscYear; }
    public double getHscGpa() { return hscGpa; }
    public String getDepartment() { return department; }
    public String getPresentAddress() { return presentAddress; }
    public String getPermanentAddress() { return permanentAddress; }
}