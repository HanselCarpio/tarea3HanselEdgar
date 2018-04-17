package Interface;

import java.util.Random;
import javafx.application.Application;
import javafx.event.EventHandler;
//import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
//import javafx.stage.WindowEvent;

public class Window extends Application implements Runnable{

    private final int WIDTH = 800;//anchura
    private final int HEIGHT = 600;//altura
    private Pane pane;
    private Scene scene;
    private Canvas canvas;//panel
    private Thread thread;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Random Graphics");
        initComponents(primaryStage);
        primaryStage.show();
    }

    private void initComponents(Stage primaryStage) {
        this.pane = new Pane();
        this.scene = new Scene(this.pane, WIDTH, HEIGHT);
        this.canvas = new Canvas(WIDTH, HEIGHT);
        
        this.thread = new Thread(this);
        this.thread.start();
        
        this.pane.getChildren().add(this.canvas);
        primaryStage.setScene(this.scene);
        //primaryStage.setOnCloseRequest(exit);
    }

    private void myDraw(GraphicsContext gc) {
        Random rand = new Random();
        
        int xRect=rand.nextInt(WIDTH-100)+1;
        int yRect=rand.nextInt(HEIGHT-100)+1;
        int xOval=rand.nextInt(WIDTH-100)+1;
        int yOval=rand.nextInt(HEIGHT-100)+1;
        int xProxRect=rand.nextInt(WIDTH-100)+1;
        int yProxRect=rand.nextInt(HEIGHT-100)+1;
        int xProxOval=rand.nextInt(WIDTH-100)+1;
        int yProxoval=rand.nextInt(HEIGHT-100)+1;
        
//        boolean s=true; 
        while(true){    
            try {
                gc.clearRect(0, 0, WIDTH, HEIGHT);
                gc.setFill(Color.AQUA);
                if(xRect<xProxRect)
                    xRect++;
                if(xRect>xProxRect)
                    xRect--;
                if(yRect<yProxRect)
                    yRect++;
                if(yRect>yProxRect)
                    yRect--;
                if(xOval<xProxOval)
                    xOval++;
                if(xOval>xProxOval)
                    xOval--;
                if(yOval<yProxoval)
                    yOval++;
                if(yOval>yProxoval)
                    yOval--;
                if(xRect==xProxRect&&yRect==yProxRect){
                    xProxRect=rand.nextInt(WIDTH-100)+1;
                    yProxRect=rand.nextInt(HEIGHT-100)+1;
                }
                if(xOval==xProxOval&&yOval==yProxoval){
                    xProxOval=rand.nextInt(WIDTH-100)+1;
                    yProxoval=rand.nextInt(HEIGHT-100)+1;
                }
                gc.fillRect(xRect, yRect, 100,100);
                gc.setFill(Color.BLUE);
                gc.fillOval(xOval, yOval, 100, 100);
                Thread.sleep(10);
            } 
            catch (InterruptedException ex) {}
        }
    }//myDraw
    
    @Override
    public void run() {
        GraphicsContext gc = this.canvas.getGraphicsContext2D();
        myDraw(gc);
    }
    
    EventHandler<WindowEvent> exit = new EventHandler<WindowEvent>() {
        @Override
        public void handle(WindowEvent event) {
            System.exit(0);
        }
    };
}