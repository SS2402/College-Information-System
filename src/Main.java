import java.util.*;

class ExistingCourseCodeException extends Exception
{
    ExistingCourseCodeException(String str){super(str);}
}
class ExistingCourseNameException extends Exception
{
    ExistingCourseNameException(String str){super(str);}
}
class CourseDurationException extends Exception
{
    CourseDurationException(String str){super(str);}
}
class MinCreditsException extends Exception {
    MinCreditsException(String str){super(str);}
}
class MaxCreditsException extends Exception
{
    MaxCreditsException(String str){super(str);}
}
class HSPercentageException extends Exception
{
    HSPercentageException(String str){super(str);}
}
class UGCGPAException extends Exception
{
    UGCGPAException(String str){super(str);}
}
class InvalidSalaryException extends Exception
{
    InvalidSalaryException(String str){super(str);}
}
class ExistingEmployeeID extends Exception
{
    ExistingEmployeeID(String str){super(str);}
}

class College
{
    static String name,address;
    static Scanner scan = new Scanner(System.in);

    static void DisplayCollegeInfo()
    {
        System.out.printf("%-50s %-50s","COLLEGE NAME","COLLEGE ADDRESS");
        System.out.println();
        System.out.printf("%-50s %-50s\n",College.name,College.address);
    }
}

class Department extends College
{
    int deptID;
    static int DEPTCount = 0;
    String deptName;
    static Department []DEPT_DB = new Department[25];

    Department()
    {
        try
        {
            this.deptID = scan.nextInt();
            scan.nextLine();
            this.deptName = scan.nextLine();
            DEPT_DB[DEPTCount++] = this;
        }
        catch(ArrayIndexOutOfBoundsException E)
        {
            System.out.println("DEPARTMENT DATABASE IS FULL " + E);
        }
    }

    void DeptOutput()
    {
        try
        {
            System.out.printf("%-8d %-10s",this.deptID,this.deptName);
        }
        catch(NullPointerException ignored){}
    }
}

class Courses extends College {
    int minCredits, maxCredits, courseDuration;
    String courseCode, courseName;
    Department Dept;

    static void CoursesMenu()
    {
        System.out.println("----COURSES----");
        System.out.println("1. ACCESS INTO UG COURSE SECTION");
        System.out.println("2. ACCESS INTO PG COURSE SECTION");
        System.out.println("3. ACCESS INTO PHD COURSE SECTION");
        System.out.println("4. DISPLAY THE ENTIRE COURSES DATABASE");
        System.out.println("5. EXIT THE COURSES DATABASE");
    }

    static int choice;

    static void CoursesDB()
    {
        do
        {
            Courses.CoursesMenu();
            System.out.println("ENTER YOUR CHOICE : ");
            choice = scan.nextInt();
            switch(choice)
            {
                case 1 :
                    UG.UGCourse();
                    break;
                case 2 :
                    PG.PGCourse();
                    break;
                case 3:
                    PHD.PHDCourse();
                    break;
                case 4 :
                    UG.DisplayAllInfo();
                    System.out.println();
                    PG.DisplayAllInfo();
                    System.out.println();
                    PHD.DisplayAllInfo();
                    break;
                case 5 :
                    System.out.println("EXITING THE COURSES DATABASE...");
                    break;
                default :
                    System.out.println("ENTER VALID CHOICE...");
            }
        }
        while(choice != 5);
    }

    void GetCourseCode(String CourseCode) throws ExistingCourseCodeException
    {
        try
        {
            for (UG UGCourses : UG.UG_DB)
                if (UGCourses.courseCode.equals(CourseCode))
                    throw new ExistingCourseCodeException("Existing COURSE CODE");
            for (PG PGCourses : PG.PG_DB)
                if (PGCourses.courseCode.equals(CourseCode))
                    throw new ExistingCourseCodeException("Existing COURSE CODE");
        }
        catch(NullPointerException ignored){}
        this.courseCode=CourseCode;
    }

    void GetCourseName(String CourseName) throws ExistingCourseNameException
    {
        try
        {
            for (UG UGCourses : UG.UG_DB)
                if (UGCourses.courseName.equals(CourseName))
                    throw new ExistingCourseNameException("Existing COURSE CODE");
            for (PG PGCourses : PG.PG_DB)
                if (PGCourses.courseName.equals(CourseName))
                    throw new ExistingCourseNameException("Existing COURSE NAME");
        }
        catch(NullPointerException ignored){}
        this.courseName=CourseName;
    }

   void GetCourseDuration(int CourseDuration) throws CourseDurationException
   {
       try
       {
           if (CourseDuration < 1)
               throw new CourseDurationException("Invalid COURSE DURATION");
        }
       catch(NullPointerException ignored){}
       this.courseDuration=CourseDuration;
    }

    void GetMinCredits(int MinCredits) throws MinCreditsException
    {
        try
        {
            if (MinCredits < 16 | MinCredits > 27)
                throw new MinCreditsException("Minimum CREDITS : 16 Expected");
        }
        catch(NullPointerException ignored){}
        this.minCredits=MinCredits;
    }

    void GetMaxCredits(int MaxCredits) throws MaxCreditsException
    {
        try
        {
            if (MaxCredits > 27 | MaxCredits < 16)
                throw new MaxCreditsException("Maximum CREDITS : 27");
        }
        catch(NullPointerException ignored){}
        this.maxCredits=MaxCredits;
    }

    void GetInput()
    {
                while(true)
                {
                    try
                    {
                        System.out.println("Enter the COURSE CODE : ");
                        scan.nextLine();
                        GetCourseCode(scan.nextLine());
                        break;
                    }
                    catch(ExistingCourseCodeException | InputMismatchException E)
                    {
                        System.out.println(E);
                    }
                }
                while(true)
                {
                    try
                    {
                        System.out.println("Enter the COURSE NAME : ");
                        GetCourseName(scan.nextLine());
                        break;
                    }
                    catch(ExistingCourseNameException | InputMismatchException E)
                    {
                        System.out.println(E);
                    }
                }
                while(true)
                {
                    try
                    {
                        System.out.println("Enter the DEPT ID and DEPT NAME : ");
                        this.Dept = new Department();
                        break;
                    }
                    catch(InputMismatchException E)
                    {
                        System.out.println(E);
                    }
                }
                while(true)
                {
                    try
                    {
                        System.out.println("Enter the COURSE DURATION : ");
                        GetCourseDuration(scan.nextInt());
                        scan.nextLine();
                        break;
                    }
                    catch(CourseDurationException | InputMismatchException E)
                    {
                        System.out.println(E);
                    }
                }
                while(true)
                {
                    try
                    {
                        System.out.println("Enter the MINIMUM CREDITS : ");
                        GetMinCredits(scan.nextInt());
                        break;
                    }
                    catch(MinCreditsException | InputMismatchException E)
                    {
                        System.out.println(E);
                    }
                }
                while(true)
                {
                    try
                    {
                        System.out.println("Enter the MAXIMUM CREDITS : ");
                        GetMaxCredits(scan.nextInt());
                        break;
                    }
                    catch(MaxCreditsException | InputMismatchException E)
                    {
                        System.out.println(E);
                    }
                }
    }

    void PrintOutput()
    {
        try
        {
            System.out.printf("%-12s %-80s ",this.courseCode,this.courseName);
            this.Dept.DeptOutput();
            System.out.printf(" %-15d %-15d %-15d",this.courseDuration,this.minCredits,this.maxCredits);
        }
        catch(NullPointerException ignored){}
    }
}

class UG extends Courses
{
    int HSPercentage;
    static int UGCount = 0;
    static UG []UG_DB = new UG[5];

    void GetHSPercentage(int HSPercentage) throws HSPercentageException
    {
        try
        {
            if(HSPercentage < 0 | HSPercentage > 100)
                throw new HSPercentageException("Invalid HS Percentage");
        }
        catch(NullPointerException ignored){}
        this.HSPercentage = HSPercentage;
    }

    static void UGMenu()
    {
        System.out.print("----UG COURSES----\n");
        System.out.println("1. ENTER A NEW RECORD INTO THE UG DATABASE");
        System.out.println("2. SHOW THE DETAILS OF THE ENTERED COURSE CODE");
        System.out.println("3. DISPLAY THE ENTIRE UG DATABASE");
        System.out.println("4. EXIT THE UG COURSE SECTION");
    }

    static int choice;

    static void UGCourse()
    {
        do
        {
            UG.UGMenu();
            System.out.println("ENTER YOUR CHOICE : ");
            choice = scan.nextInt();
            switch(choice)
            {
                case 1 :
                    new UG();
                    break;
                case 2 :
                    String cCODE;
                    System.out.println("ENTER THE COURSE CODE : ");
                    scan.nextLine();
                    cCODE = scan.nextLine();
                    UG.DisplaySpecificInfo(cCODE);
                    break;
                case 3:
                    UG.DisplayAllInfo();
                    break;
                case 4 :
                    System.out.println("EXITING UG COURSE SECTION...");
                    break;
                default :
                    System.out.println("ENTER VALID CHOICE...");
            }
        }
        while(choice != 4);
    }

    UG()
    {
        try
        {
            this.GetInput();
            while(true)
            {
                try
                {
                    System.out.println("Enter your HIGH SCHOOL Percentage : ");
                    GetHSPercentage(scan.nextInt());
                    break;
                }
                catch(HSPercentageException | InputMismatchException E)
                {
                    System.out.println(E);
                }
            }
            UG_DB[UGCount++] = this;
        }
        catch(ArrayIndexOutOfBoundsException E)
        {
            System.out.println("UG DATABASE IS FULL");
        }
    }

    void Display()
    {
        try
        {
            this.PrintOutput();
            System.out.printf(" %-10d",this.HSPercentage);
        }
        catch(NullPointerException ignored){}
    }

    static void DisplaySpecificInfo(String cCODE)
    {
        try
        {
            for (UG UGCourses : UG_DB)
            {
                if(UGCourses.courseCode.equals(cCODE))
                {
                    System.out.println("----UG DATABASE----");
                    System.out.printf("%-12s %-80s %-8s %-10s %-15s %-15s %-15s %-10s\n","COURSE CODE","COURSE NAME","DEPT ID","DEPT NAME","COURSE DURATION","MINIMUM CREDITS","MAXIMUM CREDITS","HS PERCENTAGE");
                    UGCourses.Display();
                    System.out.println();
                    break;
                }
            }
        }
        catch(NullPointerException ignored)
        {
            System.out.println("UG COURSE CODE NOT FOUND...");
        }
    }

    static void DisplayAllInfo()
    {
        try
        {
            System.out.println("----UG DATABASE----");
            System.out.printf("%-12s %-80s %-8s %-10s %-15s %-15s %-15s %-10s\n","COURSE CODE","COURSE NAME","DEPT ID","DEPT NAME","COURSE DURATION","MINIMUM CREDITS","MAXIMUM CREDITS","HS PERCENTAGE");
            for (UG UGCourses : UG_DB)
            {
                UGCourses.Display();
                System.out.println();
            }
        }
        catch(NullPointerException ignored){}
    }
}

class PG extends Courses
{
    double UGCGPA;
    static int PGCount = 0;
    static PG[] PG_DB = new PG[5];

    static void PGMenu()
    {
        System.out.print("----PG COURSES----\n");
        System.out.println("1. ENTER A NEW RECORD INTO THE PG DATABASE");
        System.out.println("2. SHOW THE DETAILS OF THE ENTERED COURSE CODE");
        System.out.println("3. DISPLAY THE ENTIRE PG DATABASE");
        System.out.println("4. EXIT THE PG COURSE SECTION...");
    }

    static int choice;

    static void PGCourse()
    {
        do
        {
            PG.PGMenu();
            System.out.println("ENTER YOUR CHOICE : ");
            choice = scan.nextInt();
            switch(choice)
            {
                case 1 :
                    new PG();
                    break;
                case 2 :
                    String cCODE;
                    System.out.println("ENTER THE COURSE CODE : ");
                    scan.nextLine();
                    cCODE = scan.nextLine();
                    PG.DisplaySpecificInfo(cCODE);
                    break;
                case 3:
                    PG.DisplayAllInfo();
                    break;
                case 4 :
                    System.out.println("EXITING PG COURSE SECTION...");
                    break;
                default :
                    System.out.println("ENTER VALID CHOICE...");
            }
        }
        while(choice != 4);
    }

    void GetUGCGPA(double UGCGPA) throws UGCGPAException
    {
        try
        {
            for(PG PGCourse : PG.PG_DB)
                if(PGCourse.UGCGPA < 0 | PGCourse.UGCGPA > 10)
                    throw new UGCGPAException("Invalid UG CGPA");
        }
        catch(NullPointerException ignored){}
        this.UGCGPA = UGCGPA;
    }

    PG()
    {
        try
        {
            this.GetInput();
            while(true)
            {
                try
                {
                    System.out.println("Enter your UG CGPA : ");
                    GetUGCGPA(scan.nextDouble());
                    break;
                }
                catch(UGCGPAException | InputMismatchException E)
                {
                    System.out.println(E);
                }
            }
            PG_DB[PGCount++] = this;
        }
        catch(ArrayIndexOutOfBoundsException E)
        {
            System.out.println("PG DATABASE IS FULL");
        }
    }

    void Display()
    {
        try
        {
            this.PrintOutput();
            System.out.printf(" %-10.2f",this.UGCGPA);
        }
        catch(NullPointerException ignored){}
    }

    static void DisplaySpecificInfo(String courseCode)
    {
        try
        {
            for (PG PGCourses : PG_DB)
            {
                if(PGCourses.courseCode.equals(courseCode))
                {
                    System.out.println("----PG DATABASE----");
                    System.out.printf("%-12s %-80s %-8s %-10s %-15s %-15s %-15s %-10s\n","COURSE CODE","COURSE NAME","DEPT ID","DEPT NAME","COURSE DURATION","MINIMUM CREDITS","MAXIMUM CREDITS","UG CGPA");
                    PGCourses.Display();
                    System.out.println();
                    break;
                }
            }
        }
        catch(NullPointerException ignored)
        {
            System.out.println("PG COURSE CODE NOT FOUND...");
        }
    }

    static void DisplayAllInfo()
    {
        try
        {
            System.out.println("----PG DATABASE----");
            System.out.printf("%-12s %-80s %-8s %-10s %-15s %-15s %-15s %-10s\n","COURSE CODE","COURSE NAME","DEPT ID","DEPT NAME","COURSE DURATION","MINIMUM CREDITS","MAXIMUM CREDITS","UG CGPA");
            for (PG PGCourses : PG_DB)
            {
                PGCourses.Display();
                System.out.println();
            }
        }
        catch(NullPointerException ignored){}
    }
}

class PHD extends Courses
{
    static int PHDCount = 0;
    String researchInterest;
    static PHD[] PHD_DB = new PHD[5];

    static void PHDMenu()
    {
        System.out.print("----PHD COURSES----\n");
        System.out.println("1. ENTER A NEW RECORD INTO THE PHD DATABASE");
        System.out.println("2. SHOW THE DETAILS OF THE ENTERED COURSE CODE");
        System.out.println("3. DISPLAY THE ENTIRE PHD DATABASE");
        System.out.println("4. EXIT THE PHD COURSE SECTION");
    }

    static int choice;

    static void PHDCourse()
    {
        do
        {
            PHD.PHDMenu();
            System.out.println("ENTER YOUR CHOICE : ");
            choice = scan.nextInt();
            switch(choice)
            {
                case 1 :
                    new PHD();
                    break;
                case 2 :
                    String cCODE;
                    System.out.println("ENTER THE COURSE CODE : ");
                    scan.nextLine();
                    cCODE = scan.nextLine();
                    PHD.DisplaySpecificInfo(cCODE);
                    break;
                case 3:
                    PHD.DisplayAllInfo();
                    break;
                case 4 :
                    System.out.println("EXITING PHD COURSE SECTION...");
                    break;
                default :
                    System.out.println("ENTER VALID CHOICE...");
            }
        }
        while(choice != 4);
    }

    PHD(){
        try
        {
            this.GetInput();
            while(true)
            {
                try
                {
                    System.out.println("Enter your RESEARCH INTEREST : ");
                    scan.nextLine();
                    this.researchInterest = scan.nextLine();
                    break;
                }
                catch(InputMismatchException E)
                {
                    System.out.println(E);
                }
            }
            PHD_DB[PHDCount++] = this;
        }
        catch(ArrayIndexOutOfBoundsException E)
        {
            System.out.println("PHD DATABASE IS FULL");
        }
    }

    void Display()
    {
        try
        {
            this.PrintOutput();
            System.out.printf(" %-20s",this.researchInterest);
        }
        catch(NullPointerException ignored){}
    }

    static void DisplaySpecificInfo(String courseCode)
    {
        try
        {
            for (PHD PHDCourses : PHD_DB)
            {
                if(PHDCourses.courseCode.equals(courseCode))
                {
                    System.out.println("----PHD DATABASE----");
                    System.out.printf("%-12s %-80s %-8s %-10s %-15s %-15s %-15s %-20s\n","COURSE CODE","COURSE NAME","DEPT ID","DEPT NAME","COURSE DURATION","MINIMUM CREDITS","MAXIMUM CREDITS","RESEARCH INTEREST");
                    PHDCourses.Display();
                    System.out.println();
                    break;
                }
            }
        }
        catch(NullPointerException ignored)
        {
            System.out.println("PHD COURSE CODE NOT FOUND...");
        }
    }

    static void DisplayAllInfo()
    {
        try
        {
            System.out.println("----PHD DATABASE----");
            System.out.printf("%-12s %-80s %-8s %-10s %-15s %-15s %-15s %-20s\n","COURSE CODE","COURSE NAME","DEPT ID","DEPT NAME","COURSE DURATION","MINIMUM CREDITS","MAXIMUM CREDITS","RESEARCH INTEREST");
            for (PHD PHDCourses : PHD_DB)
            {
                PHDCourses.Display();
                System.out.println();
            }
        }
        catch(NullPointerException ignored){}
    }
}

class Salary extends College
{
    double BSalary,HRA,DA,GSalary,NSalary;
    int SalaryCount = 0;
    boolean isTeaching;
    static Salary []Salary_DB = new Salary[5];

    Salary(double BSalary,boolean isTeaching) throws InvalidSalaryException
    {
        if (BSalary < 0)
            throw new InvalidSalaryException("Invalid BASIC SALARY");
        else
        {
            try
            {
                this.BSalary = BSalary;
                this.isTeaching = isTeaching;
                this.HRA = (0.15 * this.BSalary);
                this.DA = (0.35 * this.BSalary);
                this.GSalary = this.BSalary + this.HRA + this.DA;
                this.NSalary = (isTeaching)?(this.BSalary > 500000) ? GSalary + (0.25 * GSalary) : GSalary : GSalary;
                Salary_DB[SalaryCount++] = this;
            }
            catch(ArrayIndexOutOfBoundsException E)
            {
                System.out.println(E);
            }
        }
    }

    void SalaryOutput()
    {
        try
        {
            System.out.printf("%-15.2f %-15.2f %-15.2f", this.BSalary, this.GSalary, this.NSalary);
        }
        catch(NullPointerException ignored){}
    }
}

class Employees extends College
{
    int empID;
    String empName;
    Department Dept;
    Salary Salary;

    static void EmployeesMenu()
    {
        System.out.println("----EMPLOYEES----");
        System.out.println("1. ACCESS INTO TEACHING EMPLOYEE SECTION");
        System.out.println("2. ACCESS INTO NON TEACHING SECTION");
        System.out.println("3. DISPLAY THE ENTIRE EMPLOYEES DATABASE");
        System.out.println("4. EXIT THE EMPLOYEES DATABASE");
    }

    static int choice;

    static void EmployeesDB()
    {
        do
        {
            Employees.EmployeesMenu();
            System.out.println("ENTER YOUR CHOICE : ");
            choice = scan.nextInt();
            switch(choice)
            {
                case 1 :
                   Teaching.EmployeeTeaching();
                   break;
                case 2 :
                    NonTeaching.EmployeeNonTeaching();
                    break;
                case 3 :
                    Teaching.DisplayAllInfo();
                    NonTeaching.DisplayAllInfo();
                    break;
                case 4 :
                    System.out.println("EXITING THE EMPLOYEES DATABASE...");
                    break;
                default :
                    System.out.println("ENTER VALID CHOICE...");
            }
        }
        while(choice != 4);
    }

    void GetEmpID(int empID) throws ExistingEmployeeID
    {
        try
        {
            for (Teaching Teach : Teaching.Teaching_DB)
                if (Teach.empID == empID)
                    throw new ExistingEmployeeID("Existing EMPLOYEE ID");
            for (NonTeaching NonTeach : NonTeaching.NonTeaching_DB)
                if (NonTeach.empID == empID)
                    throw new ExistingEmployeeID("Existing EMPLOYEE ID");
        }
        catch(NullPointerException ignored){}
        this.empID=empID;
    }

    void GetInput()
    {
        while(true)
        {
            try
            {
                System.out.println("Enter the EMPLOYEE ID : ");
                GetEmpID(scan.nextInt());
                break;
            }
            catch(ExistingEmployeeID | InputMismatchException E)
            {
                System.out.println(E);
            }
        }
        while(true)
        {
            try
            {
                System.out.println("Enter the EMPLOYEE NAME : ");
                scan.nextLine();
                this.empName = scan.nextLine();
                break;
            }
            catch(InputMismatchException E)
            {
                System.out.println(E);
            }
        }
        while(true)
        {
            try
            {
                System.out.println("Enter the DEPT ID and DEPT NAME : ");
                this.Dept = new Department();
                break;
            }
            catch(InputMismatchException E)
            {
                System.out.println(E);
            }
        }
    }

    void PrintOutput()
    {
        try
        {
            System.out.printf("%-12d %-40s ",this.empID,this.empName);
            this.Dept.DeptOutput();
            System.out.print(" ");
            this.Salary.SalaryOutput();
        }
        catch(NullPointerException ignored){}
    }
}

class Teaching extends Employees
{
    String profGrade;
    static int TeachingCount = 0;
    static Teaching []Teaching_DB = new Teaching[5];
    static void TeachingMenu()
    {
        System.out.print("----TEACHING EMPLOYEE----\n");
        System.out.println("1. ENTER A NEW RECORD INTO THE TEACHING EMPLOYEE DATABASE");
        System.out.println("2. SHOW THE DETAILS OF THE ENTERED EMPLOYEE ID");
        System.out.println("3. DISPLAY THE ENTIRE TEACHING EMPLOYEE DATABASE");
        System.out.println("4. EXIT THE TEACHING EMPLOYEE SECTION");
    }

    static int choice;

    static void EmployeeTeaching()
    {
        do
        {
            Teaching.TeachingMenu();
            System.out.println("ENTER YOUR CHOICE : ");
            choice = scan.nextInt();
            switch(choice)
            {
                case 1 :
                    new Teaching();
                    break;
                case 2 :
                    int empID;
                    System.out.println("ENTER THE EMPLOYEE ID : ");
                    empID = scan.nextInt();
                    Teaching.DisplaySpecificInfo(empID);
                    break;
                case 3 :
                    Teaching.DisplayAllInfo();
                    break;
                case 4 :
                    System.out.println("EXITING TEACHING EMPLOYEE SECTION...");
                    break;
                default :
                    System.out.println("ENTER VALID CHOICE...");
            }
        }
        while(choice != 4);
    }


    Teaching()
    {
        try
        {
            this.GetInput();
            while(true)
            {
                try
                {
                    System.out.println("Enter the BASIC SALARY : ");
                    this.Salary = new Salary(scan.nextDouble(), true);
                    break;
                }
                catch(InvalidSalaryException | InputMismatchException E)
                {
                    System.out.println(E);
                }
            }
            while(true)
            {
                try
                {
                    System.out.println("Enter your GRADE : ");
                    scan.nextLine();
                    this.profGrade = scan.nextLine();
                    break;
                }
                catch(InputMismatchException E)
                {
                System.out.println(E);
                }
            }
            Teaching_DB[TeachingCount++] = this;
        }
        catch(ArrayIndexOutOfBoundsException E)
        {
            System.out.println("TEACHING DATABASE IS FULL");
        }
    }

    void Display()
    {
        try
        {
            this.PrintOutput();
            System.out.printf(" %-20s",this.profGrade);
        }
        catch(NullPointerException ignored){}
    }

    static void DisplaySpecificInfo(int empID)
    {
        try
        {
            for (Teaching Teach : Teaching_DB)
            {
                if(Teach.empID == empID)
                {
                    System.out.println("----TEACHING EMPLOYEE DATABASE----");
                    System.out.printf("%-12s %-40s %-8s %-10s %-15s %-15s %-15s %-20s\n","EMPLOYEE ID","EMPLOYEE NAME","DEPT ID","DEPT NAME","BASIC SALARY","GROSS SALARY","NET SALARY","PROFESSOR GRADE");
                    Teach.Display();
                    System.out.println();
                    break;
                }
            }
        }
        catch(NullPointerException ignored)
        {
            System.out.println("TEACHING EMPLOYEE ID NOT FOUND...");
        }
    }

    static void DisplayAllInfo()
    {
        try
        {
            System.out.println("----TEACHING EMPLOYEE DATABASE----");
            System.out.printf("%-12s %-40s %-8s %-10s %-15s %-15s %-15s %-20s\n","EMPLOYEE ID","EMPLOYEE NAME","DEPT ID","DEPT NAME","BASIC SALARY","GROSS SALARY","NET SALARY","PROFESSOR GRADE");
            for (Teaching Teach : Teaching_DB)
            {
                Teach.Display();
                System.out.println();
            }
        }
        catch(NullPointerException ignored){}
    }
}

class NonTeaching extends Employees
{
    String jobTitle;
    static int NonTeachingCount = 0;
    static NonTeaching []NonTeaching_DB = new NonTeaching[5];

    static void NonTeachingMenu()
    {
        System.out.print("----NON TEACHING EMPLOYEE----\n");
        System.out.println("1. ENTER A NEW RECORD INTO THE NON TEACHING EMPLOYEE DATABASE");
        System.out.println("2. SHOW THE DETAILS OF THE ENTERED EMPLOYEE ID");
        System.out.println("3. DISPLAY THE ENTIRE NON TEACHING EMPLOYEE DATABASE");
        System.out.println("4. EXIT THE NON TEACHING EMPLOYEE SECTION");
    }

    static int choice;

    static void EmployeeNonTeaching()
    {
        do
        {
            NonTeaching.NonTeachingMenu();
            System.out.println("ENTER YOUR CHOICE : ");
            choice = scan.nextInt();
            switch(choice)
            {
                case 1 :
                    new NonTeaching();
                    break;
                case 2 :
                    int empID;
                    System.out.println("ENTER THE EMPLOYEE ID : ");
                    empID = scan.nextInt();
                    NonTeaching.DisplaySpecificInfo(empID);
                    break;
                case 3:
                    NonTeaching.DisplayAllInfo();
                    break;
                case 4 :
                    System.out.println("EXITING NON TEACHING EMPLOYEE SECTION...");
                    break;
                default :
                    System.out.println("ENTER VALID CHOICE...");
            }
        }
        while(choice != 4);
    }

    NonTeaching()
    {
        try
        {
            this.GetInput();
            while(true)
            {
                try
                {
                    System.out.println("Enter the BASIC SALARY : ");
                    this.Salary = new Salary(scan.nextDouble(), false);
                    break;
                }
                catch(InvalidSalaryException | InputMismatchException E)
                {
                    System.out.println(E);
                }
            }
            while(true)
            {
                try
                {
                    System.out.println("Enter your JOB TITLE : ");
                    scan.nextLine();
                    this.jobTitle = scan.nextLine();
                    break;
                }
                catch(InputMismatchException E)
                {
                    System.out.println(E);
                }
            }
            NonTeaching_DB[NonTeachingCount++] = this;
        }
        catch(ArrayIndexOutOfBoundsException E)
        {
            System.out.println("NON TEACHING DATABASE IS FULL");
        }
    }

    void Display()
    {
        try
        {
            this.PrintOutput();
            System.out.printf(" %-20s",this.jobTitle);
        }
        catch(NullPointerException ignored){}
    }

    static void DisplaySpecificInfo(int empID)
    {
        try
        {
            for (NonTeaching NonTeach : NonTeaching_DB)
            {
                if(NonTeach.empID == empID)
                {
                    System.out.println("----NON TEACHING EMPLOYEE DATABASE----");
                    System.out.printf("%-12s %-40s %-8s %-10s %-15s %-15s %-15s %-20s\n","EMPLOYEE ID","EMPLOYEE NAME","DEPT ID","DEPT NAME","BASIC SALARY","GROSS SALARY","NET SALARY","JOB TITLE");
                    NonTeach.Display();
                    System.out.println();
                    break;
                }
            }
        }
        catch(NullPointerException ignored)
        {
            System.out.println("NON TEACHING EMPLOYEE ID NOT FOUND...");
        }
    }

    static void DisplayAllInfo()
    {
        try
        {
            System.out.println("----TEACHING EMPLOYEE DATABASE----");
            System.out.printf("%-12s %-40s %-8s %-10s %-15s %-15s %-15s %-20s\n","EMPLOYEE ID","EMPLOYEE NAME","DEPT ID","DEPT NAME","BASIC SALARY","GROSS SALARY","NET SALARY","JOB TITLE");
            for (NonTeaching NonTeach : NonTeaching_DB)
                {
                    NonTeach.Display();
                    System.out.println();
                }
        }
        catch(NullPointerException ignored){}
    }
}

public class Main
{
    public static void main(String [] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("----COLLEGE INFORMATION SYSTEM----");
        System.out.println("ENTER YOUR COLLEGE NAME : ");
        College.name=scan.nextLine();
        System.out.println("ENTER YOUR COLLEGE ADDRESS : ");
        College.address=scan.nextLine();

        int choice;

        do
        {
            System.out.println("1. ACCESS INTO EMPLOYEE DATABASE");
            System.out.println("2. ACCESS INTO COURSES DATABASE");
            System.out.println("3. DISPLAY THE ENTIRE COLLEGE DATABASE");
            System.out.println("4. TO EXIT THE COLLEGE INFORMATION SYSTEM");
            System.out.println("ENTER YOUR CHOICE : ");
            choice = scan.nextInt();
            switch (choice)
            {
                case 1 :
                    Employees.EmployeesDB();
                    break;
                case 2 :
                    Courses.CoursesDB();
                    break;
                case 3 :
                    College.DisplayCollegeInfo();
                    System.out.println();
                    UG.DisplayAllInfo();
                    PG.DisplayAllInfo();
                    PHD.DisplayAllInfo();
                    System.out.println();
                    Teaching.DisplayAllInfo();
                    NonTeaching.DisplayAllInfo();
                    break;
                case 4 :
                    System.out.println("EXITING THE COLLEGE INFORMATION SYSTEM...");
                    break;
                default:
                    System.out.println("ENTER VALID CHOICE...");
            }
        }
        while(choice != 4);
    }
}