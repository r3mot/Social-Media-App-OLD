package social.Objects;

public class Clubs {

    private String[][] clubs;

    private void setClubs(){
        clubs = new String[13][2];

        clubs[0][0] = "Accounting Society";
        clubs[0][1] = "The Accounting Society is a student-based organization dedicated to promoting professional development among out members";
        clubs[1][0] = "Arts Collective @ CSUDH";
        clubs[1][1] = "Interdisciplinary community organization dedicated to facilitating the accessibility and engagement of the arts.";
        clubs[2][0] = "Black Student Union";
        clubs[2][1] = "We are the Black Student Union. It is our mission to be a main space for Black student life to flourish.";
        clubs[3][0] = "Chemistry & Biochemistry Club";
        clubs[3][1] = "The club's purpose is to expand the perception and appreciation of Chemistry and Biochemistry.";
        clubs[4][0] = "Cyber Security Club";
        clubs[4][1] = "Agroup of ethical hackers, where knowledgable students may discuss security issues and implement solutions.";
        clubs[5][0] = "Esports Associate at CSUDH";
        clubs[5][1] = "Weto incorporate a competative and well versed community that harbors sportsmanship and growth among gaming.";
        clubs[6][0] = "History Club";
        clubs[6][1] = "The CSUDH history club's goal is to create connections, and assist members achieve their academic goals.";
        clubs[7][0] = "Innovation Incubator";
        clubs[7][1] = "A robust multi-faceted business incubator and accelerator. We offer mentorships and educational opportunities.";
        clubs[8][0] = "Korean Culture Club";
        clubs[8][1] = "The Korean Culture Club is a fun and interactive place for students to appreciate Korean culture!";
        clubs[9][0] = "Latino Student Business Association";
        clubs[9][1] = "Provide opportunities that will enable members to grow professionally, personally, and academically.";
        clubs[10][0] = "Logistics and Supply Chain Management";
        clubs[10][1] = "The mission of the Club is to  to educate and establish networking opportunities to students.";
        clubs[11][0] = "Math Club at CSUDH";
        clubs[11][1] = "The purpose of this organization shall be to spread the joy of mathematics throughout the CSUDH.";
        clubs[12][0] = "Pre-Law Society";
        clubs[12][1] = "The purpose of the Club is to provide students with the knowledge necessary to navigate the law school application.";

    }
    public String[][] getClubs(){
        setClubs();
        return this.clubs;
    }
    
}
