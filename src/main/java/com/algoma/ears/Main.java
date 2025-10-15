package com.algoma.ears;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.algoma.ears.dao.AppDAO;
import com.algoma.ears.dao.JobsDAO;
import com.algoma.ears.ui.Jobspage;
import com.algoma.ears.ui.StageHandler;
import com.algoma.ears.util.DatabaseConnectionManager;

import javafx.stage.Stage;

public class Main extends javafx.application.Application {
    static List <Jobs> jobs = new ArrayList<>();
    static Connection conn;
    public static void main(String[] args) { 

       DatabaseConnectionManager dbm = new DatabaseConnectionManager("127.0.0.1:5401","ears","postgres","Abdullah10");
       try {
           conn = dbm.getConnection();
       } catch (SQLException e) {
           System.err.println(e);
       }

       //Jobs job  = new Jobs();
       JobsDAO jobsDAO = new JobsDAO(conn);
        jobs = jobsDAO.findAll();

        launch(args);
    }

   

    @Override
    public void start(Stage st) throws Exception {
        
        Jobspage jp = new Jobspage(jobs);
        StageHandler sh = new StageHandler(st);
        
        sh.setCurrentView(jp.getView());
        
        // Pane jc = jp.getView();
        // Scene sc = new Scene(jc);
        // st.setScene(sc);
        // st.show();
        

    }

    public static boolean adminAuthentication(String userId, String password){
        Authentication auth = new Authentication(conn);
        return auth.adminLogIn(userId, password);
    }

    public static boolean facultyAuthentication(String userId, String password){
        Authentication auth = new Authentication(conn);
        return auth.facultyLogIn(userId, password);
    }

    public static Application postApplication(long jobId, String resume) {
        AppDAO ad = new AppDAO(conn);
        Application app = new Application();
        app.setId(ad.getLastValue("app_seq")+1);
        app.setJobId(jobId); 
        System.out.println("Posting application for job ID: " + jobId + " with resume: " + resume);
        try {
            app.setResume(resume);
            Application ap1 = ad.create(app);
            System.out.println("Resume set successfully: " + ap1.getResume());
            return ap1;
        } catch (RuntimeException|IOException e) {
            System.err.println("Error setting resume: " + e.getMessage());
            throw new RuntimeException(e);
        }
    } 

}