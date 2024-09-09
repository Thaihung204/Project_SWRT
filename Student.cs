using System;
namespace StudentManagerment
{
    public class Student
    {
        private int studentId { get; set; }
        private String studentName { get; set; }
        private double math { get; set; }
        private double physic { get; set; }
        private double chemistry { get; set; }

        public Student() { }
        public Student(int id, String name, Double math,Double physic,Double chemistry)
        {
            this.studentId = id;
            this.studentName = name;
            this.math = math;
            this.physic = physic;
            this.chemistry = chemistry;
        }

        // Getter và Setter cho studentId
        public int StudentId
        {
            get { return studentId; }
            set { studentId = value; }
        }

        // Getter và Setter cho studentName
        public string StudentName
        {
            get { return studentName; }
            set { studentName = value; }
        }

        // Getter và Setter cho math
        public double Math
        {
            get { return math; }
            set { math = value; }
        }

        // Getter và Setter cho physic
        public double Physic
        {
            get { return physic; }
            set { physic = value; }
        }

        // Getter và Setter cho chemistry
        public double Chemistry
        {
            get { return chemistry; }
            set { chemistry = value; }
        }

        public Double getAvg()
        {
            return (chemistry+math+physic)/ 3;
        }

        public String getRate()
        {
            var a = (chemistry + math + physic) / 3;
            if (a >= 8) return "A";
            if (a >= 6.5) return "B";
            if (a >= 5) return "C";
            return "D";

        }
       
    }
}

