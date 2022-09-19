import java.sql.*;

public class TrainDAO {
    static private String DRIVER_NAME = "com.mysql.jdbc.Driver";
    static private String DB_URL = "jdbc:mysql://localhost:3306/javacasestudy1";
    static private String USERNAME = "root";
    static private String PASSWORD = "Priya@123";

    Class db;
    Connection con;
    Statement stmt;
    PreparedStatement pstmt;
    ResultSet rs;


    public TrainDAO() throws ClassNotFoundException, SQLException {
        db = Class.forName(DRIVER_NAME);
        con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        stmt = null;
        pstmt = null;
        rs = null;
    }

    public static Train findTrain (int trainNo) throws SQLException, ClassNotFoundException {
        TrainDAO db = new TrainDAO();

        db.pstmt = db.con.prepareStatement("SELECT * FROM TRAINS WHERE TRAIN_NO = ?");
        db.pstmt.setInt(1, trainNo);
        db.rs  = db.pstmt.executeQuery();

        while(db.rs.next()){
            if(db.rs.getInt(1) == trainNo){
                return new Train(
                        db.rs.getInt(1),
                        db.rs.getString(2),
                        db.rs.getString(3),
                        db.rs.getString (4),
                        db.rs.getInt(5)
                );
            }
        }
        return null;
    }
}
