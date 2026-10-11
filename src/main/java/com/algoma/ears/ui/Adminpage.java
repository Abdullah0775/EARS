package com.algoma.ears.ui;

import java.sql.Connection;
import java.util.List;

import com.algoma.ears.Admin;
import com.algoma.ears.AdminService;
import com.algoma.ears.Application;
import com.algoma.ears.Faculty;
import com.algoma.ears.Jobs;
import com.algoma.ears.Reviews;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Adminpage { 
    private static ScrollPane sp; 
    private final Connection conn;
    private static AdminService adminService;
    private long id;
    //private Mvc mvc;
    private BorderPane root;


    public Adminpage(Connection conn,long id){
        this.conn = conn;
        this.id = id;
        Adminpage.adminService = new AdminService(conn);
        sp = new ScrollPane();
    }
    public Adminpage(){
        this.conn = null;
        sp = new ScrollPane();
    }
    public Pane getView() {
        root = new BorderPane();
        root.setCenter(sp);
        
        NavCol nc = new NavCol();
        nc.addItem("Faculty").setOnAction(e->{
            List<Faculty> flist = Adminpage.adminService.getAllFac();
            sp.setContent(createCardList(flist));
        });
        nc.addItem("Jobs").setOnAction(e->{
            List<Jobs> jList = Adminpage.adminService.getAllJobs();
            sp.setContent(createCardList(jList));
        });
        nc.addItem("Applications").setOnAction(e->{
            List<Application> aList = Adminpage.adminService.getAllApplications();
            sp.setContent(createCardList(aList));
        });
        nc.addItem("Profile").setOnAction(e->{
            Admin admin = adminService.getProfile(id);
            setMain(admin);
        });

        root.setLeft(nc);
        //Header class??
        //root.setTop(Header);
        //Pane view = new Pane()



        return root;
    }
    public void setView(Pane view){
        root.setCenter(view);
    }  
    
      public static VBox createCardList(List<?> items) {
        VBox container = new VBox(10);
        container.setStyle("-fx-padding: 10px;");
        if(items.get(0)instanceof  Faculty){
            for(Object item: items ){
                Card card = new Card();
                Faculty fac = (Faculty) item;
                card.setTitle(fac.getName() + " - " + fac.getUserId());
                card.setDescription(fac.getSubject());
                card.setCardId(fac.getId());
                card.addButton("Edit/View").setOnAction(e->{
                    setMain(fac);
                });;
                card.addButton("Delete").setOnAction(e->{
                });;
                container.getChildren().add(card.getCard());
            }
            Card card = new Card();
            card.setTitle("Create New Faculty");
            card.addButton(" +  ADD").setOnAction(e->{
                setMain(new Faculty());
            });;
            container.getChildren().add(card.getCard());
        }
        else if(items.get(0) instanceof Jobs){
            for(Object item: items ){
                Card card = new Card();
                Jobs job = (Jobs) item;
                card.setTitle(job.getTitle() );
                card.setCardId(job.getId()); 
                card.setDescription(job.getDescription());
                card.addButton("View Job and Applications").setOnAction(e->{
                    setMain(job);
                });;
                card.addButton("Delete").setOnAction(e->{
                    adminService.deleteJob(job);
                    List <Jobs> jlist = adminService.getAllJobs();
                    sp.setContent(createCardList(jlist));
                });;
                container.getChildren().add(card.getCard());
            }
            Card card = new Card();
            card.setTitle("Create New Job");
            card.addButton(" +  ADD").setOnAction(e->{
                setMain(new Jobs());
            });
            container.getChildren().add(card.getCard());
        }
        else if(items.get(0) instanceof Application){
            
            for(Object item: items ){
                Card card = new Card();
                Application app = (Application) item;
                card.setTitle(app.getJobId() + " - " + app.getStatus());
                card.setDescription(app.getResume());
                card.setCardId(app.getId());
                card.addButton("View Application").setOnAction(e->{
                   setMain(app);
                });
                // card.addButton("View Reviews").setOnAction(e->{
                //     List<Reviews> rev =  adminService.getAppReviews(app.getId());
                //     try {
                //         sp.setContent(createCardList(rev));
                //     } catch (Exception e1) {
                //         // TODO Auto-generated catch block
                //         e1.printStackTrace();
                //     }
                    
                // });;
                container.getChildren().add(card.getCard());
            }
        }
        else if(items.get(0) instanceof Reviews){
            for(Object item: items){
                Card card = new Card();
                Reviews rev = (Reviews) item;
                card.setTitle("By Faculty ID: "+ rev.getFacultyId() + " - For Application ID: " + rev.getApplicationId());
                card.setDescription(rev.getReviewContent());
                card.setCardId(rev.getId());
                container.getChildren().add(card.getCard());
            }
        }
        return container;
    }
   private static void setMain(Object item){
        ViewEditpage vep = new ViewEditpage(item, adminService);
        sp.setContent(vep.getView());
   }

}
