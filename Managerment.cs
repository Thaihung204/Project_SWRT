namespace StudentManagerment;

class Program
{

    List<Student> listStudent = new List<Student>();
    private static int currentId = 1;

    static void Main(string[] args)
    {
        Program program = new Program();
        bool running = true;

        while (running)
        {
            Console.WriteLine("\nMenu:");
            Console.WriteLine("1. Add a student");
            Console.WriteLine("2. View a student");
            Console.WriteLine("3. Add a list of students");
            Console.WriteLine("4. Print list of students");
            Console.WriteLine("5. Find the best student");
            Console.WriteLine("6. Exit");

            Console.Write("Select an option: ");
            int choice = Convert.ToInt32(Console.ReadLine());

            switch (choice)
            {
                case 1:
                    program.addStudent();
                    
                    break;
                case 2:
                    Console.Write("Enter student ID to view: ");
                    int viewId = Convert.ToInt32(Console.ReadLine());
                    program.viewStudent(viewId);
                    break;
                case 3:
                    //program.addList(Student);
                    break;
                case 4:
                    program.printList();
                    break;
                case 5:
                    program.findBestStudent();
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    Console.WriteLine("Invalid option. Please try again.");
                    break;
            }
        }

    }

    void addStudent()
    {
        int id = currentId++;
        
        Console.WriteLine("Input Student Name:");
        String name = (Console.ReadLine());
        Console.WriteLine("Input Math Score:");
        Double math = Convert.ToDouble(Console.ReadLine());
        Console.WriteLine("Input Physic Score:");
        Double physic = Convert.ToDouble(Console.ReadLine());
        Console.WriteLine("Input Chemistry Score:");
        Double chemistry = Convert.ToDouble(Console.ReadLine());
        Student st = new Student(id,name,math,physic,chemistry);
        viewStudent(id);
        addList(st);
    }

    void viewStudent(int id)
    {
        Student st = new Student();
        foreach (var s in listStudent)
        {
            if (s.StudentId == id) st = s;
                }

        Console.WriteLine($" Student ID:" + st.StudentId +" Name:"+st.StudentName+ " Math:"+ st.Math + " Physic:"+ st.Physic + " Chemistry:"+ st.Chemistry);
    }

    void addList(Student st)
    {
        listStudent.Add(st);
    }

    void printList()
    {
        foreach (var st in listStudent)
        {
            Console.WriteLine($"Student ID:" + st.StudentId + ",Name:" + st.StudentName + ",Math :" + st.Math + ",Physic:" + st.Physic + ",Chemistry:" + st.Chemistry);
        }
    }

    void findBestStudent()
    {
        Double max = 0;
        int id = 0;
        foreach (var st in listStudent)
        {
            if (st.Math + st.Physic + st.Chemistry > max)
            {
                id = st.StudentId;
                max = st.Math + st.Physic + st.Chemistry;
            }
        }
        foreach (var st in listStudent)
        {
            if (st.StudentId == id)
            {
                Console.WriteLine($"Student ID:" + st.StudentId + ",Name:" + st.StudentName + ",Math :" + st.Math + ",Physic:" + st.Physic + ",Chemistry:" + st.Chemistry);
            }
        }

    }

    
}

